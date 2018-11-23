/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.constant.UserRequestExtInfoKey;
import us.betahouse.haetae.serviceimpl.user.request.CommonUserRequest;
import us.betahouse.haetae.serviceimpl.user.service.UserService;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.manager.UserManager;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.haetae.user.utils.EncryptUtil;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.validator.MultiValidator;
import us.betahouse.util.wechat.WeChatLoginUtil;

import java.util.Map;
import java.util.UUID;

/**
 * 用户服务实现
 *
 * @author dango.yxm
 * @version : UserServiceImpl.java 2018/11/21 6:39 PM dango.yxm
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("")
    private String APP_ID;

    @Value("")
    private String SECRET;

    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserBasicService userBasicService;

    /**
     * 密码规则校验器
     */
    @Autowired
    private MultiValidator<UserManageRequest> passwordValidator;

    @Override
    public CommonUser register(CommonUserRequest request, OperateContext context) {
        return userManager.create(request);
    }


    @Override
    public CommonUser login(CommonUserRequest request, OperateContext context) {
        // 获取openId
        String openId = null;
        if (StringUtils.isNotBlank(request.getCode())) {
            openId = WeChatLoginUtil.fetchOpenId(request.getCode(), APP_ID, SECRET);
        }
        return userBasicService.login(request.getUsername(), request.getPassword(), openId, context.getOperateIP());
    }

    @Override
    public void logout(CommonUserRequest request, OperateContext context) {
        String openId = WeChatLoginUtil.fetchOpenId(request.getCode(), APP_ID, SECRET);
        UserBO userBO = userRepoService.queryByOpenId(openId);
        AssertUtil.assertNotNull(userBO, "用户不存在");
        userBasicService.loginOut(userBO.getUserId());
    }

    @Override
    public Map<String, PermBO> fetchUserPerms(CommonUserRequest request, OperateContext context) {
        return userBasicService.fetchUserPerms(request.getUserId());
    }

    @Override
    public Map<String, RoleBO> fetchUserRoles(CommonUserRequest request, OperateContext context) {
        return userBasicService.fetchUserRoles(request.getUserId());
    }

    @Override
    public void modifyUser(CommonUserRequest request, OperateContext context) {

        String openId = WeChatLoginUtil.fetchOpenId(request.getCode(), APP_ID, SECRET);
        UserBO userBO = userRepoService.queryByOpenId(openId);
        AssertUtil.assertNotNull(userBO, "用户不存在");

        String encodePwd = EncryptUtil.encryptPassword(request.getPassword(), userBO.getSalt());

        // 传入了密码 && 不和原密码相同 就认为是要修改密码
        if (request.getPassword() != null && !StringUtils.equals(EncryptUtil.encryptPassword(request.getPassword(), userBO.getSalt()), userBO.getPassword())) {

            // 如果传入了老密码 就认为需要比较老密码
            if(StringUtils.isNotBlank(request.fetchExtInfo(UserRequestExtInfoKey.USER_OLD_PASSWORD))){
                // 传入的老密码加密
                String oldEncodePwd = EncryptUtil.encryptPassword(request.fetchExtInfo(UserRequestExtInfoKey.USER_OLD_PASSWORD), userBO.getSalt());
                AssertUtil.assertTrue(StringUtils.equals(userBO.getPassword(), oldEncodePwd), "旧密码错误");
            }

            // 校验新密码是否满足 密码规则
            passwordValidator.validate(request);

            userBO.setSalt(UUID.randomUUID().toString());
            userBO.setPassword(EncryptUtil.encryptPassword(request.getPassword(), userBO.getSalt()));
        }
        userRepoService.updateUserByUserId(userBO);
    }
}

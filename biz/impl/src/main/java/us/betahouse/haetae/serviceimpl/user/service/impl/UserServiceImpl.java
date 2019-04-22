/*
  betahouse.us
  CopyRight (c) 2012 - 2018
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

    @Value("${wechat.appId}")
    private String APP_ID;

    @Value("${wechat.secret}")
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
    public CommonUser fetchUser(CommonUserRequest request, OperateContext context) {
        return userBasicService.getUserId(request.getUserId());
    }

    @Override
    public void logout(CommonUserRequest request, OperateContext context) {
        userBasicService.loginOut(request.getUserId());
    }

    @Override
    public void wxLogout(CommonUserRequest request, OperateContext context) {
        userBasicService.wxLoginOut(request.getUserId());
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

        UserBO userBO = userRepoService.queryByUserId(request.getUserId());
        AssertUtil.assertNotNull(userBO, "用户不存在");

        // 传入了新密码 就认为是要修改密码
        if (StringUtils.isNotBlank(request.fetchExtInfo(UserRequestExtInfoKey.USER_NEW_PASSWORD))) {
            // 传入了老密码就认为是要 校验老密码
            if (StringUtils.isNotBlank(request.getPassword())) {
                String oldEncodePwd = EncryptUtil.encryptPassword(request.getPassword(), userBO.getSalt());
                AssertUtil.assertTrue(StringUtils.equals(userBO.getPassword(), oldEncodePwd), "旧密码错误");
            }

            // 将新密码组装入管理请求
            request.setPassword(request.fetchExtInfo(UserRequestExtInfoKey.USER_NEW_PASSWORD));
            // 校验新密码是否满足 密码规则
            passwordValidator.validate(request);

            userBO.setSalt(UUID.randomUUID().toString());
            userBO.setPassword(EncryptUtil.encryptPassword(request.getPassword(), userBO.getSalt()));
        }
        userRepoService.updateUserByUserId(userBO);
    }

    @Override
    public void modifyUserMajorAndClassAndGrade(CommonUserRequest request, OperateContext context) {
        userBasicService.modifyMajorAndClassAndGrade(request.getUserId(), request.getUserInfoBO().getMajor(), request.getUserInfoBO().getClassId(), request.getUserInfoBO().getGrade());
    }
}

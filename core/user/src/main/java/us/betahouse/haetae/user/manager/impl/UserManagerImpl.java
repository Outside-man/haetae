/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.manager.UserManager;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.haetae.user.user.builder.UserBOBuilder;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.LoggerUtil;
import us.betahouse.util.validator.MultiValidator;


/**
 * 用户管理器
 *
 * @author dango.yxm
 * @version : UserManagerImpl.java 2018/11/18 下午10:49 dango.yxm
 */
@Service
public class UserManagerImpl implements UserManager {

    private final Logger LOGGER = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private RoleRepoService roleRepoService;

    @Autowired
    private PermRepoService permRepoService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Autowired
    private MultiValidator<UserManageRequest> userRegisterValidator;

    @Override
    @Transactional
    @Log(LoggerName = "us.betahouse.haetae.user.manager.UserManager")
    public UserBO create(UserManageRequest request) {
        // 校验用户是否合法
        userRegisterValidator.validate(request);

        // 创建用户
        UserBOBuilder userBOBuilder = UserBOBuilder.getInstance(request.getUserName(), request.getPassword())
                .withOpenId(request.getOpenId())
                .withSalt(request.getSalt());
        UserBO user = userRepoService.createUser(userBOBuilder.build());

        // 绑定用户信息
        UserInfoBO userInfo = request.getUserInfoBO();
        if (userInfo != null) {
            try {
                userInfoRepoService.bindUserInfo(user.getUserId(), userInfo);
            }catch (Throwable t){
                throw t;
            }
        } else {
            LoggerUtil.warn(LOGGER, "创建时没有绑定用户信息 UserManageRequest={0}", request);
        }

        // 绑定角色
        roleRepoService.userBindRoles(user.getUserId(), request.getRoleIds());

        // 绑定权限
        permRepoService.userBindPerms(user.getUserId(), request.getPermIds());

        return user;
    }

    @Override
    public void batchAddRole(UserManageRequest request) {
        roleRepoService.userBindRoles(request.getUserId(), request.getRoleIds());
    }

    @Override
    public void batchAddPerm(UserManageRequest request) {
        permRepoService.userBindPerms(request.getUserId(), request.getPermIds());
    }
}

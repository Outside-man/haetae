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
import us.betahouse.haetae.user.request.UserCreateRequest;
import us.betahouse.haetae.user.user.model.CommonUser;
import us.betahouse.haetae.user.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.user.model.basic.perm.UserBO;
import utils.LoggerUtil;
import validator.MultiValidator;

import java.util.Collections;

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
    private MultiValidator<UserBO> userRegisterValidator;

    @Override
    @Transactional
    public CommonUser create(UserCreateRequest request) {
        UserBO user = request.getUserBO();
        UserInfoBO userInfo = request.getUserInfoBO();

        // 校验用户是否合法
        userRegisterValidator.validate(user);

        // 创建用户
        userRepoService.createUser(user);

        // 绑定用户信息
        if (userInfo != null) {
            userInfoRepoService.bindUserInfo(user.getUserId(), userInfo);
        } else {
            LoggerUtil.warn(LOGGER, "创建时没有绑定用户信息 UserCreateRequest={0}", request);
        }

        // 构建用户信息返回
        CommonUser commonUser = new CommonUser();
        commonUser.setUserId(user.getUserId());
        commonUser.setUserInfo(userInfo);
        return commonUser;
    }

    @Override
    public void addRole(String userId, String roleId) {
        roleRepoService.userBindRoles(userId, Collections.singletonList(roleId));
    }

    @Override
    public void addPerm(String userId, String permId) {
        permRepoService.userBindPerms(userId, Collections.singletonList(permId));
    }
}

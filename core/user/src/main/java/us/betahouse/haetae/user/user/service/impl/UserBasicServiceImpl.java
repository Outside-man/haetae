/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.enums.UserErrorCode;
import us.betahouse.haetae.user.user.helper.PermissionHelper;
import us.betahouse.haetae.user.user.helper.UserHelper;
import us.betahouse.haetae.user.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.user.model.CommonUser;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.haetae.user.utils.EncryptUtil;
import utils.AssertUtil;
import utils.LoggerUtil;

import java.util.Date;
import java.util.UUID;

/**
 * 用户基础服务实现
 *
 * @author dango.yxm
 * @version : UserBasicServiceImpl.java 2018/11/18 下午2:55 dango.yxm
 */
@Service
public class UserBasicServiceImpl implements UserBasicService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserBasicServiceImpl.class);

    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private PermissionHelper permissionHelper;

    @Override
    public CommonUser login(String username, String password) {
        return login(username, password, null);
    }

    @Override
    public CommonUser login(String username, String password, String loginIP) {
        // 验证账号密码正确
        UserBO userBO = userRepoService.queryByUserName(username);
        AssertUtil.assertNotNull(userBO, UserErrorCode.USERNAME_PASSWORD_NOT_RIGHT);
        boolean passwordRight = StringUtils.equals(EncryptUtil.encryptPassword(password, userBO.getSalt()), userBO.getPassword());
        AssertUtil.assertTrue(passwordRight, UserErrorCode.USERNAME_PASSWORD_NOT_RIGHT);
        // 更新登陆信息
        if (StringUtils.isBlank(loginIP)) {
            LoggerUtil.warn(LOGGER, "用户登陆没有登陆ip信息");
        }
        userBO.setLastLoginIP(loginIP);
        userBO.setLastLoginDate(new Date());
        userRepoService.updateUserByUserId(userBO);

        CommonUser user = new CommonUser();

        // 获取 用户id
        String userId = userBO.getUserId();
        user.setUserId(userId);
        // fetch 用户信息
        userHelper.fillUserInfo(user);
        if (user.getUserInfo() == null) {
            LoggerUtil.warn(LOGGER, "用户还未绑定用户信息");
        }
        // fetch 用户角色
        userHelper.fillRole(user);
        // fetch 用户权限
        permissionHelper.fillUserPermission(user);
        return user;
    }

    @Override
    public void loginOut(String userId) {
    }

    @Override
    public void modifyPassword(String userId, String password) {
        UserBO userBO = userRepoService.queryByUserId(userId);
        AssertUtil.assertNotNull(userBO, UserErrorCode.USER_NOT_EXIST);
        userBO.setSalt(UUID.randomUUID().toString());
        userBO.setPassword(EncryptUtil.encryptPassword(password, userBO.getSalt()));
        userRepoService.updateUserByUserId(userBO);
    }

    @Override
    public CommonUser modifyUserInfo(String userId, UserInfoBO userInfoBO) {
        return null;
    }
}

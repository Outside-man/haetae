/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.enums.UserErrorCode;
import us.betahouse.haetae.user.model.BasicUser;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.user.helper.UserHelper;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.haetae.user.utils.EncryptUtil;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.LoggerUtil;

import java.util.*;

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
    private PermRepoService permRepoService;

    @Autowired
    private UserHelper userHelper;

    @Override
    public CommonUser login(String username, String password, String openId, String loginIP) {
        UserBO userBO = userRepoService.queryByUserName(username);
        AssertUtil.assertNotNull(userBO, UserErrorCode.USERNAME_PASSWORD_NOT_RIGHT);
        boolean passwordRight = StringUtils.equals(EncryptUtil.encryptPassword(password, userBO.getSalt()), userBO.getPassword());
        AssertUtil.assertTrue(passwordRight, UserErrorCode.USERNAME_PASSWORD_NOT_RIGHT);

        // 更新登陆信息
        if (StringUtils.isBlank(loginIP)) {
            LoggerUtil.warn(LOGGER, "用户登陆没有登陆ip信息");
        }
        if (StringUtils.isNotBlank(openId)) {
            userBO.setOpenId(openId);

            // 如果之前登陆的账号没正常退出 需要自动登出
            UserBO beforeLoginUser = userRepoService.queryByOpenId(openId);
            if (beforeLoginUser != null && StringUtils.equals(openId, beforeLoginUser.getOpenId())) {
                userRepoService.clearOpenIdAndSessionId(beforeLoginUser.getUserId());
            }
        }
        String token = UUID.randomUUID().toString();
        userBO.setLastLoginIP(loginIP);
        userBO.setLastLoginDate(new Date());
        // 覆盖会话信息
        userBO.setSessionId(EncryptUtil.parseToken(token));
        userRepoService.updateUserByUserId(userBO);

        CommonUser user = getByUserId(userBO.getUserId());
        // 存储登陆凭证
        user.setToken(token);
        return user;
    }

    @Override
    public CommonUser setToken(CommonUser commonUser){
        UserBO userBO = userRepoService.queryByUserId(commonUser.getUserId());
        String token = UUID.randomUUID().toString();
        userBO.setSessionId(EncryptUtil.parseToken(token));
        userRepoService.updateUserByUserId(userBO);
        CommonUser user = getByUserId(userBO.getUserId());
        // 存储登陆凭证
        user.setToken(token);
        return user;
    }

    @Override
    public CommonUser getByUserId(String userId) {
        CommonUser user = new CommonUser();
        // 获取 用户id
        user.setUserId(userId);
        // fetch 用户信息
        userHelper.fillUserInfo(user);
        if (user.getUserInfo() == null) {
            LoggerUtil.warn(LOGGER, "用户还未绑定用户信息");
        }
        // fetch 用户角色
        userHelper.fillRole(user);
        return user;
    }

    @Override
    public CommonUser getByStuId(String stuId) {
        UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuId);
        if(userInfoBO==null){
            return null;
        }
        return getByUserId(userInfoBO.getUserId());
    }

    @Override
    public UserBO checkLogin(String token, String loginIP) {
        String sessionId = EncryptUtil.parseToken(token);
        UserBO userBO = userRepoService.queryBySessionId(sessionId);
        if (userBO == null) {
            LoggerUtil.info(LOGGER, "用户登陆失效, token={0}, sessionId={1}, IP={2}", token, sessionId, loginIP);
            return null;
        }

        // 更新登陆信息
        if (StringUtils.isBlank(loginIP)) {
            LoggerUtil.warn(LOGGER, "用户登陆没有登陆ip信息");
        }
        userBO.setLastLoginIP(loginIP);
        userBO.setLastLoginDate(new Date());
        userRepoService.updateUserByUserId(userBO);
        return userBO;
    }

    @Override
    public void loginOut(String userId) {
        // 清除用户 sessionId
        userRepoService.clearSessionId(userId);
    }

    @Override
    public void wxLoginOut(String userId) {
        // 清除用户openid and sessionId
        userRepoService.clearOpenIdAndSessionId(userId);
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
    public void modifyMajorAndClassAndGrade(String userId, String major, String classId, String grade) {
        UserInfoBO userInfoBO=new UserInfoBO();
        userInfoBO.setMajor(major);
        userInfoBO.setClassId(classId);
        userInfoBO.setGrade(grade);
        userInfoRepoService.modifyUserInfoByUserId(userId, userInfoBO);
    }

    @Override
    public void modifyUserInfo(BasicUser basicUser) {
        String userId = basicUser.getUserId();
        UserBO userBO = userRepoService.queryByUserId(userId);
        AssertUtil.assertNotNull(userBO, UserErrorCode.USER_NOT_EXIST);
        // 如果没有用户信息 就是绑定用户信息
        if (userInfoRepoService.queryUserInfoByUserId(userId) == null) {
            userInfoRepoService.bindUserInfo(userId, basicUser.getUserInfo());
        } else {
            // 修改用户信息
            userInfoRepoService.modifyUserInfoByUserId(userId, basicUser.getUserInfo());
        }
    }

    @Override
    public Map<String, PermBO> fetchUserPerms(String userId) {
        return userHelper.fetchUserPermissions(userId);
    }

    @Override
    public Map<String, RoleBO> fetchUserRoles(String userId) {
        return userHelper.fetchUserRoles(userId);
    }

    @Override
    public boolean verifyPermissionByPermId(String userId, List<String> permIds) {
        // 判断用户上是否存在权限
        if (permRepoService.verifyUserPermRelationByPermId(userId, permIds)) {
            return true;
        }
        // 判断用户的角色上是否存在权限
        List<String> roleIds = new ArrayList<>(userHelper.fetchUserRoles(userId).keySet());
        for (String roleId : roleIds) {
            if (permRepoService.verifyRolePermRelationByPermId(roleId, permIds)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean verifyPermissionByPermType(String userId, List<String> permTypes) {

        // 判断用户上是否存在权限
        if (permRepoService.verifyUserPermRelationByPermType(userId, permTypes)) {
            return true;
        }
        // 判断用户的角色上是否存在权限
        List<String> roleIds = new ArrayList<>(userHelper.fetchUserRoles(userId).keySet());
        for (String roleId : roleIds) {
            if (permRepoService.verifyRolePermRelationByPermType(roleId, permTypes)) {
                return true;
            }
        }
        return false;
    }
}

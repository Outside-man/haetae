/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.BasicUser;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.*;
import java.util.function.Function;


/**
 * 用户助手类
 *
 * @author dango.yxm
 * @version : UserHelper.java 2018/11/17 下午8:34 dango.yxm
 */
@Component
public class UserHelper {

    @Autowired
    private RoleRepoService roleRepoService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Autowired
    protected PermRepoService permRepoService;

    /**
     * 填充用户信息
     *
     * @param user
     */
    public void fillUserInfo(BasicUser user) {
        checkBaseUser(user);
        user.setUserInfo(userInfoRepoService.queryUserInfoByUserId(user.getUserId()));

    }


    /**
     * 填充角色信息
     *
     * @param user
     */
    public void fillRole(CommonUser user) {
        checkBaseUser(user);
        roleRepoService.queryRolesByUserId(user.getUserId()).forEach(user::putRole);
    }

    /**
     * 填充用户的权限
     *
     * @param userId
     */
    public Map<String, PermBO> fetchUserPermissions(String userId) {
        AssertUtil.assertStringNotBlank(userId, CommonResultCode.SYSTEM_ERROR.getMessage(), "用户id不能为空");

        Map<String, PermBO> permissions = new HashMap<>();

        // 组装用户上的权限
        putMap(permissions, permRepoService.queryPermByUserId(userId), PermBO::getPermId);

        // 组装用户角色上的权限
        List<String> roleIds = new ArrayList<>();
        CollectionUtils.toStream(roleRepoService.queryRolesByUserId(userId))
                .filter(Objects::nonNull)
                .forEach(roleBO -> roleIds.add(roleBO.getRoleId()));

        List<PermBO> rolePermList = new ArrayList<>(permRepoService.batchQueryPermByRoleId(roleIds));
        putMap(permissions, rolePermList, PermBO::getPermId);

        return permissions;
    }

    /**
     * 填充用户的角色
     *
     * @param userId
     */
    public Map<String, RoleBO> fetchUserRoles(String userId) {
        AssertUtil.assertStringNotBlank(userId, CommonResultCode.SYSTEM_ERROR.getMessage(), "用户id不能为空");

        Map<String, RoleBO> roles = new HashMap<>();

        // 组装用户上的角色
        List<RoleBO> roleBOS = roleRepoService.queryRolesByUserId(userId);
        putMap(roles, roleBOS, RoleBO::getRoleId);

        return roles;
    }

    /**
     * 存入map
     *
     * @param map
     * @param objList
     */
    private <T> void putMap(Map<String, T> map, List<T> objList, Function<T, String> getKey) {
        AssertUtil.assertNotNull(objList);
        if (map == null) {
            map = new HashMap<>();
        }
        for (T t : objList) {
            if (t != null) {
                map.put(getKey.apply(t), t);
            }
        }
    }

    /**
     * 检查user模型
     *
     * @param user
     */
    protected void checkBaseUser(BasicUser user) {
        AssertUtil.assertNotNull(user, CommonResultCode.SYSTEM_ERROR.getMessage(), "用户不能为空");
        AssertUtil.assertStringNotBlank(user.getUserId(), CommonResultCode.SYSTEM_ERROR.getMessage(), "用户id不能为空");
    }
}

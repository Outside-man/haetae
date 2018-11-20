/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.user.model.CommonUser;
import us.betahouse.haetae.user.user.model.basic.perm.RoleBO;
import us.betahouse.util.utils.AssertUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限助手
 *
 * @author dango.yxm
 * @version : PermissionHelper.java 2018/11/17 下午4:19 dango.yxm
 */
@Component
public class PermissionHelper extends BaseHelper {

    /**
     * 填充用户的权限
     *
     * @param user
     */
    public void fillUserPermission(CommonUser user) {
        checkBaseUser(user);
        // 组装用户上的权限
        parsePermission(user, permRepoService.queryPermByUserId(user.getUserId()));
    }

    /**
     * 获取角色上的权限
     *
     * @param user
     * @return
     */
    public Map<String, PermBO> fetchRolePermission(CommonUser user) {
        Map<String, PermBO> rolePerms = new HashMap<>();
        // 如果用户模型上为null 就需要check以下
        if (user.getRoleInfo() == null) {
            roleRepoService.queryRolesByUserId(user.getUserId()).forEach(user::putRole);
        }

        // 用户上有角色 就需要获取角色上的权限
        if (!user.getRoleInfo().isEmpty()) {
            for (RoleBO role : user.getRoleInfo().values()) {
                permRepoService.queryPermByRoleId(role.getRoleId()).forEach(permBO -> rolePerms.put(permBO.getPermId(), permBO));
            }
        }
        return rolePerms;
    }

    /**
     * 解析组装权限map
     *
     * @param permList
     */
    private void parsePermission(CommonUser user, List<PermBO> permList) {
        AssertUtil.assertNotNull(permList);
        for (PermBO perm : permList) {
            if (perm != null) {
                user.putPerm(perm);
            }
        }
    }
}

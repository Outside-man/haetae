/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.user.model.CommonUser;
import utils.AssertUtil;
import utils.CollectionUtils;

import java.util.List;

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

        // 如果用户模型上为null 就需要check以下
        if(user.getRoleInfo() == null){
            user.setRoleInfo(roleRepoService.queryRolesByUserId(user.getUserId()));
        }

        // 用户上有角色 就需要组装角色上的权限
        if (!CollectionUtils.isEmpty(user.getRoleInfo())) {
            for (RoleBO role : user.getRoleInfo()) {
                parsePermission(user, permRepoService.queryPermByRoleId(role.getRoleId()));
            }
        }
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

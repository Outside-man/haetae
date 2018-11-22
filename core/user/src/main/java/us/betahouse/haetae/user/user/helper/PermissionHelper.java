/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.model.AuthorityUser;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.util.utils.AssertUtil;

import java.util.ArrayList;
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
    public void fillUserPermission(AuthorityUser user) {
        checkBaseUser(user);
        // 组装用户上的权限
        parsePermission(user, permRepoService.queryPermByUserId(user.getUserId()));

        // 组装用户角色上的权限
        roleRepoService.queryRolesByUserId(user.getUserId()).forEach(user::putRole);
        List<String> roleIds = new ArrayList<>(user.getRoleInfo().keySet());

        List<PermBO> rolePermList = new ArrayList<>(permRepoService.batchQueryPermByRoleId(roleIds));
        parsePermission(user, rolePermList);

    }

    /**
     * 解析组装权限map
     *
     * @param permList
     */
    private void parsePermission(AuthorityUser user, List<PermBO> permList) {
        AssertUtil.assertNotNull(permList);
        for (PermBO perm : permList) {
            if (perm != null) {
                user.putPerm(perm);
            }
        }
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import enums.CommonResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.model.perm.PermBO;
import us.betahouse.haetae.user.model.perm.RoleBO;
import us.betahouse.haetae.user.user.BaseUser;
import utils.AssertUtil;
import utils.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 权限助手
 *
 * @author dango.yxm
 * @version : PermissionHelper.java 2018/11/17 下午4:19 dango.yxm
 */
@Component
public class PermissionHelper {

    @Autowired
    private PermRepoService permRepoService;

    @Autowired
    private RoleRepoService roleRepoService;


    public void fillUserPermission(BaseUser user) {
        AssertUtil.assertNotNull(user, CommonResultCode.SYSTEM_ERROR.getErrorMsg(), "用户不能为空");
        String userId = user.getUserId();
        AssertUtil.assertStringNotBlank(userId, CommonResultCode.SYSTEM_ERROR.getErrorMsg(), "用户id不能为空");
        // 组装用户上的权限
        parsePermission(user.getPermission(), permRepoService.queryPermByUserId(userId));
        // 用户上有角色 就需要组装角色上的权限
        if(!CollectionUtils.isEmpty(user.getRoleInfo())){
            for (RoleBO role : user.getRoleInfo()) {
                parsePermission(user.getPermission(), permRepoService.queryPermByRoleId(role.getRoleId()));
            }
        }
    }

    /**
     * 解析组装权限map
     *
     * @param permission
     * @param permList
     */
    private void parsePermission(Map<String, PermBO> permission, List<PermBO> permList) {
        AssertUtil.assertNotNull(permission);
        AssertUtil.assertNotNull(permList);
        for (PermBO perm : permList) {
            if (perm != null) {
                permission.put(perm.getPermType(), perm);
            }
        }
    }
}

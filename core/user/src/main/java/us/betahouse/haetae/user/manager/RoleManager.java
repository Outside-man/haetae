/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager;

import us.betahouse.haetae.user.request.RoleCreateRequest;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;

import java.util.List;

/**
 * 角色管理器
 *
 * @author dango.yxm
 * @version : RoleManager.java 2018/11/19 下午3:46 dango.yxm
 */
public interface RoleManager {


    /**
     * 创建角色
     *
     * @param request
     * @return
     */
    RoleBO createRole(RoleCreateRequest request);


    /**
     * 给角色批量绑定权限
     *
     * @param roleId
     * @param perms
     */
    void batchBindPerm(String roleId, List<String> perms);
}

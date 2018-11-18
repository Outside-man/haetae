/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.user.model.basic.perm.RoleBO;

import java.util.List;

/**
 * 角色仓储服务
 *
 * @author dango.yxm
 * @version : RoleRepoService.java 2018/11/16 下午9:49 dango.yxm
 */
public interface RoleRepoService {

    /**
     * 新增角色
     *
     * @param roleBO
     * @return
     */
    RoleBO createRole(RoleBO roleBO);

    /**
     * 通过roleIds 查询角色信息
     *
     * @param roleIds
     * @return
     */
    List<RoleBO> queryRolesByRoleIds(List<String> roleIds);

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    List<RoleBO> queryRolesByUserId(String userId);

    /**
     * 给用户绑定角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    void userBindRoles(String userId, List<String> roleIds);
}

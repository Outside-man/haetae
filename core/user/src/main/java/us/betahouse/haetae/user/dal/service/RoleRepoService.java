/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.model.RoleBO;
import us.betahouse.haetae.user.model.UserRoleRelationBO;

import java.util.List;

/**
 * 角色仓储服务
 * @author dango.yxm
 * @version : RoleRepoService.java 2018/11/16 下午9:49 dango.yxm
 */
public interface RoleRepoService {

    /**
     * 新增角色
     * @param roleBO
     * @return
     */
    RoleBO createRole(RoleBO roleBO);

    /**
     * 查询用户角色
     * @param userId
     * @return
     */
    List<RoleBO> queryRoleByUserId(String userId);

    /**
     * 给用户绑定角色
     * @param userId
     * @param roleIds
     * @return
     */
    List<UserRoleRelationBO> userBindRoles(String userId, List<String> roleIds);

}

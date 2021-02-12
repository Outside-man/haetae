/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.enums.RoleCode;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;

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
    List<RoleBO> userBindRoles(String userId, List<String> roleIds);

    /**
     * 给用户绑定角色
     *
     * @param userId
     * @param roleCode
     * @return
     */
    RoleBO userBindRolesByCode(String userId, RoleCode roleCode);

    /**
     * 给用户解除绑定角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    void userUnbindRoles(String userId, List<String> roleIds);

    /**
     * 给用户绑定角色
     *
     * @param userIds
     * @param roleId
     * @return
     */
    List<UserBO> usersBindRole(List<String> userIds, String roleId);

    /**
     * 给用户解除绑定角色
     *
     * @param userIds
     * @param roleId
     * @return
     */
    void usersUnbindRole(List<String> userIds, String roleId);

    /**
     * 解除角色和所有用户的绑定
     *
     * @param roleId
     */
    void detachAllUser(String roleId);

    /**
     * 初始化角色
     *
     * @param roleBO
     * @return
     */
    RoleBO initRole(RoleBO roleBO);

    /**
     * 获取所有的角色
     *
     * @return
     */
    List<RoleBO> findAllRole();
}

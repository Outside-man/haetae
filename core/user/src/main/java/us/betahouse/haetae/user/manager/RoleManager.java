/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager;

import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.request.RoleManageRequest;

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
    RoleBO createRole(RoleManageRequest request);

    /**
     * 给角色批量绑定权限
     *
     * @param request
     */
    void batchRoleBindPerms(RoleManageRequest request);

    /**
     * 给角色解除权限
     *
     * @param request
     */
    void batchRoleUnbindPerms(RoleManageRequest request);

    /**
     * 批量用户绑定角色
     *
     * @param request
     */
    void batchUsersBindRole(RoleManageRequest request);

    /**
     * 批量用户解绑角色
     *
     * @param request
     */
    void batchUsersUnbindRole(RoleManageRequest request);

    /**
     * 获取所有角色信息
     *
     * @return
     */
    List<RoleBO> findAllRole();
}

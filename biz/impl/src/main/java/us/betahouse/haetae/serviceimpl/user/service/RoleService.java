/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service;

import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.request.RoleRequest;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserRoleRelationBO;
import us.betahouse.haetae.user.request.RoleManageRequest;

import java.util.List;

/**
 * 角色服务
 *
 * @author dango.yxm
 * @version : RoleService.java 2018/11/23 10:30 AM dango.yxm
 */
public interface RoleService {


    /**
     * 获取所有用户角色
     *
     * @return
     */
    List<RoleBO> findAllRole();

    /**
     * 创建角色
     *
     * @param request
     * @param context
     * @return
     */
    RoleBO createRole(RoleManageRequest request, OperateContext context);

    /**
     * 绑定用户
     *
     * @param request
     * @param context
     * @return
     */
    void bindUsers(RoleRequest request, OperateContext context);

    /**
     * 解绑用户
     *
     * @param request
     * @param context
     * @return
     */
    void unbindUsers(RoleRequest request, OperateContext context);


    /**
     * 获取所有用户以及所有用户的角色
     * @return
     */
    List<UserRoleRelationBO> findAllUserRelationRole();

}

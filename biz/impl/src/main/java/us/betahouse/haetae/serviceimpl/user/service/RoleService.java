/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service;

import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.request.RoleManageRequest;

/**
 * 角色服务
 *
 * @author dango.yxm
 * @version : RoleService.java 2018/11/23 10:30 AM dango.yxm
 */
public interface RoleService {

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
    void bindUsers(RoleManageRequest request, OperateContext context);

    /**
     * 解绑用户
     *
     * @param request
     * @param context
     * @return
     */
    void unbindUsers(RoleManageRequest request, OperateContext context);

}

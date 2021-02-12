/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.user.service;

import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.request.RoleUserPermRequest;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;

import java.util.List;

/**
 * 权限服务
 *
 * @author guofan.cp
 * @version : PermService.java 2019/08/23 9:34 guofan.cp
 */
public interface PermService {

    /**
     * 创建权限
     *
     * @param request
     * @return
     */
    PermBO createPerm(RoleUserPermRequest request, OperateContext context);

    /**
     * 批量用户绑定权限
     *
     * @param request
     */
    List<UserInfoBO> batchUsersBindPerms(RoleUserPermRequest request, OperateContext context);

    /**
     * 批量用户解绑权限
     *
     * @param request
     */
    void batchUsersUnbindPerms(RoleUserPermRequest request, OperateContext context);

    /**
     * 和所有用户解绑
     *
     * @param request
     * @param context
     */
    void detachAllUsers(RoleUserPermRequest request, OperateContext context);

    /**
     * 获取权限的用户id 并按照创建时间顺序排序
     *
     * @param request
     * @param context
     * @return
     */
    List<UserInfoBO> getPermUsers(RoleUserPermRequest request, OperateContext context);


    /**
     * 获取所有权限排除 活动盖章和财务管理权限
     *
     * @return
     */
    List<PermBO> findAllNotContainStamperAndFinance();
}

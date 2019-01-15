/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.service.RoleService;
import us.betahouse.haetae.user.manager.RoleManager;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.request.RoleManageRequest;

/**
 * 角色服务
 *
 * @author dango.yxm
 * @version : RoleServiceImpl.java 2018/11/23 10:33 AM dango.yxm
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleManager roleManager;

    @Override
    public RoleBO createRole(RoleManageRequest request, OperateContext context) {
        return roleManager.createRole(request);
    }

    @Override
    public void bindUsers(RoleManageRequest request, OperateContext context) {
        roleManager.batchUsersBindRole(request);
    }

    @Override
    public void unbindUsers(RoleManageRequest request, OperateContext context) {
        roleManager.batchUsersUnbindRole(request);
    }
}

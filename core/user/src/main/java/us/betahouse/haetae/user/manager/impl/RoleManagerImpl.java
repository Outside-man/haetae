/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.manager.RoleManager;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.request.RoleManageRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;


/**
 * 角色管理器实现
 *
 * @author dango.yxm
 * @version : RoleManagerImpl.java 2018/11/19 下午3:59 dango.yxm
 */
@Service
public class RoleManagerImpl implements RoleManager {

    @Autowired
    private RoleRepoService roleRepoService;

    @Autowired
    private PermRepoService permRepoService;

    @Override
    @Transactional
    public RoleBO createRole(RoleManageRequest request) {
        // 创建角色
        RoleBO roleBO = roleRepoService.createRole(request.getRole());

        // 绑定权限
        if (!CollectionUtils.isEmpty(request.getPermIds())) {
            permRepoService.roleBindPerms(roleBO.getRoleId(), request.getPermIds());
        }

        // 绑定用户
        if (!CollectionUtils.isEmpty(request.getUserIds())) {
            roleRepoService.usersBindRole(request.getUserIds(), roleBO.getRoleId());
        }
        return roleBO;
    }

    @Override
    public void batchRoleBindPerms(RoleManageRequest request) {
        AssertUtil.assertNotNull(request.getRole());
        AssertUtil.assertStringNotBlank(request.getRole().getRoleId());
        AssertUtil.assertNotNull(request.getPermIds());
        permRepoService.roleBindPerms(request.getRole().getRoleId(), request.getPermIds());
    }

    @Override
    public void batchRoleUnbindPerms(RoleManageRequest request) {
        AssertUtil.assertNotNull(request.getRole());
        AssertUtil.assertStringNotBlank(request.getRole().getRoleId());
        AssertUtil.assertNotNull(request.getPermIds());
        permRepoService.roleUnbindPerms(request.getRole().getRoleId(), request.getPermIds());
    }

    @Override
    public void batchUsersBindRole(RoleManageRequest request) {
        AssertUtil.assertNotNull(request.getRole());
        AssertUtil.assertStringNotBlank(request.getRole().getRoleId());
        AssertUtil.assertNotNull(request.getUserIds());
        roleRepoService.usersBindRole(request.getUserIds(), request.getRole().getRoleId());
    }

    @Override
    public void batchUsersUnbindRole(RoleManageRequest request) {
        AssertUtil.assertNotNull(request.getRole());
        AssertUtil.assertStringNotBlank(request.getRole().getRoleId());
        AssertUtil.assertNotNull(request.getUserIds());
        roleRepoService.usersUnbindRole(request.getUserIds(), request.getRole().getRoleId());
    }

    @Override
    public List<RoleBO> findAllRole() {
        return roleRepoService.findAllRole();
    }
}

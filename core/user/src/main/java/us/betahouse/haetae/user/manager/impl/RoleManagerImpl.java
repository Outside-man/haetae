/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.manager.RoleManager;
import us.betahouse.haetae.user.request.RoleCreateRequest;
import us.betahouse.haetae.user.user.model.basic.perm.RoleBO;
import utils.CollectionUtils;

import java.util.List;

/**
 * 角色管理器实现
 *
 * @author dango.yxm
 * @version : RoleManagerImpl.java 2018/11/19 下午3:59 dango.yxm
 */
public class RoleManagerImpl implements RoleManager {

    @Autowired
    private RoleRepoService roleRepoService;

    @Autowired
    private PermRepoService permRepoService;

    @Override
    @Transactional
    public RoleBO createRole(RoleCreateRequest request) {
        // 创建角色
        RoleBO roleBO = roleRepoService.createRole(request.getRole());

        // 建立空角色就直接返回了
        if (CollectionUtils.isEmpty(request.getPermIds())) {
            return roleBO;
        }

        permRepoService.roleBindPerms(roleBO.getRoleId(), request.getPermIds());
        return roleBO;
    }

    @Override
    public void batchBindPerm(String roleId, List<String> perms) {
        permRepoService.roleBindPerms(roleId, perms);
    }
}

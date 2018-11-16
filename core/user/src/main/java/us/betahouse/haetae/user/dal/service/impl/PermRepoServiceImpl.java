/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.user.dal.repo.perm.PermDORepo;
import us.betahouse.haetae.user.dal.repo.perm.RolePermRelationDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserPermRelationDORepo;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.model.PermBO;
import us.betahouse.haetae.user.model.RolePermRelationBO;
import us.betahouse.haetae.user.model.UserPermRelationBO;

import java.util.List;

/**
 * 权限仓储服务实现
 *
 * @author dango.yxm
 * @version : PermRepoServiceImpl.java 2018/11/16 下午11:23 dango.yxm
 */
public class PermRepoServiceImpl implements PermRepoService {

    @Autowired
    private PermDORepo permDORepo;

    @Autowired
    private UserPermRelationDORepo userPermRelationDORepo;

    @Autowired
    private RolePermRelationDORepo rolePermRelationDORepo;


    @Override
    public PermBO createPerm(PermBO permBO) {
        return null;
    }

    @Override
    public List<PermBO> queryPermByRoleId(String roleId) {
        return null;
    }

    @Override
    public List<PermBO> queryPermByUserId(String userId) {
        return null;
    }

    @Override
    public List<RolePermRelationBO> roleBindPerms(String roleId, List<String> permIds) {
        return null;
    }

    @Override
    public List<UserPermRelationBO> userBindPerms(String userId, List<String> permIds) {
        return null;
    }
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo.perm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.perm.RolePermRelationDO;

import java.util.List;

/**
 * 角色权限映射仓储
 *
 * @author dango.yxm
 * @version : RolePermRelationDORepo.java 2018/11/16 下午7:13 dango.yxm
 */
@Repository
public interface RolePermRelationDORepo extends JpaRepository<RolePermRelationDO, Long> {

    /**
     * 通过角色获取权限映射
     *
     * @param roleId
     * @return
     */
    List<RolePermRelationDO> findAllByRoleId(String roleId);

    /**
     * 通过角色获取权限映射
     *
     * @param roleIds
     * @return
     */
    List<RolePermRelationDO> findAllByRoleIdIn(List<String> roleIds);

    /**
     * 批量解除角色上的权限
     *
     * @param role
     * @param permIds
     */
    void deleteAllByRoleIdAndPermIdIn(String role, List<String> permIds);

    /**
     * 解除权限的全部角色上的绑定
     *
     * @param permId
     */
    void deleteAllByPermId(String permId);

    /**
     * 获取角色对应的权限
     *
     * @param roleId
     * @param permId
     * @return
     */
    RolePermRelationDO findByRoleIdAndPermId(String roleId, String permId);
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo.perm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.perm.RoleDO;

import java.util.List;

/**
 * 角色仓储
 *
 * @author dango.yxm
 * @version : RoleDORepo.java 2018/11/16 下午7:05 dango.yxm
 */
@Repository
public interface RoleDORepo extends JpaRepository<RoleDO, String> {

    /**
     * 获取角色实体
     *
     * @param roleId
     * @return
     */
    RoleDO findByRoleId(String roleId);

    /**
     * 批量获取角色实体
     *
     * @param roleIds
     * @return
     */
    List<RoleDO> findAllByRoleIdIn(List<String> roleIds);

    /**
     * 通过角色号获取角色
     *
     * @param roleCode
     * @return
     */
    RoleDO findByRoleCode(String roleCode);
}
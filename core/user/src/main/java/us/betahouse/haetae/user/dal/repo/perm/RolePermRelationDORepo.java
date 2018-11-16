/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo.perm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.perm.RolePermRelationDO;

/**
 * 角色权限映射仓储
 *
 * @author dango.yxm
 * @version : RolePermRelationDORepo.java 2018/11/16 下午7:13 dango.yxm
 */
@Repository
public interface RolePermRelationDORepo extends JpaRepository<RolePermRelationDO, Long> {
}

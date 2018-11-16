/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo.perm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.perm.UserRoleRelationDO;

import java.util.List;

/**
 * 用户角色关系仓储
 *
 * @author dango.yxm
 * @version : UserRoleRelationDORepo.java 2018/11/16 下午7:08 dango.yxm
 */
@Repository
public interface UserRoleRelationDORepo extends JpaRepository<UserRoleRelationDO, Long> {

    /**
     * 查询用户的所有角色
     *
     * @param userId
     * @return
     */
    List<UserRoleRelationDO> findAllByUserId(String userId);
}

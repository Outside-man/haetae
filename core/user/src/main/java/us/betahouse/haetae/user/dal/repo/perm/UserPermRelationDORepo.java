/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo.perm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.perm.UserPermRelationDO;

import java.util.List;

/**
 * 用户权限映射仓储
 *
 * @author dango.yxm
 * @version : UserPermRelationDORepo.java 2018/11/16 下午7:12 dango.yxm
 */
@Repository
public interface UserPermRelationDORepo extends JpaRepository<UserPermRelationDO, Long> {

    /**
     * 通过用户id获取映射关系
     *
     * @param userId
     * @return
     */
    List<UserPermRelationDO> findAllByUserId(String userId);
}

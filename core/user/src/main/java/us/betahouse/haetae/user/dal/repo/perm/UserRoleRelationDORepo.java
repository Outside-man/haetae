/*
  betahouse.us
  CopyRight (c) 2012 - 2018
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


    /**
     * 解绑用户下的角色
     *
     * @param userId
     * @param roleIds
     */
    void deleteAllByUserIdAndRoleIdIn(String userId, List<String> roleIds);

    /**
     * 查询角色下的所有用户
     *
     * @param roleId
     * @return
     */
    List<UserRoleRelationDO> findAllByRoleId(String roleId);

    /**
     * 批量解绑用户
     *
     * @param roleId
     * @param userIds
     */
    void deleteAllByRoleIdAndUserIdIn(String roleId, List<String> userIds);

    /**
     * 删除所有用户和权限的关联
     *
     * @param roleId
     */
    void deleteAllByRoleId(String roleId);
}

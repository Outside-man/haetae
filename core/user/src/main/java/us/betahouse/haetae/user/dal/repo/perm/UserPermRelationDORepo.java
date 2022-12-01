/*
  betahouse.us
  CopyRight (c) 2012 - 2018
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

    /**
     * 解除用户权限关联
     *
     * @param userId
     * @param permIds
     */
    void deleteByUserIdAndPermIdIn(String userId, List<String> permIds);

    /**
     * 解除和所有用户绑定
     *
     * @param permId
     */
    void deleteByPermId(String permId);

    /**
     * 获取所有权限绑定 并按照创建顺序排列
     *
     * @param permId
     * @return
     */
    List<UserPermRelationDO> findAllByPermIdOrderByGmtCreate(String permId);

    /**
     * 批量权限解除用户id
     *
     * @param permId
     * @param userIds
     */
    void deleteByPermIdAndUserIdIn(String permId, List<String> userIds);


    /**
     * 获取用户权限关系
     *
     * @param userId
     * @param permId
     * @return
     */
    UserPermRelationDO findByUserIdAndPermId(String userId, String permId);
}

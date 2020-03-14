/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.UserInfoDO;

import java.util.List;

/**
 * 用户信息仓储
 *
 * @author dango.yxm
 * @version : UserInfoDORepo.java 2018/11/16 下午7:15 dango.yxm
 */
@Repository
public interface UserInfoDORepo extends JpaRepository<UserInfoDO, Long> {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfoDO findByUserId(String userId);

    /**
     * 通过过学号获取用户信息实体
     *
     * @param stuId
     * @return
     */
    UserInfoDO findByStuId(String stuId);

    /**
     * 批量获取用户信息
     *
     * @param userIds
     * @return
     */
    List<UserInfoDO> findAllByUserIdIn(List<String> userIds);


}

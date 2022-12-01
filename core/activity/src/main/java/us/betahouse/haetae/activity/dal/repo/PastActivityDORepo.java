/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.activity.dal.model.PastActivityDO;

/**
 * @author MessiahJK
 * @version : PastActivityDORepo.java 2019/04/26 10:54 MessiahJK
 */
public interface PastActivityDORepo extends JpaRepository<PastActivityDO,Long> {

    /**
     * 通过以往活动id查询以往活动
     *
     * @param pastActivityId
     * @return
     */
    PastActivityDO findByPastActivityId(String pastActivityId);

    /**
     * 通过用户id查询以往活动
     *
     * @param userId
     * @return
     */
    PastActivityDO findByUserId(String userId);

    /**
     * 通过学号来查询以往活动
     *
     * @param stuId
     * @return
     */
    PastActivityDO findByStuId(String stuId);
}

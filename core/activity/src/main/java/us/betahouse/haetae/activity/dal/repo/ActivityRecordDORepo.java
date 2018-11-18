/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;

import java.util.List;

/**
 *
 * @author MessiahJK
 * @version : ActivityRecordDORepo.java 2018/11/17 15:44 MessiahJK
 */
@Repository
public interface  ActivityRecordDORepo extends JpaRepository<ActivityRecordDO,Long> {
    /**
     * 通过用户id获取活动记录
     *
     * @param userId
     * @return
     */
    List<ActivityRecordDO> findByUserId(String userId);

    /**
     * 通过用户id和活动类型获取活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    List<ActivityRecordDO> findByUserIdAndType(String userId,String type);

    /**
     * 通过活动id获取活动记录数
     *
     * @param activityId
     * @return
     */
    Long countAllByActivityIdEquals(String activityId);
}

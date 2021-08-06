/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 活动记录仓储
 *
 * @author MessiahJK
 * @version : ActivityRecordDORepo.java 2018/11/17 15:44 MessiahJK
 */
@Repository
public interface ActivityRecordDORepo extends JpaRepository<ActivityRecordDO, Long> {
    
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    void deleteAllByActivityIdAndUserId(String activityId, String userId);
    
    /**
     * 通过用户id获取活动记录
     *
     * @param userId
     * @return
     */
    List<ActivityRecordDO> findByUserId(String userId);
    
    List<ActivityRecordDO> findAllByActivityId(String activityId);

    /**
     * 通过用户id和活动类型获取活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    List<ActivityRecordDO> findByUserIdAndType(String userId, String type);

    /**
     * 通过活动id获取活动记录数
     *
     * @param activityId
     * @return
     */
    Long countAllByActivityIdEquals(String activityId);


    /**
     * 根据用户 活动类型 学期获取记录
     *
     * @param userId
     * @param types
     * @param terms
     * @return
     */
    List<ActivityRecordDO> findAllByUserIdAndTypeInAndTermIn(String userId, List<String> types, List<String> terms);

    /**
     * 获取 用户的活动 记录
     *
     * @param activity
     * @param userIds
     * @return
     */
    List<ActivityRecordDO> findAllByActivityIdAndUserIdIn(String activity, List<String> userIds);

    /**
     * 获取用户 指定学期的所有活动记录
     *
     * @param userId
     * @param term
     * @return
     */
    List<ActivityRecordDO> findAllByUserIdAndTerm(String userId, String term);

    /**
     * 通过活动记录id查询活动记录
     *
     * @param activityRecordId
     * @return
     */
    ActivityRecordDO findByActivityRecordId(String activityRecordId);

    int countAllByUserIdAndType(String userId, String type);

    @Query(value = "SELECT sum( 1 ), sum( activity_record.time ),activity_record.user_id,activity_record.type FROM activity_record GROUP BY activity_record.user_id,activity_record.type", nativeQuery = true)
    List<Object[]>  findGroupByActivityTypeAndUserId();
}

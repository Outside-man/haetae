/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service;

import us.betahouse.haetae.activity.model.ActivityRecordBO;

import java.util.List;

/**
 * @author MessiahJK
 * @version : ActivityRecordRepoService.java 2018/11/17 20:00 MessiahJK
 */
public interface ActivityRecordRepoService {
    /**
     * 新增活动记录
     *
     * @param activityRecordBO
     * @return
     */
    ActivityRecordBO createActivityRecord(ActivityRecordBO activityRecordBO);

    /**
     * 通过用户id查询活动记录
     *
     * @param userId
     * @return
     * */
    List<ActivityRecordBO> queryActivityRecordByUserId(String userId);

    /**
     * 通过用户id和活动类型查询活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    List<ActivityRecordBO> queryActivityRecordByUserIdAndType(String userId,String type);

    /**
     * 通过活动id统计活动记录数
     *
     * @param activityId
     * @return
     */
    Long countActivityRecordByActivityId(String activityId);
}

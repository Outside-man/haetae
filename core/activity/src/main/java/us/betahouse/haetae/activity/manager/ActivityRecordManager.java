/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager;

import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.activity.request.ActivityRecordRequest;

import java.util.List;

/**
 * @author MessiahJK
 * @version : ActivityRecordManager.java 2018/11/22 23:42 MessiahJK
 */
public interface ActivityRecordManager {
    /**
     * 创建活动记录
     *
     * @param request
     * @return
     */
    ActivityRecordBO create(ActivityRecordRequest request);

    /**
     * 通过用户id查询活动记录
     *
     * @param userId
     * @return
     */
    List<ActivityRecordBO> findByUserId(String  userId);

    /**
     * 通过用户id和类型查找活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    List<ActivityRecordBO> findByUserIdAndType(String userId,String type);

    /**
     * 通过活动id统计活动记录条数
     *
     * @param activityId
     * @return
     */
    Long countByActivityId(String activityId);
}

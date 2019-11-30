/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager;

import us.betahouse.haetae.activity.model.basic.ActivityRecordBO;
import us.betahouse.haetae.activity.request.ActivityRecordRequest;

import java.util.List;

/**
 * 活动记录管理器
 *
 * @author MessiahJK
 * @version : ActivityRecordManager.java 2018/11/22 23:42 MessiahJK
 */
public interface ActivityRecordManager {

    /**
     * 通过用户id查询活动记录
     *
     * @param userId
     * @return
     */
    List<ActivityRecordBO> findByUserId(String userId);

    /**
     * 通过用户id查询活动记录
     *
     * @param userId
     * @param term
     * @return
     */
    List<ActivityRecordBO> findByUserIdAndTerm(String userId, String term);

    /**
     * 通过用户id和类型查找活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    List<ActivityRecordBO> findByUserIdAndType(String userId, String type);

    /**
     * 通过活动id统计活动记录条数
     *
     * @param activityId
     * @return
     */
    Long countByActivityId(String activityId);


    /**
     * 批量创建活动记录
     *
     * @param request
     * @param userIds
     * @return
     */
    List<ActivityRecordBO> batchCreate(ActivityRecordRequest request, List<String> userIds);


    /**
     * 通过用户id查询活动记录
     *
     * @param userId
     * @return
     */
    List<ActivityRecordBO> fetchUserActivityRecord(String userId, String activityType, String term);

    /**
     * 更新扫码员姓名
     *
     * @param activityRecordId
     * @return
     */
    ActivityRecordBO updateScannerName(String activityRecordId,String scannerName);

    List<ActivityRecordBO> findAll();
}

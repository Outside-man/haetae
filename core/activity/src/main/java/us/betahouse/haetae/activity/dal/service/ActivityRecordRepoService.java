/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service;

import us.betahouse.haetae.activity.model.basic.ActivityRecordBO;

import java.util.List;

/**
 * 活动记录仓储服务
 *
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
     */
    List<ActivityRecordBO> queryActivityRecordByUserId(String userId);

    /**
     * 通过用户id和学期查询活动记录
     *
     * @param userId
     * @return
     */
    List<ActivityRecordBO> queryActivityRecordByUserIdAndTerm(String userId, String term);

    /**
     * 通过用户id和活动类型查询活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    List<ActivityRecordBO> queryActivityRecordByUserIdAndType(String userId, String type);

    /**
     * 通过活动id统计活动记录数
     *
     * @param activityId
     * @return
     */
    Long countActivityRecordByActivityId(String activityId);

    /**
     * 批量创建活动记录
     *
     * @param activityRecordBOs
     * @return
     */
    List<ActivityRecordBO> batchCreateActivityRecord(List<ActivityRecordBO> activityRecordBOs);


    /**
     * 查询用户活动记录
     *
     * @param userId
     * @param activityTypes
     * @param terms
     * @return
     */
    List<ActivityRecordBO> queryUserTermActivityRecord(String userId, List<String> activityTypes, List<String> terms);

    /**
     * 解析参加过的用户记录
     *
     * @param activityId
     * @param userIds
     * @return
     */
    List<ActivityRecordBO> parseJoinUserIds(String activityId, List<String> userIds);

    /**
     * 更新扫码员姓名
     *
     * @param activityRecordId
     * @param scannerName
     * @return
     */
    ActivityRecordBO updateScannerName(String activityRecordId,String scannerName);

    /**
     * 查询所有记录
     *
     * @return
     */
    List<ActivityRecordBO> findAll();
}

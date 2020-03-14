package us.betahouse.haetae.activity.dal.service;

import us.betahouse.haetae.activity.dal.model.ActivityEntryDO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;

import java.util.List;

/**
 * 活动报名记录仓储服务
 *
 * @author zjb
 * @version : ActivityEntryRecordRepoService.java 2019/7/7 15:43 zjb
 */
public interface ActivityEntryRecordRepoService {

    /**
     * 通过报名信息id查找报名记录
     *
     * @param activityEntryId
     * @return
     */
    List<ActivityEntryRecordBO> findAllByActivityEntryId(String activityEntryId);

    /**
     * 通过报名信息id和用户id获取报名信息
     * @param activityEntryId
     * @param userId
     * @return
     */
    ActivityEntryRecordBO findByActivityEntryIdAndUserId(String activityEntryId, String userId);

    /**
     * 更新报名记录
     * @param activityEntryRecordBO
     * @return
     */
    ActivityEntryRecordBO updateActivityEntryRecord(ActivityEntryRecordBO activityEntryRecordBO);

    /**
     * 通过用户id查找报名记录
     *
     * @param userId
     * @return
     */
    List<ActivityEntryRecordBO> findAllByUserId(String userId);

    /**
     * 通过报名信息id查找报名记录数量
     * @param activityEntryId
     * @return
     */
    Long countByActivityEntryIdAndState(String activityEntryId,String state);


    /**
     * 新增报名记录
     *
     * @param activityEntryRecordBO
     * @return
     */
    ActivityEntryRecordBO createActivityEntryRecord(ActivityEntryRecordBO activityEntryRecordBO);

    /**
     * 标记出席
     *
     * @param activityId
     * @return
     */
    List<ActivityEntryRecordBO> attend(String userId,String activityId);

}

package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.haetae.activity.request.ActivityEntryRecordRequest;
import us.betahouse.haetae.activity.request.ActivityEntryRequest;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.user.model.basic.UserInfoBO;

import java.util.List;

/**
 *
 * 活动报名信息服务
 *
 * @author zjb
 * @version : ActivityEntryService.java 2019/7/7 17:04 zjb
 */
public interface ActivityEntryService {

    /**
     * 通过活动id查找报名信息
     *
     * @param activityId
     * @return
     */
    ActivityEntryList activityEntryQueryByActivityId(String activityId);

//    /**
//     * 通过报名信息id查找报名记录,返回Excel文件地址
//     *
//     * @param activityEntryId
//     * @return
//     */
//    String getActivityEntryRecordFileByActivityEntryId(String activityEntryId);

    /**
     * 通过报名信息id查找报名记录,返回Excel文件地址
     *
     * @param activityEntryId
     * @return
     */
    List<UserInfoBO> getActivityEntryRecordUserInfoFileByActivityEntryId(String activityEntryId);

    /**
     * 获取报名信息title
     * @param activityEntryId
     * @return
     */
    String getActivityEntryTitle(String activityEntryId);

    /**
     * 获取报名信息
     * @param request
     * @param userID
     * @return
     */
    ActivityEntryList activityEntryQuery(ActivityEntryRequest request, String userID);

    /**
     * 获取已报名状态的报名信息
     * @param request
     * @param userID
     * @return
     */
    ActivityEntryList registeredActivityEntryQuery(ActivityEntryRequest request, String userID);

    /**
     * 创建报名信息
     *
     * @param request
     * @return
     */
    ActivityEntryBO createActivityEntry(ActivityEntryRequest request);

    /**
     * 更新报名信息
     * @param request
     * @return
     */
    ActivityEntryBO updateActivityEntry(ActivityEntryRequest request);

    /**
     * 活动报名
     *
     * @param request
     * @return
     */
    ActivityEntryRecordBO createActivityEntryRecord(ActivityEntryRecordRequest request);

    /**
     * 取消报名
     * @param request
     * @return
     */
    ActivityEntryRecordBO undoSignUp(ActivityEntryRecordRequest request);

    /**
     * 结束可以结束的报名
     *
     * @return
     */
    List<ActivityEntryBO> systemFinishActivityEntry();
}

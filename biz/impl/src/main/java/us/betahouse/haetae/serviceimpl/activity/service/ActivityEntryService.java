package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityEntryQueryRequest;

/**
 *
 * 活动报名信息服务
 *
 * @author zjb
 * @version : ActivityEntryService.java 2019/7/7 17:04 zjb
 */
public interface ActivityEntryService {

    /**
     * 获取报名信息
     * @param request
     * @param userID
     * @return
     */
    ActivityEntryList activityEntryQuery(ActivityEntryQueryRequest request, String userID);

    /**
     * 获取已报名状态的报名信息
     * @param request
     * @param userID
     * @return
     */
    ActivityEntryList registeredActivityEntryQuery(ActivityEntryQueryRequest request, String userID);
}

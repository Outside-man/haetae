/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.operate;

import us.betahouse.haetae.activity.model.basic.ActivityBO;

/**
 * 活动操作
 *
 * @author dango.yxm
 * @version : ActivityOperate.java 2018/11/26 2:27 PM dango.yxm
 */
public interface ActivityOperate {

    /**
     * 活动操作
     *
     * @param request
     * @return
     */
    ActivityBO doOperate(ActivityOperationRequest request);

    /**
     * 活动操作
     *
     * @param activityId
     * @param userId
     * @return
     */
    ActivityBO operate(String activityId, String userId);

    /**
     * 获取操作信息
     *
     * @return
     */
    String getOperate();
}

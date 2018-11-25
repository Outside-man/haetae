/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * 活动记录服务
 *
 * @author MessiahJK
 * @version : ActivityRecordService.java 2018/11/22 20:31 MessiahJK
 */
public interface ActivityRecordService {

    /**
     * 盖章
     *
     * @param request
     * @param context
     * @return
     */
    ActivityRecordBO stamp(ActivityStampRequest request, OperateContext context);

    /**
     * 通过用户id查询活动记录
     *
     * @param request
     * @param context
     * @return
     */
    List<ActivityRecordBO> findByUserId(ActivityStampRequest request, OperateContext context);

    /**
     * 通过活动id统计活动记录条数
     *
     * @param request
     * @param context
     * @return
     */
    Long countByActivityId(ActivityStampRequest request, OperateContext context);

    /**
     * 批量盖章
     *
     * @param request
     * @param context
     * @return
     */
    List<ActivityRecordBO> batchStamp(ActivityStampRequest request, OperateContext context);

    /**
     * 获取用户活动章
     *
     * @param request
     * @param context
     * @return
     */
    List<ActivityStamp> getUserStamps(ActivityStampRequest request, OperateContext context);
}

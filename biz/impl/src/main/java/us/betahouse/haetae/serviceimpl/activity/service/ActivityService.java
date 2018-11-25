/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * @author MessiahJK
 * @version : ActivityService.java 2018/11/22 19:53 MessiahJK
 */
public interface ActivityService {

    /**
     * 创建活动
     *
     * @param request
     * @param context
     * @return
     */
    ActivityBO create(ActivityManagerRequest request, OperateContext context);

    /**
     * 查找所有活动
     *
     * @param context
     * @return
     */
    List<ActivityBO> findAll(OperateContext context);

    /**
     * 更新活动
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO update(ActivityManagerRequest request,OperateContext operateContext);

    /**
     * 改变活动状态
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO changeStatus(ActivityManagerRequest request,OperateContext operateContext);

    /**
     * 活动通过
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO pass(ActivityManagerRequest request,OperateContext operateContext);

    /**
     * 活动发布
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO publish(ActivityManagerRequest request,OperateContext operateContext);

    /**
     * 活动结束
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO finish(ActivityManagerRequest request,OperateContext operateContext);

    /**
     * 活动重新拉起
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO republish(ActivityManagerRequest request,OperateContext operateContext);

    /**
     * 活动取消
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO remove(ActivityManagerRequest request,OperateContext operateContext);
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.PastActivityBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.model.basic.UserInfoBO;

import java.text.ParseException;
import java.util.List;

/**
 * 活动业务服务
 *
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
     * 查找活动
     *
     * @param request
     * @param context
     * @return
     */
    PageList<ActivityBO> findAll(ActivityManagerRequest request, OperateContext context);



    /**
     * 更新活动
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO update(ActivityManagerRequest request, OperateContext operateContext);
    /**
     * 审批通过
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO publish(ActivityManagerRequest request, OperateContext operateContext);
    /**
     * 驳回申请
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO cancel(ActivityManagerRequest request, OperateContext operateContext);

    /**
     * 操作活动
     *
     * @param request
     * @param operateContext
     * @return
     */
    ActivityBO operate(ActivityManagerRequest request, OperateContext operateContext);

    /**
     * 活动添加盖章员
     *
     * @param request
     * @param context
     */
    void bindStamper(ActivityManagerRequest request, OperateContext context);

    /**
     * 获取盖章员信息
     *
     * @param request
     * @param context
     * @return
     */
    List<UserInfoBO> getStampers(ActivityManagerRequest request, OperateContext context);

    /**
     * 活动去除盖章员
     *
     * @param request
     * @param context
     */
    void unbindStamper(ActivityManagerRequest request, OperateContext context);

    /**
     * 结束可以结束的活动
     *
     * @return
     */
    List<ActivityBO> systemFinishActivity();

    /**
     * 初始化以往活动记录
     */
    void initPastActivity();

    /**
     * 获取以往活动记录
     *
     * @param request
     * @param context
     * @return
     */
    PastActivityBO getPastActivity(ActivityManagerRequest request, OperateContext context);

    /**
     * 分配以往活动记录
     *
     * @param request
     * @param context
     */
    void  assignPastRecord(ActivityManagerRequest request,OperateContext context);

    /**
     * 完善活动扫章时间起止日期
     *
     * @param activityBOS
     * @return
     */
    List<ActivityBO> fillActivityStampedStartAndEndTime(List<ActivityBO> activityBOS);

    /**
     * 完善活动的创建者的stuId，将其放置在(额外信息)extInfo中
     *
     * @param activityBOS
     * @return
     */
    List<ActivityBO> fillActivityCreatorStuId(List<ActivityBO> activityBOS);

    /**
     * 查找活动通过UserId
     *
     * @param request
     * @param context
     * @return
     */
    PageList<ActivityBO> findByUserId(ActivityManagerRequest request, OperateContext context);

    /**
     * 查找已审批通过的活动
     *
     * @param request
     * @param context
     * @return
     */
    PageList<ActivityBO> findApproved(ActivityManagerRequest request, OperateContext context) throws ParseException;

    /**
     * 查找本周创建的活动
     * @param request
     * @param context
     * @return
     */
    PageList<ActivityBO> findCreatedByWeek(ActivityManagerRequest request, OperateContext context);

    /**
     * 查找本周通过审批的活动
     * @param request
     * @param context
     * @return
     */
    PageList<ActivityBO> findApprovedByWeek(ActivityManagerRequest request, OperateContext context);

}

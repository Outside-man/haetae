/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager;

import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.PastActivityBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.haetae.activity.request.ActivityRequest;

import java.text.ParseException;
import java.util.List;

/**
 * 活动管理器
 *
 * @author MessiahJK
 * @version : ActivityManager.java 2018/11/22 23:42 MessiahJK
 */
public interface ActivityManager {
    /**
     * 创建活动
     *
     * @param request
     * @return
     */
    ActivityBO create(ActivityRequest request);

    /**
     * 查找所有活动
     *
     * @return
     */
    List<ActivityBO> findAll();

    /**
     * 查找所有活动 ByState
     *
     * @param state 状态
     * @return
     */
    List<ActivityBO> findByState(ActivityStateEnum state);

    /**
     * 更新活动
     *
     * @param request
     * @return
     */
    ActivityBO update(ActivityRequest request);

    /**
     * 活动审批通过
     *
     * @param request
     * @return
     */
    ActivityBO publish(ActivityRequest request);

    /**
     * 活动审批驳回
     *
     * @param request
     * @return
     */
    ActivityBO cancel(ActivityRequest request);



    /**
     * 查找活动
     *
     * @param request
     * @return
     */
    PageList<ActivityBO> find(ActivityRequest request);



    /**
     * 查询过去活动记录
     * @param request
     * @return
     */
    PastActivityBO findPast(ActivityRequest request);

    /**
     * 创建过去活动
     *
     * @param request
     * @return
     */
    PastActivityBO createPast(ActivityRequest request);

    /**
     * 创建过去活动记录
     *
     * @param pastActivityBO
     * @return
     */
    PastActivityBO createPast(PastActivityBO pastActivityBO);

    /**
     * 更新过去活动记录
     *
     * @param request
     * @return
     */
    PastActivityBO updatePast(ActivityRequest request);

    /**
     * 查找活动By UserId 分页
     *
     * @param request
     * @return
     */
    PageList<ActivityBO> findByUserId(ActivityRequest request);

    /**
     * 查找已审批通过的活动 分页
     *
     * @param request
     * @return
     */
    PageList<ActivityBO> findApproved(ActivityRequest request);

    /**
     * 查找已审批通过的活动(添加time) 分页
     *
     * @param request
     * @return
     */
    PageList<ActivityBO> findApprovedAddTime(ActivityRequest request) throws ParseException;

    /**
     * 查找本周创建的活动 分页
     * @param request
     * @return
     */
    PageList<ActivityBO> findCreatedByWeek(ActivityRequest request);

    /**
     * 查找本周审批通过的活动 分页
     * @param request
     * @return
     */
    PageList<ActivityBO> findApprovedByWeek(ActivityRequest request);
}

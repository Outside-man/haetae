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
}

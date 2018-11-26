/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager;

import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.activity.status.activitystatus.ActivityStateEnum;

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
     * 改变活动状态
     *
     * @param activityId
     * @param motion
     * @return
     */
    ActivityBO changeStatus(String activityId,String motion);

    /**
     * 检查所有活动状态
     *
     * @return
     */
    void chickAll();
}

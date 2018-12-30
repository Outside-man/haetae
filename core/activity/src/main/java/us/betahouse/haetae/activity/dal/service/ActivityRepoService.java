/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service;

import us.betahouse.haetae.activity.model.basic.ActivityBO;

import java.util.List;

/**
 * 活动仓储服务
 *
 * @author MessiahJK
 * @version : ActivityRepoService.java 2018/11/17 20:00 MessiahJK
 */
public interface ActivityRepoService {
    /**
     * 查询所有活动
     *
     * @return
     */
    List<ActivityBO> queryAllActivity();

    /**
     * 通过状态查询活动
     *
     * @return
     */
    List<ActivityBO> queryActivitiesByState(String state);

    /**
     * 通过类型查询活动
     *
     * @return
     */
    List<ActivityBO> queryActivityByType(String type);

    /**
     * 新增活动
     *
     * @param activityBO
     * @return
     */
    ActivityBO createActivity(ActivityBO activityBO);

    /**
     * 更新活动
     *
     * @param activityBO
     * @return
     */
    ActivityBO updateActivity(ActivityBO activityBO);

    /**
     * 通过活动id查询活动
     *
     * @param activityId
     * @return
     */
    ActivityBO queryActivityByActivityId(String activityId);

    /**
     * 通过活动ids查询活动
     *
     * @param activityIds
     * @return
     */
    List<ActivityBO> queryActivityByActivityIds(List<String> activityIds);

    /**
     * 通过活动名查询活动
     * @param name
     * @return
     */
    ActivityBO queryActivityByActivityName(String name);
}

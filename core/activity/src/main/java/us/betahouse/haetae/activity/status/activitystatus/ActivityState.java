/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.status.activitystatus;

/**
 * 抽象状态类
 *
 * @author MessiahJK
 * @version : ActivityState.java 2018/11/23 1:03 MessiahJK
 */
public interface ActivityState {
    /**
     * 通过活动
     *
     * @param activityStateManager
     * @return
     */
     boolean pass(ActivityStateManager activityStateManager);

    /**
     * 提交活动
     *
     * @param activityStateManager
     * @return
     */
     boolean publish(ActivityStateManager activityStateManager);

    /**
     * 结束活动
     * @param activityStateManager
     * @return
     */
     boolean finish(ActivityStateManager activityStateManager);

    /**
     * 重新拉起活动
     *
     * @param activityStateManager
     * @return
     */
     boolean republish(ActivityStateManager activityStateManager);

    /**
     * 取消活动
     *
     * @param activityStateManager
     * @return
     */
     boolean remove(ActivityStateManager activityStateManager);

    /**
     * 获取活动状态
     * @return
     */
     ActivityStateEnum getActivityState();
}

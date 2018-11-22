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
     * 通过
     *
     * @param activityStateManager
     * @return
     */
     boolean pass(ActivityStateManager activityStateManager);

     boolean publish(ActivityStateManager activityStateManager);

     boolean finish(ActivityStateManager activityStateManager);

     boolean republish(ActivityStateManager activityStateManager);

     boolean remove(ActivityStateManager activityStateManager);

     ActivityStateEnum getActivityState();
}

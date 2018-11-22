/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.status.activitystatus;

/**
 * 发布状态
 *
 * @author MessiahJK
 * @version : PublishedState.java 2018/11/23 1:28 MessiahJK
 */
public class PublishedState implements ActivityState {
    @Override
    public boolean pass(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public boolean publish(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public boolean finish(ActivityStateManager activityStateManager) {
        activityStateManager.setActivityState(new FinishedState());
        return true;
    }

    @Override
    public boolean republish(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public boolean remove(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public ActivityStateEnum getActivityState() {
        return ActivityStateEnum.PUBLISHED;
    }
}

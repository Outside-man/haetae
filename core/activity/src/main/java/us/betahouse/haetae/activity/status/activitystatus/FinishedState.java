/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.status.activitystatus;

/**
 * 结束状态
 *
 * @author MessiahJK
 * @version : FinishedState.java 2018/11/23 1:29 MessiahJK
 */
public class FinishedState implements ActivityState {
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
        return false;
    }

    @Override
    public boolean republish(ActivityStateManager activityStateManager) {
        activityStateManager.setActivityState(new RestoreState());
        return true;
    }

    @Override
    public boolean remove(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public ActivityStateEnum getActivityState() {
        return ActivityStateEnum.FINISHED;
    }
}

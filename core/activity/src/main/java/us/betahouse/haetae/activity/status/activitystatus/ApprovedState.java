/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.status.activitystatus;

/**
 * 通过状态
 *
 * @author MessiahJK
 * @version : ApprovedState.java 2018/11/23 1:26 MessiahJK
 */
public class ApprovedState implements ActivityState{
    @Override
    public boolean pass(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public boolean publish(ActivityStateManager activityStateManager) {
        activityStateManager.setActivityState(new PublishedState());
        return true;
    }

    @Override
    public boolean finish(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public boolean republish(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public boolean remove(ActivityStateManager activityStateManager) {
        activityStateManager.setActivityState(new CanceledState());
        return true;
    }

    @Override
    public ActivityStateEnum getActivityState() {
        return ActivityStateEnum.APPROVED;
    }
}

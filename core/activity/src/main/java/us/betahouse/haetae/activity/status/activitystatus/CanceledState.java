/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.status.activitystatus;

/**
 * 取消状态
 *
 * @author MessiahJK
 * @version : CanceldState.java 2018/11/23 1:31 MessiahJK
 */
public class CanceledState implements ActivityState {
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
        return false;
    }

    @Override
    public boolean remove(ActivityStateManager activityStateManager) {
        return false;
    }

    @Override
    public ActivityStateEnum getActivityState() {
        return ActivityStateEnum.CANCELED;
    }
}

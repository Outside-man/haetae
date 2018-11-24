/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.status.activitystatus;

/**
 * 活动状态机
 *
 * @author MessiahJK
 * @version : ActivityStateManager.java 2018/11/23 1:09 MessiahJK
 */
public class ActivityStateManager {
    private ActivityState activityState;

    public ActivityStateManager(ActivityState activityState){
        this.activityState=activityState;
    }

    public ActivityState getActivityState() {
        return activityState;
    }

    public void setActivityState(ActivityState activityState) {
        this.activityState = activityState;
    }

    public boolean pass(){
        return activityState.pass(this);
    }

    public boolean publish(){
        return activityState.pass(this);
    }

    public boolean finish(){
        return activityState.pass(this);
    }

    public boolean republish(){
        return activityState.pass(this);
    }

    public boolean remove(){
        return activityState.pass(this);
    }
}

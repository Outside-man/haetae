/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.util.common.ToString;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;

/**
 * 志愿活动记录
 *
 * @author dango.yxm
 * @version : VolunteerActivityStampRecord.java 2018/11/26 10:42 PM dango.yxm
 */
public class VolunteerActivityStampRecord extends ToString implements StampRecord {

    private static final long serialVersionUID = 543493486999019429L;

    /**
     * 活动章
     */
    private List<ActivityStamp> activityStamps;

    /**
     * 志愿时长
     */
    private double volunteerTotalTime = 0;


    /**
     * 计算志愿时长
     */
    public void countVolunteerTotalTime() {
        double totalTime = 0;
        if (!CollectionUtils.isEmpty(activityStamps)) {
            for (ActivityStamp activityStamp : activityStamps) {
                if (activityStamp.getActivityTime() != null) {
                    totalTime += activityStamp.getActivityTime();
                }
            }
        }
    }

    @Override
    public List<ActivityStamp> getActivityStamps() {
        return activityStamps;
    }

    public void setActivityStamps(List<ActivityStamp> activityStamps) {
        this.activityStamps = activityStamps;
    }

    public double getVolunteerTotalTime() {
        return volunteerTotalTime;
    }

    public void setVolunteerTotalTime(double volunteerTotalTime) {
        this.volunteerTotalTime = volunteerTotalTime;
    }
}

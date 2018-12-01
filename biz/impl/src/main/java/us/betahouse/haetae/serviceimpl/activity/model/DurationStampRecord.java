/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.util.utils.CollectionUtils;

import java.util.List;


/**
 * 志愿活动记录
 *
 * @author dango.yxm
 * @version : VolunteerActivityStampRecord.java 2018/11/26 10:42 PM dango.yxm
 */
public class DurationStampRecord extends StampRecord {

    private static final long serialVersionUID = 543493486999019429L;

    private final static double TEN = 10;

    /**
     * 统计时长
     */
    private String totalTime = "0.0";

    public DurationStampRecord(List<ActivityStamp> activityStamps) {
        super(activityStamps);
    }


    /**
     * 计算时长
     */
    public void countVolunteerTotalTime() {
        int time = 0;
        if (!CollectionUtils.isEmpty(getActivityStamps())) {
            for (ActivityStamp activityStamp : getActivityStamps()) {
                if (activityStamp.getTime() != null) {
                    time += activityStamp.getTime();
                }
            }
        }
        this.totalTime = String.format("%.1f", time / TEN);
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}

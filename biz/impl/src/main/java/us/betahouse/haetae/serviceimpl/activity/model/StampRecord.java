/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.model;


import us.betahouse.util.common.ToString;

import java.util.List;

/**
 * 章记录
 *
 * @author dango.yxm
 * @version : StampRecord.java 2018/11/26 10:38 PM dango.yxm
 */
public class StampRecord extends ToString {

    private static final long serialVersionUID = 4607726724817332027L;

    /**
     * 活动章
     */
    private List<ActivityStamp> activityStamps;

    public List<ActivityStamp> getActivityStamps() {
        return activityStamps;
    }

    public void setActivityStamps(List<ActivityStamp> activityStamps) {
        this.activityStamps = activityStamps;
    }

    public StampRecord(List<ActivityStamp> activityStamps) {
        this.activityStamps = activityStamps;
    }
}

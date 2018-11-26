/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.util.common.ToString;

import java.util.List;

/**
 * 校园活动章记录
 *
 * @author dango.yxm
 * @version : SchoolActivityStampRecord.java 2018/11/26 10:35 PM dango.yxm
 */
public class SchoolActivityStampRecord extends ToString implements StampRecord {

    private static final long serialVersionUID = -586892669388507172L;

    /**
     * 活动章
     */
    private List<ActivityStamp> activityStamps;

    @Override
    public List<ActivityStamp> getActivityStamps() {
        return activityStamps;
    }

    public void setActivityStamps(List<ActivityStamp> activityStamps) {
        this.activityStamps = activityStamps;
    }
}

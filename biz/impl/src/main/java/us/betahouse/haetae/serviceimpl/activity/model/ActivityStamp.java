/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.haetae.activity.model.ActivityRecordBO;

import java.util.Date;

/**
 * 活动章
 *
 * @author dango.yxm
 * @version : ActivityStamp.java 2018/11/25 1:53 PM dango.yxm
 */
public class ActivityStamp extends ActivityRecordBO {

    private static final long serialVersionUID = -6049854808032443292L;

    private static final double TIME_CONVERT = 10;

    /**
     * 活动名
     */
    private String activityName;

    /**
     * 单位信息
     */
    private String organizationMessage;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动分数
     */
    private Long score;

    /**
     * 活动时长
     */
    private String activityTime = "0";

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getOrganizationMessage() {
        return organizationMessage;
    }

    public void setOrganizationMessage(String organizationMessage) {
        this.organizationMessage = organizationMessage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public void setTime(Integer time) {
        super.setTime(time);
        this.activityTime = String.format("%.1f", time / TIME_CONVERT);
    }
}

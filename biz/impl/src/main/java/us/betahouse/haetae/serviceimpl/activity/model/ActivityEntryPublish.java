package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.util.common.ToString;

/**
  * @Author kana-cr
  * @Date  2020/8/28 13:23
  **/
public class ActivityEntryPublish extends ToString {

    /**
     * 当前订阅的消息 的 唯一凭证
     */
   private String subscribeId;

    /**
     * 温馨提示 （备注）
     */
    private String note;
    /**
     * 活动开始时间
     */
    private String start;

    /**
     * 活动结束时间
     */
    private String activityTime;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动地点
     */
    private String location;

    public String getSubscribeId() { return subscribeId; }

    public void setSubscribeId(String subscribeId) { this.subscribeId = subscribeId; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
    public String getStart() { return start; }

    public void setStart(String start) { this.start = start; }

    public String getActivityTime() { return activityTime; }

    public void setActivityTime(String activityTime) { this.activityTime = activityTime; }

    public String getActivityName() { return activityName; }

    public void setActivityName(String activityName) { this.activityName = activityName; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

}

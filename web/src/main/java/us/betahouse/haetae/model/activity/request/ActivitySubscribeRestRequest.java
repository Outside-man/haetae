package us.betahouse.haetae.model.activity.request;

import us.betahouse.haetae.common.RestRequest;

public class ActivitySubscribeRestRequest extends RestRequest {


    /**
     * 订阅ID 当前订阅的消息的唯一凭证
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
     * 活动时间
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

    /**
     * 微信小程序点击订阅后的跳转页面
     */
    private String page;

    /**
     * 设置在活动开始时间前 多长时间的提醒 0 - 120
     */
    private String advanceTime;

    public String getAdvanceTime() { return advanceTime; }

    public void setAdvanceTime(String advanceTime) { this.advanceTime = advanceTime; }

    public String getPage() { return page; }

    public void setPage(String page) { this.page = page; }

    public String getSubscribeId() { return subscribeId; }

    public void setSubscribeId(String subscribeId) { this.subscribeId = subscribeId; }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note;
    }
    public String getStart() { return start; }

    public void setStart(String start) { this.start = start; }

    public String getActivityTime() { return activityTime; }

    public void setActivityTime(String activityTime) { this.activityTime = activityTime; }

    public String getActivityName() { return activityName; }

    public void setActivityName(String activityName) { this.activityName = activityName; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }


}

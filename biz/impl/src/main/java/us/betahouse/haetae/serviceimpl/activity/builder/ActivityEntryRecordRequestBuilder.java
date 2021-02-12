package us.betahouse.haetae.serviceimpl.activity.builder;

import us.betahouse.haetae.activity.request.ActivityEntryRecordRequest;

/**
 * @author zjb
 * @version : ActivityEntryRecordRequestBuilder.java 2019/8/1 12:59 zjb
 */
public class ActivityEntryRecordRequestBuilder {

    /**
     * 报名记录id
     */
    private String activityEntryRecordId;

    /**
     * 关联报名信息id
     */
    private String activityEntryId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 是否参加
     */
    private Boolean isAttend;

    /**
     * 备注(预留)
     */
    private String note;

    /**
     * 报名选项(预留)
     */
    private String choose;

    private ActivityEntryRecordRequestBuilder() {
    }

    public static ActivityEntryRecordRequestBuilder anActivityEntryRecordRequest() {
        return new ActivityEntryRecordRequestBuilder();
    }

    public ActivityEntryRecordRequestBuilder withActivityEntryRecordId(String activityEntryRecordId) {
        this.activityEntryRecordId = activityEntryRecordId;
        return this;
    }

    public ActivityEntryRecordRequestBuilder withActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
        return this;
    }

    public ActivityEntryRecordRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ActivityEntryRecordRequestBuilder withAttend(Boolean attend) {
        isAttend = attend;
        return this;
    }

    public ActivityEntryRecordRequestBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public ActivityEntryRecordRequestBuilder withChoose(String choose) {
        this.choose = choose;
        return this;
    }

    public ActivityEntryRecordRequest build() {
        ActivityEntryRecordRequest activityEntryRecordRequest = new ActivityEntryRecordRequest();
        activityEntryRecordRequest.setActivityEntryId(activityEntryId);
        activityEntryRecordRequest.setActivityEntryRecordId(activityEntryRecordId);
        activityEntryRecordRequest.setUserId(userId);
        activityEntryRecordRequest.setAttend(isAttend);
        activityEntryRecordRequest.setNote(note);
        activityEntryRecordRequest.setChoose(choose);
        return activityEntryRecordRequest;
    }
}

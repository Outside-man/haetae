package us.betahouse.haetae.activity.builder;

import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zjb
 * @version : ActivityEntryRecordBOBuilder.java 2019/8/1 12:06 zjb
 */
public class ActivityEntryRecordBOBuilder {
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

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    private ActivityEntryRecordBOBuilder() {
    }

    public static ActivityEntryRecordBOBuilder getInstance() {
        return new ActivityEntryRecordBOBuilder();
    }

    public ActivityEntryRecordBOBuilder withActivityEntryRecordId(String activityEntryRecordId) {
        this.activityEntryRecordId = activityEntryRecordId;
        return this;
    }

    public ActivityEntryRecordBOBuilder withActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
        return this;
    }

    public ActivityEntryRecordBOBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ActivityEntryRecordBOBuilder withAttend(Boolean attend) {
        isAttend = attend;
        return this;
    }

    public ActivityEntryRecordBOBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public ActivityEntryRecordBOBuilder withChoose(String choose) {
        this.choose = choose;
        return this;
    }

    public ActivityEntryRecordBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public ActivityEntryRecordBO build() {
        ActivityEntryRecordBO activityEntryRecordBO = new ActivityEntryRecordBO();
        activityEntryRecordBO.setActivityEntryId(activityEntryId);
        activityEntryRecordBO.setAttend(isAttend);
        activityEntryRecordBO.setUserId(userId);
        activityEntryRecordBO.setChoose(choose);
        activityEntryRecordBO.setNote(note);
        activityEntryRecordBO.setExtInfo(extInfo);
        return activityEntryRecordBO;
    }
}

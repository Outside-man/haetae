package us.betahouse.haetae.activity.request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zjb
 * @version : ActivityEntryRecordRequest.java 2019/8/1 12:54 zjb
 */
public class ActivityEntryRecordRequest extends BaseRequest{

    private static final long serialVersionUID = -1874954318001026716L;
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
     * 报名记录状态
     */
    private String state;

    /**
     * 备注(预留)
     */
    private String note;

    /**
     * 报名选项(预留)
     */
    private String choose;

    public String getActivityEntryRecordId() {
        return activityEntryRecordId;
    }

    public void setActivityEntryRecordId(String activityEntryRecordId) {
        this.activityEntryRecordId = activityEntryRecordId;
    }

    public String getActivityEntryId() {
        return activityEntryId;
    }

    public void setActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getAttend() {
        return isAttend;
    }

    public void setAttend(Boolean attend) {
        isAttend = attend;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }
}

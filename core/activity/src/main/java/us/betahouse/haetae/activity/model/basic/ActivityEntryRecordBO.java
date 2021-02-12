package us.betahouse.haetae.activity.model.basic;

import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动报名记录领域对象
 *
 * @author zjb
 * @version : ActivityEntryRecordBO.java 2019/7/7 15:40 zjb
 */
public class ActivityEntryRecordBO extends ToString {


    private static final long serialVersionUID = -2348173932566634278L;
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
     * 记录状态
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

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    public String fetchExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }

    public void putExtInfo(String key, String value) {
        if (extInfo == null) {
            extInfo = new HashMap<>();
        }
        extInfo.put(key, value);
    }


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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

package us.betahouse.haetae.activity.model.basic;

import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动黑名单领域对象
 *
 * @author zjb
 * @version : ActivityBlacklistBO.java 2019/8/3 14:39 zjb
 */
public class ActivityBlacklistBO extends ToString {

    private static final long serialVersionUID = 594492617118067688L;
    /**
     * 黑名单id
     */
    private String activityBlacklistId;

    /**
     * 关联报名信息id
     */
    private String activityEntryId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 原因
     */
    private String reason;

    /**
     * 加入黑名单学期
     */
    private String term;

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

    public String getActivityBlacklistId() {
        return activityBlacklistId;
    }

    public void setActivityBlacklistId(String activityBlacklistId) {
        this.activityBlacklistId = activityBlacklistId;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

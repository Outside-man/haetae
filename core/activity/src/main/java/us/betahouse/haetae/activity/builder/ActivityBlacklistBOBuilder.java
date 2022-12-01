package us.betahouse.haetae.activity.builder;

import us.betahouse.haetae.activity.model.basic.ActivityBlacklistBO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zjb
 * @version : ActivityBlacklistBOBuilder.java 2019/8/3 15:48 zjb
 */
public class ActivityBlacklistBOBuilder {

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

    private ActivityBlacklistBOBuilder() {
    }
    
    public static ActivityBlacklistBOBuilder anActivityBlacklistBOBuilder(){
        return new ActivityBlacklistBOBuilder();
    }

    public ActivityBlacklistBOBuilder withActivityBlacklistId(String activityBlacklistId) {
        this.activityBlacklistId = activityBlacklistId;
        return this;
    }

    public ActivityBlacklistBOBuilder withActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
        return this;
    }

    public ActivityBlacklistBOBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ActivityBlacklistBOBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public ActivityBlacklistBOBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public ActivityBlacklistBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public ActivityBlacklistBO build() {
        ActivityBlacklistBO activityBlacklistBO = new ActivityBlacklistBO();
        activityBlacklistBO.setActivityBlacklistId(activityBlacklistId);
        activityBlacklistBO.setActivityEntryId(activityEntryId);
        activityBlacklistBO.setUserId(userId);
        activityBlacklistBO.setTerm(term);
        activityBlacklistBO.setReason(reason);
        activityBlacklistBO.setExtInfo(extInfo);
        return activityBlacklistBO;
    }
}

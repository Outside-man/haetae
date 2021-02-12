package us.betahouse.haetae.activity.dal.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
/**
 * 活动黑名单实体
 *
 * @author zjb
 * @version : ActivityBlacklistDO.java 2019/8/3 14:23 zjb
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activity_blacklist",
        indexes = {
                @Index(name = "uk_activity_blacklist_id", columnList = "activity_blacklist_id", unique = true)
        })
public class ActivityBlacklistDO extends BaseDO{

    private static final long serialVersionUID = -5545987838750380725L;
    /**
     * 黑名单id
     */
    @Column(name = "activity_blacklist_id", length = 32, updatable = false)
    private String activityBlacklistId;

    /**
     * 关联报名信息id
     */
    @Column(name = "activity_entry_id", length = 32, updatable = false)
    private String activityEntryId;

    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;

    /**
     * 原因
     */
    private String reason;

    /**
     * 加入黑名单学期
     */
    private String term;


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
}

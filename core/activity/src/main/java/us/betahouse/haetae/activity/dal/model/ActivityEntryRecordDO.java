package us.betahouse.haetae.activity.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 *
 *  活动报名记录实体
 *
 * @author zjb
 * @version : ActivityEntryRecordDO.java 2019/7/7 15:16 zjb
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activity_entry_record",
        indexes = {
                @Index(name = "uk_activity_entry_record_id", columnList = "activity_entry_record_id", unique = true)
        })
public class ActivityEntryRecordDO extends BaseDO{


    private static final long serialVersionUID = 4876493005623862550L;
    /**
     * 报名记录id
     */
    @Column(name = "activity_entry_record_id", length = 32, updatable = false)
    private String activityEntryRecordId;

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
     * 是否参加
     */
    @Column(name = "is_attend")
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


    public String getActivityEntryRecordId() {
        return activityEntryRecordId;
    }

    public void setActivityEntryRecordId(String activityEntryRecordId) {
        this.activityEntryRecordId = activityEntryRecordId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

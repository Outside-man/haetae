/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 活动章实体
 *
 * @author MessiahJK
 * @version : ActivitySealDO.java 2018/11/17 1:45 MessiahJK
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_activity_seal",
        indexes = {
                @Index(name = "uk_activity_seal_id", columnList = "activity_seal_id", unique = true)
        })
public class ActivitySealDO extends BaseDO {

    private static final long serialVersionUID = -8735096497842710304L;

    /**
     * 活动记录id
     */
    @Column(name = "activity_seal_id", length = 32, updatable = false)
    private String activitySealId;

    /**
     * 活动id
     */
    @Column(name = "activity_id",length = 32,nullable = false)
    private String activityId;

    /**
     * 用户id
     */
    @Column(name = "user_id",length = 32,nullable = false)
    private String userId;

    /**
     * 记录者id
     */
    @Column(name="scanner_user_id",length = 32,nullable = false)
    private String scannerUserId;

    /**
     * 时长
     */
    private Double time;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 学期
     */
    private String team;

    /**
     * 等第
     */
    private String grades;

    public String getActivitySealId() {
        return activitySealId;
    }

    public void setActivitySealId(String activitySealId) {
        this.activitySealId = activitySealId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScannerUserId() {
        return scannerUserId;
    }

    public void setScannerUserId(String scannerUserId) {
        this.scannerUserId = scannerUserId;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }
}

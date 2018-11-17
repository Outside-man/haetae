/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 活动实体
 *
 * @author MessiahJK
 * @version : ActivityDO.java 2018/11/17 1:43 MessiahJK
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_activity",
        indexes = {
                @Index(name = "uk_activity_id", columnList = "activity_id", unique = true)
        })
public class ActivityDO extends BaseDO{

    private static final long serialVersionUID = -6722889286261263968L;
    /**
     * 活动id
     */
    @Column(name = "activity_id", length = 32, updatable = false)
    private String activityId;

    /**
     * 活动名
     */
    @Column(name = "activity_name",length = 32)
    private String activityName;

    /**
     * 活动类型
     */
    @Column(length = 32)
    private String type;

    /**
     * 单位信息
     */
    @Column(name ="organization_message" ,length = 256)
    private String organizationMessage;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 默认时长
     */
    @Column(name ="default_time" )
    private Double defaultTime;

    /**
     * 活动开始时间
     */
    private Date start;

    /**
     * 活动结束时间
     */
    private Date end;

    /**
     * 活动分数
     */
    private Long score;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动创建者
     */
    @Column(name ="user_id")
    private String userId;

    /**
     * 活动状态
     */
    private String state;

    /**
     * 活动学期
     */
    private String team;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganizationMessage() {
        return organizationMessage;
    }

    public void setOrganizationMessage(String organizationMessage) {
        this.organizationMessage = organizationMessage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getDefaultTime() {
        return defaultTime;
    }

    public void setDefaultTime(Double defaultTime) {
        this.defaultTime = defaultTime;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}

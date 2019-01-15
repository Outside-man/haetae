/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.activity.request;

import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.common.RestRequest;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityOperationEnum;

/**
 * @author MessiahJK
 * @version : ActivityRestRequest.java 2018/11/25 21:39 MessiahJK
 */
public class ActivityRestRequest extends RestRequest {

    private static final long serialVersionUID = 8305664398451878372L;

    /**
     * 活动id
     */
    private String activityId;
    /**
     * 活动名
     */

    private String activityName;

    /**
     * 活动类型
     */

    private String activityType;

    /**
     * 单位信息
     */

    private String organizationMessage;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 默认时长
     */
    private Double defaultTime;

    /**
     * 活动开始时间
     */
    private Long activityStartTime;

    /**
     * 活动结束时间
     */
    private Long activityEndTime;

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
    private String userId;

    /**
     * 活动状态
     *
     * @see ActivityStateEnum
     */
    private String state;

    /**
     * 操作
     *
     * @see ActivityOperationEnum
     */
    private String operation;

    /**
     * 活动学期
     */
    private String term;

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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
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

    public Long getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Long activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Long getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Long activityEndTime) {
        this.activityEndTime = activityEndTime;
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

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}

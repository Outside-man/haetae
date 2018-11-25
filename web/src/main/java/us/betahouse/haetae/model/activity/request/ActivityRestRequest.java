/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.activity.request;

import us.betahouse.haetae.common.RestRequest;

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

    private String type;

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
    private Long start;

    /**
     * 活动结束时间
     */
    private Long end;

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
     */
    private String state;

    /**
     * 动作
     */
    private String motion;

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

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
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

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.builder;

import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityStampStatusEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : ActivityRequestBuilder.java 2018/11/22 18:34 MessiahJK
 */
public final class ActivityRequestBuilder {
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
     *请求id
     */
    private String requestId;
    /**
     * 单位信息
     */
    private String organizationMessage;
    /**
     * 额外参数
     */
    private Map<String, String> extInfo = new HashMap<>();
    /**
     * 地点
     */
    private String location;
    /**
     * 默认时长
     */
    private Double defaultTime;
    /**
     * 开始时间
     */
    private Long start;
    /**
     * 结束时间
     */
    private Long end;
    /**
     * 分数
     */
    private Long score;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建者用户id
     */
    private String userId;
    /**
     * 状态
     */
    private String state = ActivityStampStatusEnum.ENABLE.getCode();
    /**
     * 学期
     */
    private String term;

    private ActivityRequestBuilder() {
    }

    public static ActivityRequestBuilder anActivityRequest() {
        return new ActivityRequestBuilder();
    }

    public ActivityRequestBuilder withActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public ActivityRequestBuilder withActivityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    public ActivityRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ActivityRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public ActivityRequestBuilder withOrganizationMessage(String organizationMessage) {
        this.organizationMessage = organizationMessage;
        return this;
    }

    public ActivityRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public ActivityRequestBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public ActivityRequestBuilder withDefaultTime(Double defaultTime) {
        this.defaultTime = defaultTime;
        return this;
    }

    public ActivityRequestBuilder withStart(Long start) {
        this.start = start;
        return this;
    }

    public ActivityRequestBuilder withEnd(Long end) {
        this.end = end;
        return this;
    }

    public ActivityRequestBuilder withScore(Long score) {
        this.score = score;
        return this;
    }

    public ActivityRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ActivityRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ActivityRequestBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public ActivityRequestBuilder withTeam(String team) {
        this.term = team;
        return this;
    }

    public ActivityRequest build() {
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setActivityId(activityId);
        activityRequest.setActivityName(activityName);
        activityRequest.setType(type);
        activityRequest.setRequestId(requestId);
        activityRequest.setOrganizationMessage(organizationMessage);
        activityRequest.setExtInfo(extInfo);
        activityRequest.setLocation(location);
        activityRequest.setDefaultTime(defaultTime);
        activityRequest.setStart(start);
        activityRequest.setEnd(end);
        activityRequest.setScore(score);
        activityRequest.setDescription(description);
        activityRequest.setUserId(userId);
        activityRequest.setState(state);
        activityRequest.setTerm(term);
        return activityRequest;
    }
}

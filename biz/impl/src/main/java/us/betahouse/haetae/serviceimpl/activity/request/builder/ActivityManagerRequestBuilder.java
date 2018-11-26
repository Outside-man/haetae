/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request.builder;

import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : ActivityManagerRequestBuilder.java 2018/11/25 21:51 MessiahJK
 */
public final class ActivityManagerRequestBuilder {
    private String activityId;
    private String activityName;
    private String type;
    private String requestId;
    private String organizationMessage;
    private Map<String, String> extInfo = new HashMap<>();
    private String location;
    private Long start;
    private Long end;
    private Long score;
    private String description;
    private String userId;
    private String state;
    private String operation;
    private String term;

    private ActivityManagerRequestBuilder() {
    }

    public static ActivityManagerRequestBuilder getInstance() {
        return new ActivityManagerRequestBuilder();
    }

    public ActivityManagerRequestBuilder withActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public ActivityManagerRequestBuilder withActivityName(String activityName) {
        this.activityName = activityName;
        return this;
    }

    public ActivityManagerRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ActivityManagerRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public ActivityManagerRequestBuilder withOrganizationMessage(String organizationMessage) {
        this.organizationMessage = organizationMessage;
        return this;
    }

    public ActivityManagerRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public ActivityManagerRequestBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public ActivityManagerRequestBuilder withStart(Long start) {
        this.start = start;
        return this;
    }

    public ActivityManagerRequestBuilder withEnd(Long end) {
        this.end = end;
        return this;
    }

    public ActivityManagerRequestBuilder withScore(Long score) {
        this.score = score;
        return this;
    }

    public ActivityManagerRequestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ActivityManagerRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ActivityManagerRequestBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public ActivityManagerRequestBuilder withOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public ActivityManagerRequestBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public ActivityManagerRequest build() {
        ActivityManagerRequest activityManagerRequest = new ActivityManagerRequest();
        activityManagerRequest.setActivityId(activityId);
        activityManagerRequest.setActivityName(activityName);
        activityManagerRequest.setType(type);
        activityManagerRequest.setRequestId(requestId);
        activityManagerRequest.setOrganizationMessage(organizationMessage);
        activityManagerRequest.setExtInfo(extInfo);
        activityManagerRequest.setLocation(location);
        activityManagerRequest.setStart(start);
        activityManagerRequest.setEnd(end);
        activityManagerRequest.setScore(score);
        activityManagerRequest.setDescription(description);
        activityManagerRequest.setUserId(userId);
        activityManagerRequest.setState(state);
        activityManagerRequest.setOperation(operation);
        activityManagerRequest.setTerm(term);
        return activityManagerRequest;
    }
}

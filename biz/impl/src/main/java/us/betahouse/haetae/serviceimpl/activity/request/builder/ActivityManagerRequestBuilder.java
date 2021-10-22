/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request.builder;

import us.betahouse.haetae.activity.builder.ActivityBOBuilder;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;

import java.util.Date;
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
    private int applicationStamper;
    private String description;
    private String userId;
    private String state;
    private String operation;
    private String term;
    private Integer page;
    private Integer limit;
    private String orderRule;
    private Long activityStampedTimeStart;
    private Long activityStampedTimeEnd;
    private int actualStamper;
    private double stamperPercentageDeviation;
    private Date approvedTime;
    private String cancelReason;
    private Boolean modified;
    private String pictureUrl;

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

    public ActivityManagerRequestBuilder withApplicationStamper(int applicationStamper) {
        this.applicationStamper = applicationStamper;
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
    public ActivityManagerRequestBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }
    public ActivityManagerRequestBuilder withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }
    public ActivityManagerRequestBuilder withOrderRule(String orderRule){
        this.orderRule=orderRule;
        return this;
    }
    public ActivityManagerRequestBuilder withActivityStampedTimeStart(Long startTime) {
        this.activityStampedTimeStart=startTime;
        return this;
    }
    public ActivityManagerRequestBuilder withActivityStampedTimeEnd(Long endTime) {
        this.activityStampedTimeEnd=endTime;
        return this;
    }

    public ActivityManagerRequestBuilder withApprovedTime(Date approvedTime){
        this.approvedTime=approvedTime;
        return this;
    }
    public ActivityManagerRequestBuilder withCancelReason(String cancelReason){
        this.cancelReason=cancelReason;
        return this;
    }

    public ActivityManagerRequestBuilder withModified(Boolean modified){
        this.modified=modified;
        return this;
    }

    public ActivityManagerRequestBuilder withPictureUrl(String pictureUrl){
        this.pictureUrl=pictureUrl;
        return this;
    }

    public ActivityManagerRequestBuilder withActualStamper(int actualStamper){
        this.actualStamper=actualStamper;
        return this;
    }
    public ActivityManagerRequestBuilder withStamperPercentageDeviation(double stamperPercentageDeviation){
        this.stamperPercentageDeviation=stamperPercentageDeviation;
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
        activityManagerRequest.setApplicationStamper(applicationStamper);
        activityManagerRequest.setDescription(description);
        activityManagerRequest.setUserId(userId);
        activityManagerRequest.setState(state);
        activityManagerRequest.setOperation(operation);
        activityManagerRequest.setTerm(term);
        activityManagerRequest.setPage(page);
        activityManagerRequest.setLimit(limit);
        activityManagerRequest.setOrderRule(orderRule);
        activityManagerRequest.setActivityStampedTimeStart(activityStampedTimeStart);
        activityManagerRequest.setActivityStampedTimeEnd(activityStampedTimeEnd);
        activityManagerRequest.setApprovedTime(approvedTime);
        activityManagerRequest.setCancelReason(cancelReason);
        activityManagerRequest.setModified(modified);
        activityManagerRequest.setPictureUrl(pictureUrl);
        activityManagerRequest.setActualStamper(actualStamper);
        activityManagerRequest.setStamperPercentageDeviation(stamperPercentageDeviation);
        return activityManagerRequest;
    }
}

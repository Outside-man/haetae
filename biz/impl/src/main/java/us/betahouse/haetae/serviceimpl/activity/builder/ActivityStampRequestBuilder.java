/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.builder;

import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : ActivityRecordRequestBuilder.java 2018/11/22 18:59 MessiahJK
 */
public final class ActivityStampRequestBuilder {
    /**
     * 活动记录id
     */
    private String activityRecordId;
    /**
     * 活动id
     */
    private String activityId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 记录员id
     */
    private String scannerUserId;
    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();
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
    private String term;
    /**
     * 等第
     */
    private String grades;

    /**
     * 批量盖章
     */
    private List<String> userIds;


    private ActivityStampRequestBuilder() {
    }

    public static ActivityStampRequestBuilder getInstance() {
        return new ActivityStampRequestBuilder();
    }

    public ActivityStampRequest build() {
        ActivityStampRequest activityRecordRequest = new ActivityStampRequest();
        activityRecordRequest.setActivityRecordId(activityRecordId);
        activityRecordRequest.setActivityId(activityId);
        activityRecordRequest.setUserId(userId);
        activityRecordRequest.setRequestId(requestId);
        activityRecordRequest.setScannerUserId(scannerUserId);
        activityRecordRequest.setExtInfo(extInfo);
        activityRecordRequest.setTime(time);
        activityRecordRequest.setType(type);
        activityRecordRequest.setStatus(status);
        activityRecordRequest.setTerm(term);
        activityRecordRequest.setGrades(grades);
        activityRecordRequest.setUserIds(userIds);
        return activityRecordRequest;
    }

    public ActivityStampRequestBuilder withActivityRecordId(String activityRecordId) {
        this.activityRecordId = activityRecordId;
        return this;
    }

    public ActivityStampRequestBuilder withActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public ActivityStampRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ActivityStampRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public ActivityStampRequestBuilder withScannerUserId(String scannerUserId) {
        this.scannerUserId = scannerUserId;
        return this;
    }

    public ActivityStampRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public ActivityStampRequestBuilder withTime(Double time) {
        this.time = time;
        return this;
    }

    public ActivityStampRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ActivityStampRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public ActivityStampRequestBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public ActivityStampRequestBuilder withGrades(String grades) {
        this.grades = grades;
        return this;
    }

    public ActivityStampRequestBuilder withUserIds(List<String> userIds) {
        this.userIds = userIds;
        return this;
    }
}

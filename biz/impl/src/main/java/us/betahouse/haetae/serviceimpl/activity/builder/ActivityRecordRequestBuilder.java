/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.builder;

import us.betahouse.haetae.activity.request.ActivityRecordRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : ActivityRecordRequestBuilder.java 2018/11/22 18:59 MessiahJK
 */
public final class ActivityRecordRequestBuilder {
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
    private String team;
    /**
     * 等第
     */
    private String grades;

    private ActivityRecordRequestBuilder() {
    }

    public static ActivityRecordRequestBuilder anActivityRecordRequest() {
        return new ActivityRecordRequestBuilder();
    }

    public ActivityRecordRequestBuilder withActivityRecordId(String activityRecordId) {
        this.activityRecordId = activityRecordId;
        return this;
    }

    public ActivityRecordRequestBuilder withActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public ActivityRecordRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ActivityRecordRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public ActivityRecordRequestBuilder withScannerUserId(String scannerUserId) {
        this.scannerUserId = scannerUserId;
        return this;
    }

    public ActivityRecordRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public ActivityRecordRequestBuilder withTime(Double time) {
        this.time = time;
        return this;
    }

    public ActivityRecordRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ActivityRecordRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public ActivityRecordRequestBuilder withTeam(String team) {
        this.team = team;
        return this;
    }

    public ActivityRecordRequestBuilder withGrades(String grades) {
        this.grades = grades;
        return this;
    }

    public ActivityRecordRequest build() {
        ActivityRecordRequest activityRecordRequest = new ActivityRecordRequest();
        activityRecordRequest.setActivityRecordId(activityRecordId);
        activityRecordRequest.setActivityId(activityId);
        activityRecordRequest.setUserId(userId);
        activityRecordRequest.setRequestId(requestId);
        activityRecordRequest.setScannerUserId(scannerUserId);
        activityRecordRequest.setExtInfo(extInfo);
        activityRecordRequest.setTime(time);
        activityRecordRequest.setType(type);
        activityRecordRequest.setStatus(status);
        activityRecordRequest.setTeam(team);
        activityRecordRequest.setGrades(grades);
        return activityRecordRequest;
    }
}

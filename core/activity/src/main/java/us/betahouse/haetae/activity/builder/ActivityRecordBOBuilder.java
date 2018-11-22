/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.builder;

import us.betahouse.haetae.activity.model.ActivityRecordBO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : ActivityRecordBOBuilder.java 2018/11/22 21:35 MessiahJK
 */
public final class ActivityRecordBOBuilder {
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
     * 记录者id
     */
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
    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();


    private ActivityRecordBOBuilder() {
    }

    public static ActivityRecordBOBuilder anActivityRecordBO() {
        return new ActivityRecordBOBuilder();
    }

    public ActivityRecordBOBuilder withActivityRecordId(String activityRecordId) {
        this.activityRecordId = activityRecordId;
        return this;
    }

    public ActivityRecordBOBuilder withActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public ActivityRecordBOBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ActivityRecordBOBuilder withScannerUserId(String scannerUserId) {
        this.scannerUserId = scannerUserId;
        return this;
    }

    public ActivityRecordBOBuilder withTime(Double time) {
        this.time = time;
        return this;
    }

    public ActivityRecordBOBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ActivityRecordBOBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public ActivityRecordBOBuilder withTeam(String team) {
        this.team = team;
        return this;
    }

    public ActivityRecordBOBuilder withGrades(String grades) {
        this.grades = grades;
        return this;
    }

    public ActivityRecordBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public ActivityRecordBO build() {
        ActivityRecordBO activityRecordBO = new ActivityRecordBO();
        activityRecordBO.setActivityRecordId(activityRecordId);
        activityRecordBO.setActivityId(activityId);
        activityRecordBO.setUserId(userId);
        activityRecordBO.setScannerUserId(scannerUserId);
        activityRecordBO.setTime(time);
        activityRecordBO.setType(type);
        activityRecordBO.setStatus(status);
        activityRecordBO.setTeam(team);
        activityRecordBO.setGrades(grades);
        activityRecordBO.setExtInfo(extInfo);
        return activityRecordBO;
    }
}

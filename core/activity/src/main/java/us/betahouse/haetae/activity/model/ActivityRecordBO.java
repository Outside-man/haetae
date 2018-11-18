/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.model;

import common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动记录模型
 *
 * @author MessiahJK
 * @version : ActivityRecordBO.java 2018/11/17 20:20 MessiahJK
 */
public class ActivityRecordBO extends ToString {


    private static final long serialVersionUID = 1910747655670036477L;
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

    public String getActivityRecordId() {
        return activityRecordId;
    }

    public void setActivityRecordId(String activityRecordId) {
        this.activityRecordId = activityRecordId;
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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
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

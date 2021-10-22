/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.request;


import us.betahouse.haetae.activity.model.basic.PastActivityBO;

import java.util.Date;

/**
 * 活动管理请求
 *
 * @author MessiahJK
 * @version : ActivityRequest.java 2018/11/22 16:11 MessiahJK
 */
public class ActivityRequest extends BaseRequest {

    private static final long serialVersionUID = 4996563906904688691L;
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
     * 扫章数
     */
    private int applicationStamper;

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
    private String operation;
    /**
     * 活动学期
     */
    private String term;

    /**
     * 每页条数
     */
    private Integer limit;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 排序方式
     */
    private String orderRule;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 尚未分配活动章
     */
    private Long undistributedStamp;

    /**
     * 以往校园活动章
     */
    private Long pastSchoolActivity;

    /**
     * 以往讲座活动章
     */
    private Long pastLectureActivity;

    /**
     * 以往志愿活动时间
     */
    private Long pastVolunteerActivityTime;

    /**
     * 以往社会实践次数
     */
    private Long pastPracticeActivity;

    /**
     * 活动盖章开始时间
     */
    private Long activityStampedTimeStart;

    /**
     * 活动盖章结束时间
     */
    private Long activityStampedTimeEnd;


    /**
     * 审批通过的时间
     */
    private Date approvedTime;

    /**
     * 驳回原因
     */
    private String cancelReason;

    /**
     * 审批修改记录
     */
    private Boolean modified;

    /**
     * 钉钉审批截图
     */
    private String pictureUrl;


    /**
     * 实际扫章数
     */
    private int actualStamper;

    /**
     * 扫章偏差百分比
     */
    private double stamperPercentageDeviation;

    private PastActivityBO pastActivityBO;

    public int getActualStamper() {
        return actualStamper;
    }

    public void setActualStamper(int actualStamper) {
        this.actualStamper = actualStamper;
    }

    public double getStamperPercentageDeviation() {
        return stamperPercentageDeviation;
    }

    public void setStamperPercentageDeviation(double stamperPercentageDeviation) {
        this.stamperPercentageDeviation = stamperPercentageDeviation;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Boolean getModified() {
        return modified;
    }

    public void setModified(Boolean modified) {
        this.modified = modified;
    }

    public Date getApprovedTime() {
        return approvedTime;
    }

    public void setApprovedTime(Date approvedTime) {
        this.approvedTime = approvedTime;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }

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

    public int getApplicationStamper() {
        return applicationStamper;
    }

    public void setApplicationStamper(int applicationStamper) {
        this.applicationStamper = applicationStamper;
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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }


    public Long getUndistributedStamp() {
        return undistributedStamp;
    }

    public void setUndistributedStamp(Long undistributedStamp) {
        this.undistributedStamp = undistributedStamp;
    }

    public Long getPastSchoolActivity() {
        return pastSchoolActivity;
    }

    public void setPastSchoolActivity(Long pastSchoolActivity) {
        this.pastSchoolActivity = pastSchoolActivity;
    }

    public Long getPastLectureActivity() {
        return pastLectureActivity;
    }

    public void setPastLectureActivity(Long pastLectureActivity) {
        this.pastLectureActivity = pastLectureActivity;
    }

    public Long getPastVolunteerActivityTime() {
        return pastVolunteerActivityTime;
    }

    public void setPastVolunteerActivityTime(Long pastVolunteerActivityTime) {
        this.pastVolunteerActivityTime = pastVolunteerActivityTime;
    }

    public Long getPastPracticeActivity() {
        return pastPracticeActivity;
    }

    public void setPastPracticeActivity(Long pastPracticeActivity) {
        this.pastPracticeActivity = pastPracticeActivity;
    }

    public PastActivityBO getPastActivityBO() {
        return pastActivityBO;
    }

    public void setPastActivityBO(PastActivityBO pastActivityBO) {
        this.pastActivityBO = pastActivityBO;
    }

    public Long getActivityStampedTimeStart() {
        return activityStampedTimeStart;
    }

    public void setActivityStampedTimeStart(Long activityStampedTimeStart) {
        this.activityStampedTimeStart = activityStampedTimeStart;
    }

    public Long getActivityStampedTimeEnd() {
        return activityStampedTimeEnd;
    }

    public void setActivityStampedTimeEnd(Long activityStampedTimeEnd) {
        this.activityStampedTimeEnd = activityStampedTimeEnd;
    }
}

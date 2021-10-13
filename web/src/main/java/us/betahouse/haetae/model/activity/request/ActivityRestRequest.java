/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.activity.request;

import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.common.RestRequest;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityOperationEnum;

import java.util.Date;

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
     * 申请章数
     */

    private int applicationStamper;

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

    /**
     * 每页条数
     */
    private Integer limit;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 排序规则
     */
    private String orderRule;


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
     * 活动盖章开始时间
     */
    private Long activityStampedStart;

    /**
     * 活动盖章结束时间
     */
    private Long activityStampedEnd;

    /**
     * 通过负责人学号关键字查找
     */
    private String searchCreatorStuId;

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


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
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

    public int getApplicationStamper() {
        return applicationStamper;
    }

    public void setApplicationStamper(int applicationStamper) {
        this.applicationStamper = applicationStamper;
    }


    public Long getActivityStampedStart() {
        return activityStampedStart;
    }

    public void setActivityStampedStart(Long activityStampedStart) {
        this.activityStampedStart = activityStampedStart;
    }

    public Long getActivityStampedEnd() {
        return activityStampedEnd;
    }

    public void setActivityStampedEnd(Long activityStampedEnd) {
        this.activityStampedEnd = activityStampedEnd;
    }

    public String getSearchCreatorStuId() {
        return searchCreatorStuId;
    }

    public void setSearchCreatorStuId(String searchCreatorStuId) {
        this.searchCreatorStuId = searchCreatorStuId;
    }


}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.request;


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

    private String orderRule;

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

}

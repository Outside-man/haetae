package us.betahouse.haetae.activity.request;

/**
 * 活动报名信息请求
 *
 * @author zjb
 * @version : ActivityEntryRequest.java 2019/7/7 17:07 zjb
 */
public class ActivityEntryRequest extends BaseRequest {


    private static final long serialVersionUID = -4837312492643687499L;
    /**
     * 活动id
     */
    private String activityId;

    /**
     * 报名状态
     */
    private String state;

    /**
     * 报名学期
     */
    private String term;

    /**
     * 活动类型
     */
    private String type;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 每页条数
     */
    private Integer limit;

    /**
     * 排序方式
     */
    private String orderRule;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }
}

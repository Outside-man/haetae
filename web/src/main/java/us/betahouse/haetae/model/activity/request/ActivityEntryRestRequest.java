package us.betahouse.haetae.model.activity.request;

import us.betahouse.haetae.common.RestRequest;

/**
 * 活动报名信息Rest请求
 *
 * @author zjb
 * @version : ActivityEntryRequest.java 2019/7/7 17:07 zjb
 */
public class ActivityEntryRestRequest extends RestRequest {


    private static final long serialVersionUID = -5797478718379942199L;
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
    private String activityType;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 每页条数
     */
    private Integer limit;


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

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
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

}

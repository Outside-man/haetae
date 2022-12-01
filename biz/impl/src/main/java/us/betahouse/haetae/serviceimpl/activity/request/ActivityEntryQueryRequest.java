package us.betahouse.haetae.serviceimpl.activity.request;

import us.betahouse.haetae.activity.request.ActivityEntryRequest;
import us.betahouse.haetae.activity.request.BaseRequest;

/**
 *
 * 活动报名信息查询请求
 *
 * @author zjb
 * @version : ActivityEntryRequest.java 2019/7/7 17:01 zjb
 */
public class ActivityEntryQueryRequest extends ActivityEntryRequest  {

    private static final long serialVersionUID = 3391301551289112529L;
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


    public ActivityEntryQueryRequest(String activityId, String state, String term, String type, Integer page, Integer limit) {
        this.activityId = activityId;
        this.state = state;
        this.term = term;
        this.type = type;
        this.page = page;
        this.limit = limit;
    }

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

}

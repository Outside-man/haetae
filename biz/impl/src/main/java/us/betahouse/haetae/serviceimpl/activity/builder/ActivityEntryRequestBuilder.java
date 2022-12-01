package us.betahouse.haetae.serviceimpl.activity.builder;

import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;
import us.betahouse.haetae.activity.request.ActivityEntryRequest;

/**
 * @author zjb
 * @version : ActivityEntryRequestBuilder.java 2019/7/31 22:21 zjb
 */
public class ActivityEntryRequestBuilder {

    /**
     * 活动报名信息id
     */
    private String activityEntryId;

    /**
     * 关联活动id
     */
    private String activityId;

    /**
     * 报名状态
     * @see ActivityEntryStateEnum
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
     * 报名标题
     */
    private String title;

    /**
     * 报名人数限额
     */
    private Integer number;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 报名开始时间
     */
    private Long start;

    /**
     * 报名结束时间
     */
    private Long end;

    /**
     * 报名选项 以“|”分隔选项(预留)
     */
    private String choose;

    /**
     * 置顶
     */
    private Integer top;


    /**
     * 报名要求
     */
    private String note;


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

    private ActivityEntryRequestBuilder() {
    }

    public static ActivityEntryRequestBuilder anActivityEntryRequest() {
        return new ActivityEntryRequestBuilder();
    }

    public ActivityEntryRequestBuilder withActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
        return this;
    }

    public ActivityEntryRequestBuilder withActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public ActivityEntryRequestBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public ActivityEntryRequestBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public ActivityEntryRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ActivityEntryRequestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ActivityEntryRequestBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public ActivityEntryRequestBuilder withLinkman(String linkman) {
        this.linkman = linkman;
        return this;
    }

    public ActivityEntryRequestBuilder withContact(String contact) {
        this.contact = contact;
        return this;
    }

    public ActivityEntryRequestBuilder withStart(Long start) {
        this.start = start;
        return this;
    }

    public ActivityEntryRequestBuilder withEnd(Long end) {
        this.end = end;
        return this;
    }

    public ActivityEntryRequestBuilder withChoose(String choose) {
        this.choose = choose;
        return this;
    }

    public ActivityEntryRequestBuilder withTop(Integer top) {
        this.top = top;
        return this;
    }

    public ActivityEntryRequestBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public ActivityEntryRequestBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }

    public ActivityEntryRequestBuilder withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public ActivityEntryRequestBuilder withOrderRule(String orderRule) {
        this.orderRule = orderRule;
        return this;
    }

    public ActivityEntryRequest build() {
        ActivityEntryRequest activityEntryRequest = new ActivityEntryRequest();
        activityEntryRequest.setActivityEntryId(activityEntryId);
        activityEntryRequest.setActivityId(activityId);
        activityEntryRequest.setState(state);
        activityEntryRequest.setTerm(term);
        activityEntryRequest.setType(type);
        activityEntryRequest.setTitle(title);
        activityEntryRequest.setNumber(number);
        activityEntryRequest.setLinkman(linkman);
        activityEntryRequest.setContact(contact);
        activityEntryRequest.setStart(start);
        activityEntryRequest.setEnd(end);
        activityEntryRequest.setChoose(choose);
        activityEntryRequest.setTop(top);
        activityEntryRequest.setNote(note);
        activityEntryRequest.setPage(page);
        activityEntryRequest.setLimit(limit);
        activityEntryRequest.setOrderRule(orderRule);
        return activityEntryRequest;
    }
}

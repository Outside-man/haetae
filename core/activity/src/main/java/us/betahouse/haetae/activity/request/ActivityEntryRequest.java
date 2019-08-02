package us.betahouse.haetae.activity.request;

import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;

import java.util.Date;

/**
 * 活动报名信息请求
 *
 * @author zjb
 * @version : ActivityEntryRequest.java 2019/7/7 17:07 zjb
 */
public class ActivityEntryRequest extends BaseRequest {


    private static final long serialVersionUID = -4837312492643687499L;

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


    public String getActivityEntryId() {
        return activityEntryId;
    }

    public void setActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

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
     * @see us.betahouse.haetae.activity.enums.ActivityEntryStateEnum
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
    private String start;

    /**
     * 报名结束时间
     */
    private String end;

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
     * 报名记录id
     */
    private String activityEntryRecordId;

    /**
     * 关联报名信息id
     */
    private String activityEntryId;

    /**
     * 是否参加
     */
    private Boolean isAttend;

    /**
     * 用户报名备注(预留)
     */
    private String recordNote;

    /**
     * 用户报名选项(预留)
     */
    private String recordChoose;


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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
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

    public String getRecordNote() {
        return recordNote;
    }

    public void setRecordNote(String recordNote) {
        this.recordNote = recordNote;
    }

    public String getRecordChoose() {
        return recordChoose;
    }

    public void setRecordChoose(String recordChoose) {
        this.recordChoose = recordChoose;
    }

    public String getActivityEntryRecordId() {
        return activityEntryRecordId;
    }

    public void setActivityEntryRecordId(String activityEntryRecordId) {
        this.activityEntryRecordId = activityEntryRecordId;
    }

    public String getActivityEntryId() {
        return activityEntryId;
    }

    public void setActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
    }

    public Boolean getAttend() {
        return isAttend;
    }

    public void setAttend(Boolean attend) {
        isAttend = attend;
    }
}

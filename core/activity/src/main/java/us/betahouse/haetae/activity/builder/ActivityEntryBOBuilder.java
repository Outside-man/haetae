package us.betahouse.haetae.activity.builder;

import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;
import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zjb
 * @version : ActivityEntryBOBuilder.java 2019/7/31 21:26 zjb
 */
public class ActivityEntryBOBuilder {
    /**
     * 活动报名信息id
     */
    private String activityEntryId;

    /**
     * 关联活动id
     */
    private String activityId;

    /**
     * 活动类型
     */
    private String type;

    /**
     * 报名标题
     */
    private String title;

    /**
     * 报名学期
     */
    private String term;

    /**
     * 报名状态
     * @see ActivityEntryStateEnum
     */
    private String state;

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
    private Date start;

    /**
     * 报名结束时间
     */
    private Date end;

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
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    private ActivityEntryBOBuilder() {
    }

    public static ActivityEntryBOBuilder getInstance() {
        return new ActivityEntryBOBuilder();
    }

    public ActivityEntryBOBuilder withActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
        return this;
    }

    public ActivityEntryBOBuilder withActivityId(String activityId) {
        this.activityId = activityId;
        return this;
    }

    public ActivityEntryBOBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public ActivityEntryBOBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ActivityEntryBOBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public ActivityEntryBOBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public ActivityEntryBOBuilder withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public ActivityEntryBOBuilder withLinkman(String linkman) {
        this.linkman = linkman;
        return this;
    }

    public ActivityEntryBOBuilder withContact(String contact) {
        this.contact = contact;
        return this;
    }

    public ActivityEntryBOBuilder withStart(Date start) {
        this.start = start;
        return this;
    }

    public ActivityEntryBOBuilder withEnd(Date end) {
        this.end = end;
        return this;
    }

    public ActivityEntryBOBuilder withChoose(String choose) {
        this.choose = choose;
        return this;
    }

    public ActivityEntryBOBuilder withTop(Integer top) {
        this.top = top;
        return this;
    }

    public ActivityEntryBOBuilder withNote(String note) {
        this.note = note;
        return this;
    }

    public ActivityEntryBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public ActivityEntryBO build() {
        ActivityEntryBO activityEntryBO = new ActivityEntryBO();
        activityEntryBO.setActivityEntryId(activityEntryId);
        activityEntryBO.setActivityId(activityId);
        activityEntryBO.setType(type);
        activityEntryBO.setTitle(title);
        activityEntryBO.setTerm(term);
        activityEntryBO.setState(state);
        activityEntryBO.setNumber(number);
        activityEntryBO.setLinkman(linkman);
        activityEntryBO.setContact(contact);
        activityEntryBO.setStart(start);
        activityEntryBO.setEnd(end);
        activityEntryBO.setChoose(choose);
        activityEntryBO.setTop(top);
        activityEntryBO.setNote(note);
        activityEntryBO.setExtInfo(extInfo);
        return activityEntryBO;
    }
}

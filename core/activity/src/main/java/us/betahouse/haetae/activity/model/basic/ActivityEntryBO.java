package us.betahouse.haetae.activity.model.basic;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;
import us.betahouse.util.common.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 活动报名信息领域对象
 *
 * @author zjb
 * @version : ActivityEntryBO.java 2019/7/7 15:31 zjb
 */
public class ActivityEntryBO extends ToString {


    private static final long serialVersionUID = 959124293407740123L;
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

    public String fetchExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }

    public void putExtInfo(String key, String value) {
        if (extInfo == null) {
            extInfo = new HashMap<>();
        }
        extInfo.put(key, value);
    }

    /**
     * 判断是否可以结束
     *
     * @return
     */
    public boolean canFinish() {
        if(StringUtils.equals(state, ActivityEntryStateEnum.PUBLISHED.getCode()) && end.before(new Date())) {
            return true;
        }
        return false;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

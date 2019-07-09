package us.betahouse.haetae.activity.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import us.betahouse.haetae.activity.enums.ActivityEntryStateEnum;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * 活动报名信息实体
 *
 * @author zjb
 * @version : ActivityEntryDO.java 2019/7/7 15:01 zjb
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activity_entry",
        indexes = {
                @Index(name = "uk_activity_entry_id", columnList = "activity_entry_id", unique = true)
        })
public class ActivityEntryDO extends BaseDO{


    private static final long serialVersionUID = -6742306388497749906L;
    /**
     * 活动报名信息id
     */
    @Column(name = "activity_entry_id", length = 32, updatable = false)
    private String activityEntryId;

    /**
     * 关联活动id
     */
    @Column(name = "activity_id", length = 32, updatable = false)
    private String activityId;

    /**
     * 活动类型
     */
    @Column(length = 32)
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
     * 报名要求(预留)
     */
    private String note;

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
}

package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.util.common.ToString;

import java.util.Date;

/**
 *
 * 活动报名信息
 *
 * @author zjb
 * @version : ActivityEntry.java 2019/7/7 18:20 zjb
 */
public class ActivityEntry extends ToString {


    private static final long serialVersionUID = -6648068882347617567L;
    /**
     * 活动id
     */
    private String activityId;

    /**
     * 活动报名信息id
     */
    private String activityEntryId;

    /**
     * 活动报名标题
     */
    private String title;

    /**
     * 活动报名开始时间
     */
    private String activityEntryStart;

    /**
     * 活动报名结束时间
     */
    private String activityEntryEnd;

    /**
     * 报名状态
     */
    private String status;

    /**
     * 距报名开始秒数
     */
    private Long second;
    /**
     * 报名名额
     */
    private Integer number;

    /**
     * 报名对应联系人
     */
    private String linkman;

    /**
     * 报名对应联系方式
     */
    private String contact;

    /**
     * 报名选项
     */
    private String choose;

    /**
     * 报名优先级
     */
    private Integer top;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动类型
     */
    private String activityType;

    /**
     * 活动开始时间
     */
    private String start;

    /**
     * 活动结束时间
     */
    private String end;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 活动描述
     */
    private String description;


    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityEntryId() {
        return activityEntryId;
    }

    public void setActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityEntryStart() {
        return activityEntryStart;
    }

    public void setActivityEntryStart(String activityEntryStart) {
        this.activityEntryStart = activityEntryStart;
    }

    public String getActivityEntryEnd() {
        return activityEntryEnd;
    }

    public void setActivityEntryEnd(String activityEntryEnd) {
        this.activityEntryEnd = activityEntryEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSecond() {
        return second;
    }

    public void setSecond(Long second) {
        this.second = second;
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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean typeAndStatusAndTermFilter (String type, String status, String term){
        boolean flag = false;
        if("".equals(type) || type == null){
            if( "".equals(status)|| status == null || this.status.equals(status)){
                flag = true;
            }
        }else if( this.activityType.equals(type)){
            if( "".equals(status)|| status == null || this.status.equals(status)){
                flag = true;
            }
        }

        if(!"".equals(term) && type != null && this.status.equals(term) ){
            flag = false;
        }
        return flag;
    }

}

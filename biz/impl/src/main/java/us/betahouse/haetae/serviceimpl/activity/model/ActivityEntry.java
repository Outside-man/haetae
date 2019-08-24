package us.betahouse.haetae.serviceimpl.activity.model;

import us.betahouse.util.common.ToString;

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
     * 活动名称
     */
    private String activityName;

    /**
     * 活动类型
     */
    private String activityType;

    /**
     * 报名开始时间
     */
    private String start;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 报名状态
     */
    private String status;

    /**
     * 距报名开始秒数
     */
    private Long second;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
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

    public String getActivityEntryId() {
        return activityEntryId;
    }

    public void setActivityEntryId(String activityEntryId) {
        this.activityEntryId = activityEntryId;
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

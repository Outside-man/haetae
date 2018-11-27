/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.user.request;

import us.betahouse.haetae.common.RestRequest;
import us.betahouse.haetae.serviceimpl.activity.constant.GradesConstant;

import java.util.List;

/**
 * 活动盖章请求
 *
 * @author dango.yxm
 * @version : StamperRequest.java 2018/11/25 3:32 PM dango.yxm
 */
public class StamperRequest extends RestRequest {

    private static final long serialVersionUID = -8891245952523574347L;

    /**
     * 参与者
     */
    private List<String> participants;

    /**
     * 活动类型
     */
    private String activityId;

    /**
     * 盖章员stuId 赋权时使用
     */
    private String stampStuId;

    /**
     * 等第 实践活动使用
     *
     * @see GradesConstant
     */
    private String grades;

    /**
     * 时长 志愿/义工使用
     */
    private Double time;

    /**
     * 义工名称
     */
    private String volunteerWorkName;

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getStamperStuId() {
        return stampStuId;
    }

    public void setStamperStuId(String stampStuId) {
        this.stampStuId = stampStuId;
    }

    public String getStampStuId() {
        return stampStuId;
    }

    public void setStampStuId(String stampStuId) {
        this.stampStuId = stampStuId;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getVolunteerWorkName() {
        return volunteerWorkName;
    }

    public void setVolunteerWorkName(String volunteerWorkName) {
        this.volunteerWorkName = volunteerWorkName;
    }
}

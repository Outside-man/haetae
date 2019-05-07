/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.activity.model.basic;

import us.betahouse.util.common.ToString;

/**
 * @author MessiahJK
 * @version : PastActivityBO.java 2019/04/26 11:00 MessiahJK
 */
public class PastActivityBO extends ToString {


    /**
     * 活动id
     */
    private String pastActivityId;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 学号
     */
    private String stuId;


    /**
     * 尚未分配活动章
     */
    private Long undistributedStamp;

    /**
     * 以往校园活动章
     */
    private Long pastSchoolActivity;

    /**
     * 以往讲座活动章
     */
    private Long pastLectureActivity;

    /**
     * 以往志愿活动时间
     */
    private Long pastVolunteerActivityTime;

    /**
     * 以往社会实践次数
     */
    private Long pastPracticeActivity;

    public String getPastActivityId() {
        return pastActivityId;
    }

    public void setPastActivityId(String pastActivityId) {
        this.pastActivityId = pastActivityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getUndistributedStamp() {
        return undistributedStamp;
    }

    public void setUndistributedStamp(Long undistributedStamp) {
        this.undistributedStamp = undistributedStamp;
    }

    public Long getPastSchoolActivity() {
        return pastSchoolActivity;
    }

    public void setPastSchoolActivity(Long pastSchoolActivity) {
        this.pastSchoolActivity = pastSchoolActivity;
    }

    public Long getPastLectureActivity() {
        return pastLectureActivity;
    }

    public void setPastLectureActivity(Long pastLectureActivity) {
        this.pastLectureActivity = pastLectureActivity;
    }

    public Long getPastVolunteerActivityTime() {
        return pastVolunteerActivityTime;
    }

    public void setPastVolunteerActivityTime(Long pastVolunteerActivityTime) {
        this.pastVolunteerActivityTime = pastVolunteerActivityTime;
    }

    public Long getPastPracticeActivity() {
        return pastPracticeActivity;
    }

    public void setPastPracticeActivity(Long pastPracticeActivity) {
        this.pastPracticeActivity = pastPracticeActivity;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

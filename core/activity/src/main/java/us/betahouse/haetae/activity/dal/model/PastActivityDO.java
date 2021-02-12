/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.activity.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 以往小绿本数据 2017级毕业后可以考虑移除
 *
 * @author MessiahJK
 * @version : PastActivityDO.java 2019/04/26 9:07 MessiahJK
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activity_past_tnt",
        indexes = {
                @Index(name = "uk_activity_past_id", columnList = "activity_past_id", unique = true)
        })
public class PastActivityDO  extends BaseDO {

    /**
     * 活动id
     */
    @Column(name = "activity_past_id", length = 32, updatable = false)
    private String pastActivityId;
    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;

    /**
     * 用户id
     */
    @Column(name = "stu_id", length = 32, nullable = false)
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

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Long getPastPracticeActivity() {
        return pastPracticeActivity;
    }

    public void setPastPracticeActivity(Long pastPracticeActivity) {
        this.pastPracticeActivity = pastPracticeActivity;
    }
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.activity;

import us.betahouse.haetae.activity.model.basic.PastActivityBO;

/**
 * @author MessiahJK
 * @version : PastActivityVO.java 2019/04/30 0:06 MessiahJK
 */
public class PastActivityVO {

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
    private Double pastVolunteerActivityTime;

    /**
     * 以往社会实践次数
     */
    private Long pastPracticeActivity;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
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

    public Double getPastVolunteerActivityTime() {
        return pastVolunteerActivityTime;
    }

    public void setPastVolunteerActivityTime(Double pastVolunteerActivityTime) {
        this.pastVolunteerActivityTime = pastVolunteerActivityTime;
    }

    public Long getPastPracticeActivity() {
        return pastPracticeActivity;
    }

    public void setPastPracticeActivity(Long pastPracticeActivity) {
        this.pastPracticeActivity = pastPracticeActivity;
    }
    public static PastActivityVO valueOf(PastActivityBO pastActivityBO){
        PastActivityVO pastActivityVO = new PastActivityVO();
        pastActivityVO.setStuId(pastActivityBO.getStuId());
        pastActivityVO.setUndistributedStamp(pastActivityBO.getUndistributedStamp());
        pastActivityVO.setPastSchoolActivity(pastActivityBO.getPastSchoolActivity());
        pastActivityVO.setPastLectureActivity(pastActivityBO.getPastLectureActivity());
        pastActivityVO.setPastVolunteerActivityTime((double) (pastActivityBO.getPastVolunteerActivityTime()/10));
        pastActivityVO.setPastPracticeActivity(pastActivityBO.getPastPracticeActivity());
        return pastActivityVO;

    }
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.activity.builder;

import us.betahouse.haetae.activity.model.basic.PastActivityBO;

/**
 * @author MessiahJK
 * @version : PastActivityBOBuilder.java 2019/04/28 22:27 MessiahJK
 */
public final class PastActivityBOBuilder {
    private String pastActivityId;
    private String userId;
    private String stuId;
    private Long undistributedStamp;
    private Long pastSchoolActivity;
    private Long pastLectureActivity;
    private Long pastVolunteerActivityTime;
    private Long pastPracticeActivity;

    private PastActivityBOBuilder() {
    }

    public static PastActivityBOBuilder aPastActivityBO() {
        return new PastActivityBOBuilder();
    }

    public PastActivityBOBuilder withPastActivityId(String pastActivityId) {
        this.pastActivityId = pastActivityId;
        return this;
    }

    public PastActivityBOBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public PastActivityBOBuilder withStuId(String stuId) {
        this.stuId = stuId;
        return this;
    }

    public PastActivityBOBuilder withUndistributedStamp(Long undistributedStamp) {
        this.undistributedStamp = undistributedStamp;
        return this;
    }

    public PastActivityBOBuilder withPastSchoolActivity(Long pastSchoolActivity) {
        this.pastSchoolActivity = pastSchoolActivity;
        return this;
    }

    public PastActivityBOBuilder withPastLectureActivity(Long pastLectureActivity) {
        this.pastLectureActivity = pastLectureActivity;
        return this;
    }

    public PastActivityBOBuilder withPastVolunteerActivityTime(Long pastVolunteerActivityTime) {
        this.pastVolunteerActivityTime = pastVolunteerActivityTime;
        return this;
    }

    public PastActivityBOBuilder withPastPracticeActivity(Long pastPracticeActivity) {
        this.pastPracticeActivity = pastPracticeActivity;
        return this;
    }

    public PastActivityBO build() {
        PastActivityBO pastActivityBO = new PastActivityBO();
        pastActivityBO.setPastActivityId(pastActivityId);
        pastActivityBO.setUserId(userId);
        pastActivityBO.setStuId(stuId);
        pastActivityBO.setUndistributedStamp(undistributedStamp);
        pastActivityBO.setPastSchoolActivity(pastSchoolActivity);
        pastActivityBO.setPastLectureActivity(pastLectureActivity);
        pastActivityBO.setPastVolunteerActivityTime(pastVolunteerActivityTime);
        pastActivityBO.setPastPracticeActivity(pastPracticeActivity);
        return pastActivityBO;
    }
}

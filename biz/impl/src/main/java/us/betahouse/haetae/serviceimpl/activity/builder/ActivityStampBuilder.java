/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.builder;

import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.util.utils.AssertUtil;

/**
 * 活动章构建者
 *
 * @author dango.yxm
 * @version : ActivityStampBuilder.java 2018/11/25 1:55 PM dango.yxm
 */
final public class ActivityStampBuilder {

    private ActivityRecordBO activityRecordBO;

    private ActivityBO activityBO;

    public static ActivityStampBuilder getInstance(){
        return new ActivityStampBuilder();
    }

    public ActivityStamp build(){
        AssertUtil.assertNotNull(activityBO, "活动不存在");
        AssertUtil.assertNotNull(activityBO, "活动记录不存在");
        ActivityStamp activityStamp = new ActivityStamp();
        activityStamp.setActivityRecordId(activityRecordBO.getActivityRecordId());
        activityStamp.setActivityId(activityRecordBO.getActivityId());
        activityStamp.setUserId(activityRecordBO.getUserId());
        activityStamp.setScannerUserId(activityRecordBO.getScannerUserId());
        activityStamp.setTime(activityRecordBO.getTime());
        activityStamp.setType(activityRecordBO.getType());
        activityStamp.setStatus(activityRecordBO.getStatus());
        activityStamp.setTerm(activityRecordBO.getTerm());
        activityStamp.setGrades(activityRecordBO.getGrades());
        activityStamp.setExtInfo(activityRecordBO.getExtInfo());
        // 活动信息补充
        activityStamp.setActivityName(activityBO.getActivityName());
        activityStamp.setStartTime(activityBO.getStart());
        activityStamp.setEndTime(activityBO.getEnd());
        activityStamp.setLocation(activityBO.getLocation());
        activityStamp.setOrganizationMessage(activityBO.getOrganizationMessage());
        activityStamp.setScore(activityBO.getScore());
        return activityStamp;
    }

    private ActivityStampBuilder() {
    }

    public ActivityStampBuilder withActivityRecordBO(ActivityRecordBO activityRecordBO) {
        this.activityRecordBO = activityRecordBO;
        return this;
    }

    public ActivityStampBuilder withActivityBO(ActivityBO activityBO) {
        this.activityBO = activityBO;
        return this;
    }
}

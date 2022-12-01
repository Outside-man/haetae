/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.activity.manager.stamp;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.model.DurationStampRecord;
import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.util.utils.AssertUtil;

import java.util.List;

/**
 * @author MessiahJK
 * @version : PartyTimeActivityStampService.java 2019/04/03 10:46 MessiahJK
 */
public class PartyTimeActivityStampService implements StampService {

    @Autowired
    protected ActivityRecordManager activityRecordManager;

    @Override
    public StampRecord parseStampRecord(List<ActivityStamp> activityStamps) {
        DurationStampRecord durationStampRecord = new DurationStampRecord(activityStamps);
        durationStampRecord.countVolunteerTotalTime();
        return durationStampRecord;
    }

    @Override
    public void batchStamp(ActivityStampRequest request, List<String> userIds, ActivityBO activityBO) {
        AssertUtil.assertNotNull(request.getTime(), "党员志愿时长不能为空");

        // 批量盖章
        activityRecordManager.batchCreate(request, userIds);
    }
}

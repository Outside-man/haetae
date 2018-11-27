/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.stamp;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.model.DurationStampRecord;
import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.util.utils.AssertUtil;

import java.util.List;


/**
 * 义工章服务
 *
 * @author dango.yxm
 * @version : SchoolActivityStampService.java 2018/11/27 1:50 AM dango.yxm
 */
public class VolunteerWorkStampService implements StampService {

    @Autowired
    protected ActivityRecordManager activityRecordManager;

    @Override
    public StampRecord parseStampRecord(List<ActivityStamp> activityStamps) {
        DurationStampRecord durationStampRecord = new DurationStampRecord(activityStamps);
        durationStampRecord.countVolunteerTotalTime();
        return durationStampRecord;
    }

    @Override
    public void batchStamp(ActivityStampRequest request, List<String> userIds) {
        AssertUtil.assertNotNull(request.getTime(), "义工时长不能为空");

        // 批量盖章
        activityRecordManager.batchCreate(request, userIds);
    }
}

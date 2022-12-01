/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.stamp;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.enums.ActivityRecordExtInfoKey;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.model.DurationStampRecord;
import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.List;
import java.util.Objects;


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
        // 义工活动记录需要读取义工名称
        CollectionUtils.toStream(activityStamps).filter(Objects::nonNull)
                .forEach(activityStamp -> {
                    // 需要填充义工活动名称
                    String volunteerName = activityStamp.fetchExtInfo(ActivityRecordExtInfoKey.VOLUNTEER_WORK_NAME);
                    if (StringUtils.isNotBlank(volunteerName)) {
                        activityStamp.setActivityName(volunteerName);
                    } else {
                        // 如果出现没有义工名称 需要报出异常
                        LoggerUtil.error(LOGGER, "义工活动记录没有义工活动名称, activityRecordId={0}", activityStamp.getActivityRecordId());
                    }
                });


        DurationStampRecord durationStampRecord = new DurationStampRecord(activityStamps);
        durationStampRecord.countVolunteerTotalTime();
        return durationStampRecord;
    }

    @Override
    public void batchStamp(ActivityStampRequest request, List<String> userIds, ActivityBO activityBO) {
        AssertUtil.assertNotNull(request.getTime(), "义工时长不能为空");
        AssertUtil.assertStringNotBlank(request.getVolunteerWorkName(), "义工名称不能为空");

        // 批量盖章
        activityRecordManager.batchCreate(request, userIds);
    }
}

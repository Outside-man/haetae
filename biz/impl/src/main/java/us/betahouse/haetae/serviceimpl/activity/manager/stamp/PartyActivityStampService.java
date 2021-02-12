/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.activity.manager.stamp;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.service.ActivityRecordRepoService;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.ActivityRecordBO;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.util.utils.AssertUtil;

import java.util.List;

/**
 * @author MessiahJK
 * @version : PartyActivityStampService.java 2019/04/03 10:42 MessiahJK
 */
public class PartyActivityStampService implements StampService {

    @Autowired
    protected ActivityRecordManager activityRecordManager;

    @Autowired
    protected ActivityRecordRepoService activityRecordRepoService;

    @Override
    public void batchStamp(ActivityStampRequest request, List<String> userIds, ActivityBO activityBO) {
        // 校验活动是否有效
        AssertUtil.assertTrue(activityBO.canStamp(), "活动不在进行中");
        // 获取参加过的活动记录
        List<ActivityRecordBO> joinedActivityRecords = activityRecordRepoService.parseJoinUserIds(request.getActivityId(), userIds);
        // 批量盖章
        activityRecordManager.batchCreate(request, userIds);
    }
}

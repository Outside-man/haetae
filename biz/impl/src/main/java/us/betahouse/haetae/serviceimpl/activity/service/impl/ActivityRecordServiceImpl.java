/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;

import java.util.List;

/**
 * 活动盖章服务
 * @author MessiahJK
 * @version : ActivityRecordServiceImpl.java 2018/11/22 20:56 MessiahJK
 */
@Service
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Autowired
    ActivityRecordManager activityRecordManager;

    @Override
    @VerifyPerm(ActivityPermType.ACTIVITY_STAMPER)
    public ActivityRecordBO create(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.create(request);
    }

    @Override
    public List<ActivityRecordBO> findByUserId(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.findByUserId(request.getUserId());
    }

    @Override
    public List<ActivityRecordBO> findByUserIdAndType(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.findByUserIdAndType(request.getUserId(), request.getType());
    }

    @Override
    public Long countByActivityId(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.countByActivityId(request.getActivityId());
    }
}

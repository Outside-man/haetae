/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.activity.request.ActivityRecordRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * @author MessiahJK
 * @version : ActivityRecordServiceImpl.java 2018/11/22 20:56 MessiahJK
 */
public class ActivityRecordServiceImpl implements ActivityRecordService {
    @Autowired
    ActivityRecordManager activityRecordManager;

    @Override
    public ActivityRecordBO create(ActivityRecordRequest request, OperateContext context) {
        return activityRecordManager.create(request);
    }

    @Override
    public List<ActivityRecordBO> findByUserId(ActivityRecordRequest request, OperateContext context) {
        return activityRecordManager.findByUserId(request.getUserId());
    }

    @Override
    public List<ActivityRecordBO> findByUserIdAndType(ActivityRecordRequest request, OperateContext context) {
        return activityRecordManager.findByUserIdAndType(request.getUserId(), request.getType());
    }

    @Override
    public Long countByActivityId(ActivityRecordRequest request, OperateContext context) {
        return activityRecordManager.countByActivityId(request.getActivityId());
    }
}

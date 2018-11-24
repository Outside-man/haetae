/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * @author MessiahJK
 * @version : ActivityServiceImpl.java 2018/11/22 20:56 MessiahJK
 */
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityManager activityManager;
    @Override
    public ActivityBO create(ActivityRequest request, OperateContext context) {
        return activityManager.create(request);
    }

    @Override
    public List<ActivityBO> findAll(OperateContext context) {
        return activityManager.findAll();
    }

    @Override
    public ActivityBO update(ActivityRequest request, OperateContext operateContext) {
        return activityManager.update(request);
    }

    @Override
    public ActivityBO changeStatus(ActivityRequest request, OperateContext operateContext) {

        return activityManager.changeStatus(request.getActivityId(), request.getMotion());
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityExtInfoKey;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.constant.PermExInfokey;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.request.PermManageRequest;
import us.betahouse.haetae.user.user.builder.PermBOBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : ActivityServiceImpl.java 2018/11/22 20:56 MessiahJK
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityManager activityManager;
    @Autowired
    PermManager permManager;

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_CREATE)
    public ActivityBO create(ActivityManagerRequest request, OperateContext context) {
        PermBO permBO=PermBOBuilder.getInstance(ActivityPermType.ACTIVITY_STAMPER, request.getActivityName()+"盖章权限").build();
        PermManageRequest permManageRequest=new PermManageRequest();
        permManageRequest.setPermBO(permBO);
        List<String> userList=new ArrayList<>();
        userList.add(request.getUserId());
        permManageRequest.setUserId(userList);
        permBO=permManager.createPerm(permManageRequest);
        request.putExtInfo(ActivityExtInfoKey.ACTIVITY_STAMP_PERM, permBO.getPermId());
        ActivityBO activityBO= activityManager.create(request);
        permManageRequest.putExtInfo(PermExInfokey.ACTIVITY_ID, activityBO.getActivityId());
        permManageRequest.setPermBO(permBO);
        permManager.createPerm(permManageRequest);
        return activityBO;
    }

    @Override
    public List<ActivityBO> findAll(OperateContext context) {
        return activityManager.findAll();
    }

    @Override
    public ActivityBO update(ActivityManagerRequest request, OperateContext operateContext) {
        return activityManager.update(request);
    }

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_PUBLISH)
    public ActivityBO changeStatus(ActivityManagerRequest request, OperateContext operateContext) {
        return activityManager.changeStatus(request.getActivityId(), request.getMotion());
    }

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_CREATE)
    public ActivityBO pass(ActivityManagerRequest request, OperateContext operateContext) {
        return activityManager.changeStatus(request.getActivityId(), "pass");
    }

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_PUBLISH)
    public ActivityBO publish(ActivityManagerRequest request, OperateContext operateContext) {
        return activityManager.changeStatus(request.getActivityId(), "publish");
    }

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_FINISH)
    public ActivityBO finish(ActivityManagerRequest request, OperateContext operateContext) {
        return activityManager.changeStatus(request.getActivityId(), "finish");
    }

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_RESTART)
    public ActivityBO republish(ActivityManagerRequest request, OperateContext operateContext) {
        return activityManager.changeStatus(request.getActivityId(), "republish");
    }

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_FINISH)
    public ActivityBO remove(ActivityManagerRequest request, OperateContext operateContext) {
        return activityManager.changeStatus(request.getActivityId(), "remove");
    }
}

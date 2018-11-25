/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.builder.ActivityBOBuilder;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.activity.status.activitystatus.*;
import us.betahouse.haetae.activity.utils.ActivityUtil;

import java.util.Date;
import java.util.List;

/**
 * 活动管理器实现
 *
 * @author MessiahJK
 * @version : ActivityManagerImpl.java 2018/11/22 23:54 MessiahJK
 */
@Service
public class ActivityManagerImpl implements ActivityManager {
    @Autowired
    private ActivityRepoService activityRepoService;

    /**
     * 创建活动
     *
     * @param request
     * @return
     */
    @Override
    public ActivityBO create(ActivityRequest request) {
        int time = 0;
        if (request.getDefaultTime() != null) {
            time = (int) Math.round(request.getDefaultTime() * 10);
        }
        if(request.getStart()==null){
            request.setStart(0L);
        }
        if(request.getEnd()==null){
            request.setEnd(0L);
        }
        ActivityBOBuilder builder=ActivityBOBuilder.getInstance()
                .withActivityName(request.getActivityName())
                .withType(request.getType())
                .withOrganizationMessage(request.getOrganizationMessage())
                .withLocation(request.getLocation())
                .withDefaultTime(time)
                .withStart(new Date(request.getStart()))
                .withEnd(new Date(request.getEnd()))
                .withScore(request.getScore())
                .withDescription(request.getDescription())
                .withUserId(request.getUserId())
                .withState(request.getState())
                .withTerm(request.getTerm())
                .withExtInfo(request.getExtInfo());

        return activityRepoService.createActivity(builder.build());
    }

    /**
     * 查找所有活动
     *
     * @return
     */
    @Override
    public List<ActivityBO> findAll() {
        return activityRepoService.queryAllActivity();
    }

    /**
     * 更新活动
     *
     * @param request
     * @return
     */
    @Override
    public ActivityBO update(ActivityRequest request) {
        int time = 0;
        if (request.getDefaultTime() != null) {
            time = (int) Math.round(request.getDefaultTime() * 10);
        }
        ActivityBOBuilder builder=ActivityBOBuilder.getInstance()
                .withActivityId(request.getActivityId())
                .withActivityName(request.getActivityName())
                .withType(request.getType())
                .withOrganizationMessage(request.getOrganizationMessage())
                .withLocation(request.getLocation())
                .withDefaultTime(time)
                .withStart(new Date(request.getStart()))
                .withEnd(new Date(request.getEnd()))
                .withScore(request.getScore())
                .withDescription(request.getDescription())
                .withUserId(request.getUserId())
                .withState(request.getState())
                .withTerm(request.getTerm())
                .withExtInfo(request.getExtInfo());
        return activityRepoService.createActivity(builder.build());
    }

    /**
     * 改变活动状态
     *
     * @param activityId
     * @param motion
     * @return
     */
    @Override
    public ActivityBO changeStatus(String activityId, String motion) {
        ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityId);
        String status = activityBO.getState();
        Boolean pd;
        ActivityState activityState = null;
        switch (status) {
            case "Approved":
                activityState = new ApprovedState();
                break;
            case "Canceled":
                activityState = new CanceledState();
                break;
            case "Finished":
                activityState = new FinishedState();
                break;
            case "Published":
                activityState = new PublishedState();
                break;
            case "Restore":
                activityState = new RestoreState();
                break;
            default:
                return null;
        }
        ActivityStateManager activityStateManager = new ActivityStateManager(activityState);
        switch (motion) {
            case "pass":
                pd = activityStateManager.pass();
                break;
            case "publish":
                pd = activityStateManager.publish();
                break;
            case "finish":
                pd = activityStateManager.finish();
                break;
            case "republish":
                pd = activityStateManager.republish();
                break;
            case "remove":
                pd = activityStateManager.remove();
                break;
            default:
                return null;
        }
        if (pd) {
            activityBO.setState(activityStateManager.getActivityState().getActivityState().getDesc());
            activityRepoService.updateActivity(activityBO);
            return activityBO;
        } else {
            return null;
        }
    }

    @Override
    public void chickAll() {
        List<ActivityBO> activityBOList = activityRepoService.queryAllActivity();
        for (ActivityBO activityBO : activityBOList) {
            if (ActivityUtil.isFinish(activityBO)) {
                activityBO.setState(ActivityStateEnum.FINISHED.getDesc());
                activityRepoService.updateActivity(activityBO);
            }
        }
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.builder.ActivityBOBuilder;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.utils.AssertUtil;

import java.util.ArrayList;
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

    /**
     * 系统结束标志
     */
    private final static String SYSTEM_FINISH_SIGN = "systemFinish";

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
        if (request.getStart() == null) {
            request.setStart(0L);
        }
        if (request.getEnd() == null) {
            request.setEnd(0L);
        }
        ActivityBOBuilder builder = ActivityBOBuilder.getInstance()
                .withActivityName(request.getActivityName())
                .withType(request.getType())
                .withOrganizationMessage(request.getOrganizationMessage())
                .withLocation(request.getLocation())
                .withStart(new Date(request.getStart()))
                .withEnd(new Date(request.getEnd()))
                .withScore(request.getScore())
                .withDescription(request.getDescription())
                .withCreatorId(request.getUserId())
                .withState(ActivityStateEnum.APPROVED.getCode())
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

    @Override
    public List<ActivityBO> findByState(ActivityStateEnum state) {
        AssertUtil.assertNotNull(state, CommonResultCode.SYSTEM_ERROR);
        return activityRepoService.queryActivitiesByState(state.getCode());
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
        ActivityBOBuilder builder = ActivityBOBuilder.getInstance()
                .withActivityId(request.getActivityId())
                .withActivityName(request.getActivityName())
                .withType(request.getType())
                .withOrganizationMessage(request.getOrganizationMessage())
                .withLocation(request.getLocation())
                .withStart(new Date(request.getStart()))
                .withEnd(new Date(request.getEnd()))
                .withScore(request.getScore())
                .withDescription(request.getDescription())
                .withCreatorId(request.getUserId())
                .withState(request.getState())
                .withTerm(request.getTerm())
                .withExtInfo(request.getExtInfo());
        return activityRepoService.updateActivity(builder.build());
    }

    @Override
    public List<ActivityBO> systemFinishActivity() {
        List<ActivityBO> activityBOList = activityRepoService.queryActivitiesByState(ActivityStateEnum.PUBLISHED.getCode());
        List<ActivityBO> systemFinishActivities = new ArrayList<>();
        for (ActivityBO activityBO : activityBOList) {
            if (activityBO.canFinish()) {
                activityBO.setState(ActivityStateEnum.FINISHED.getCode());
                activityBO.putExtInfo(SYSTEM_FINISH_SIGN, SYSTEM_FINISH_SIGN);
                systemFinishActivities.add(activityRepoService.updateActivity(activityBO));
            }
        }
        return systemFinishActivities;
    }
}

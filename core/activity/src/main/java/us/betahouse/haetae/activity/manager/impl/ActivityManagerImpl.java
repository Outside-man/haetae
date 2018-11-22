/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.request.ActivityRequest;

import java.util.List;

/**
 * 活动管理器实现
 *
 * @author MessiahJK
 * @version : ActivityManagerImpl.java 2018/11/22 23:54 MessiahJK
 */
public class ActivityManagerImpl implements ActivityManager {
    @Autowired
    ActivityRepoService activityRepoService;

    /**
     * 创建活动
     *
     * @param request
     * @return
     */
    @Override
    public ActivityBO create(ActivityRequest request) {
        ActivityBO activityBO=new ActivityBO();
        activityBO.setActivityName(request.getActivityName());
        activityBO.setType(request.getType());
        activityBO.setOrganizationMessage(request.getOrganizationMessage());
        activityBO.setLocation(request.getLocation());
        request.setDefaultTime(request.getDefaultTime()*10);
        activityBO.setDefaultTime(request.getDefaultTime().intValue());
        activityBO.setStart(request.getStart());
        activityBO.setEnd(request.getEnd());
        activityBO.setScore(request.getScore());
        activityBO.setDescription(request.getDescription());
        activityBO.setUserId(request.getUserId());
        activityBO.setState(request.getState());
        activityBO.setTeam(request.getTeam());
        activityBO.setExtInfo(request.getExtInfo());
        return activityRepoService.createActivity(activityBO);
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
        ActivityBO activityBO=new ActivityBO();
        activityBO.setActivityId(request.getActivityId());
        activityBO.setActivityName(request.getActivityName());
        activityBO.setType(request.getType());
        activityBO.setOrganizationMessage(request.getOrganizationMessage());
        activityBO.setLocation(request.getLocation());
        request.setDefaultTime(request.getDefaultTime()*10);
        activityBO.setDefaultTime(request.getDefaultTime().intValue());
        activityBO.setStart(request.getStart());
        activityBO.setEnd(request.getEnd());
        activityBO.setScore(request.getScore());
        activityBO.setDescription(request.getDescription());
        activityBO.setUserId(request.getUserId());
        activityBO.setState(request.getState());
        activityBO.setTeam(request.getTeam());
        activityBO.setExtInfo(request.getExtInfo());
        return activityRepoService.createActivity(activityBO);
    }
}

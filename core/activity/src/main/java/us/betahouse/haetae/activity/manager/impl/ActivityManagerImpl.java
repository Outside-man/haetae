/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.builder.ActivityBOBuilder;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.PastActivityBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.utils.AssertUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public PageList<ActivityBO> find(ActivityRequest request) {
        //順序
        String asc ="ASC";
        if(asc.equals(request.getOrderRule())){
            return activityRepoService.queryActivityByTermAndStateAndTypePagerASC(request.getTerm(), request.getState(), request.getType(), request.getPage(), request.getLimit());
        }else{
            return activityRepoService.queryActivityByTermAndStateAndTypePagerDESC(request.getTerm(), request.getState(), request.getType(), request.getPage(), request.getLimit());
        }
    }


    @Override
    public PastActivityBO findPast(ActivityRequest request) {
        if(StringUtils.isNotBlank(request.getUserId())){
            return activityRepoService.getPastByUserId(request.getUserId());
        }
        if(StringUtils.isNotBlank(request.getStuId())){
            return activityRepoService.getPastByStuId(request.getStuId());
        }
        return null;
    }

    @Override
    public PastActivityBO createPast(ActivityRequest request) {
        PastActivityBO pastActivityBO = new PastActivityBO();
        if(activityRepoService.getPastByUserId(request.getUserId())!=null){
            return activityRepoService.getPastByUserId(pastActivityBO.getUserId());
        }
        pastActivityBO.setUserId(request.getUserId());
        pastActivityBO.setUndistributedStamp(request.getUndistributedStamp());
        pastActivityBO.setPastSchoolActivity(request.getPastSchoolActivity());
        pastActivityBO.setPastLectureActivity(request.getPastLectureActivity());
        pastActivityBO.setPastVolunteerActivityTime(request.getPastVolunteerActivityTime());
        pastActivityBO.setPastPracticeActivity(request.getPastPracticeActivity());
        pastActivityBO.setStuId(request.getStuId());
        return activityRepoService.createPastActivity(pastActivityBO);
    }

    @Override
    public PastActivityBO createPast(PastActivityBO pastActivityBO) {
        if(activityRepoService.getPastByUserId(pastActivityBO.getUserId())!=null){
            return activityRepoService.getPastByUserId(pastActivityBO.getUserId());
        }
        return activityRepoService.createPastActivity(pastActivityBO);
    }

    @Override
    public PastActivityBO updatePast(ActivityRequest request) {
        return activityRepoService.updatePastActivity(request.getUserId(), request.getPastActivityBO());
    }

    @Override
    public PageList<ActivityBO> findByUserId(ActivityRequest request) {
        return activityRepoService.queryActivityByUserId(request.getUserId(),request.getPage(), request.getLimit());
    }

    @Override
    public PageList<ActivityBO> findApproved(ActivityRequest request) {
        return activityRepoService.queryApproved(request.getState(),request.getStuId(),request.getActivityName(),
                request.getOrganizationMessage(),request.getPage(), request.getLimit());
    }

    @Override
    public PageList<ActivityBO> findApprovedAddTime(ActivityRequest request) throws ParseException {
        return activityRepoService.queryApprovedAddTime(request.getState(),request.getStuId(),request.getActivityName(),
                request.getOrganizationMessage(),request.getStart(),request.getEnd(),request.getPage(), request.getLimit());
    }

    @Override
    public PageList<ActivityBO> findCreatedByWeek(ActivityRequest request) {
        String asc ="ASC";
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.DAY_OF_WEEK);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        Date date=new Date();
        long now=date.getTime();
        //周日由1改为8
        if(week==1){
            week=8;
        }
        long Monday=now-1000L*60*60*24*(week-2)-1000*60*60*hour-1000*60*minute-1000*second;
        date= new Date(Monday);
        if(asc.equals(request.getOrderRule())){
            return activityRepoService.queryCreatedByWeekPagerASC(date, request.getPage(), request.getLimit(),request.getActivityName());
        }else{
            return activityRepoService.queryCreatedByWeekPagerDESC(date,request.getPage(), request.getLimit(),request.getActivityName());
        }
    }
    @Override
    public PageList<ActivityBO> findApprovedByWeek(ActivityRequest request) {
        String asc ="ASC";
        Calendar c = Calendar.getInstance();
        int week = c.get(Calendar.DAY_OF_WEEK);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        Date date=new Date();
        long now=date.getTime();
        if(week==1){
            week=8;
        }
        long Monday=now-1000L*60*60*24*(week-2)-1000*60*60*hour-1000*60*minute-1000*second;
        date= new Date(Monday);
        if(asc.equals(request.getOrderRule())){
            return activityRepoService.queryApprovedByWeekPagerASC(date, request.getPage(), request.getLimit(),request.getActivityName());
        }else{
            return activityRepoService.queryApprovedByWeekPagerDESC(date,request.getPage(), request.getLimit(),request.getActivityName());
        }
    }
}

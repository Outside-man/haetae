/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.activity.builder.ActivityBOBuilder;
import us.betahouse.haetae.activity.dal.model.ActivityDO;
import us.betahouse.haetae.activity.dal.repo.ActivityDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.dal.service.impl.ActivityRepoServiceImpl;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.PastActivityBO;
import us.betahouse.haetae.activity.model.common.PageList;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private ActivityDORepo activityDORepo;


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
        if(request.getActivityStampedTimeStart()==null){
            request.setActivityStampedTimeStart(0L);
        }
        if(request.getActivityStampedTimeEnd()==null){
            request.setActivityStampedTimeEnd(0L);
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
                .withActivityStampedStart(new Date(request.getActivityStampedTimeStart()))
                .withActivityStampedEnd(new Date(request.getActivityStampedTimeEnd()))
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
    public void updateStampedTimeByActivityId(ActivityRequest request) {
        Date arg1=new Date(request.getActivityStampedTimeStart());
        Date arg2=new Date(request.getActivityStampedTimeEnd());
        String arg3=request.getActivityId();
        //分别是活动扫章开始时间，活动扫章结束时间，活动id
        activityRepoService.updateActivityStampedTimeByActivityId(arg1,arg2,arg3);
    }

    @Override
    public PageList<ActivityBO> findApprovedActivity(ActivityRequest request) {
        int page=1;
        int size=10;
        String activityName="";
        String organizationName="";
        if(request.getPage()!=null&&request.getPage()>0){
            page=request.getPage();
        }
        if(request.getLimit()!=null&&request.getLimit()>0){
            size=request.getLimit();
        }
        if(request.getActivityName()!=null){
            activityName=request.getActivityName();
        }
        if(request.getOrganizationMessage()!=null){
            organizationName=request.getOrganizationMessage();
        }
        if(request.getStuId()!=null&&!request.getStuId().equals("")){
            if(request.getActivityStampedTimeStart()!=null&&request.getActivityStampedTimeEnd()!=null){
                //全都传入值的情况
                List<ActivityDO> approvedActivities = activityDORepo.findApprovedActivity(activityName, organizationName, request.getStuId(),
                        new Date(request.getActivityStampedTimeStart()), new Date(request.getActivityStampedTimeEnd()),
                        page * size-size, size);
                Long totalEle=activityDORepo.findApprovedActivityNum(activityName, organizationName, request.getStuId(),
                        new Date(request.getActivityStampedTimeStart()), new Date(request.getActivityStampedTimeEnd()));
                return new PageList(activityRepoService.convert(approvedActivities),totalEle,page,size);
            }
            //未传入查询时间的情况
            List<ActivityDO> approvedActivities = activityDORepo.findApprovedActivity(activityName, organizationName,
                    request.getStuId(), page * size, size);
            Long totalEle=activityDORepo.findApprovedActivityNum(activityName, organizationName,request.getStuId());
            return new PageList(activityRepoService.convert(approvedActivities),totalEle,page,size);
        }else if(request.getActivityStampedTimeStart()!=null&&request.getActivityStampedTimeEnd()!=null){
            //未传入stuId的情况
            List<ActivityDO> approvedActivities = activityDORepo.findApprovedActivity(activityName, organizationName,
                    new Date(request.getActivityStampedTimeStart()), new Date(request.getActivityStampedTimeEnd()),
                    page * size-size, size);
            Long totalEle=activityDORepo.findApprovedActivityNum(activityName, organizationName,
                    new Date(request.getActivityStampedTimeStart()), new Date(request.getActivityStampedTimeEnd()));
            return new PageList(activityRepoService.convert(approvedActivities),totalEle,page,size);
        }else {
            //两个都未传入的情况
            List<ActivityDO> approvedActivities = activityDORepo.findApprovedActivity(activityName, organizationName,
                    page * size-size, size);
            Long totalEle=activityDORepo.findApprovedActivityNum(activityName, organizationName);
            return new PageList(activityRepoService.convert(approvedActivities),totalEle,page,size);
        }
    }
}

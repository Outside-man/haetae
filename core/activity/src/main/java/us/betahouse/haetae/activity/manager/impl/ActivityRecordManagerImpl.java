/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.service.ActivityRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.activity.request.ActivityRecordRequest;

import java.util.List;

/**
 * @author MessiahJK
 * @version : ActivityRecordManagerImpl.java 2018/11/23 0:09 MessiahJK
 */
@Service
public class ActivityRecordManagerImpl implements ActivityRecordManager {

    @Autowired
    private ActivityRecordRepoService activityRecordRepoService;

    @Autowired
    private ActivityRepoService activityRepoService;
    /**
     * 创建活动记录
     *
     * @param request
     * @return
     */
    @Override
    public ActivityRecordBO create(ActivityRecordRequest request) {
        ActivityRecordBO activityRecordBO=new ActivityRecordBO();
        activityRecordBO.setActivityId(request.getActivityId());
        activityRecordBO.setUserId(request.getUserId());
        activityRecordBO.setScannerUserId(request.getScannerUserId());
        activityRecordBO.setExtInfo(request.getExtInfo());
        request.setTime(request.getTime()*10);
        activityRecordBO.setTime(request.getTime().intValue());
        activityRecordBO.setType(request.getType());
        activityRecordBO.setStatus(request.getStatus());
        activityRecordBO.setTeam(request.getTeam());
        activityRecordBO.setGrades(request.getGrades());
        if(activityRecordBO.getTime()==0) {
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(request.getActivityId());
            activityRecordBO.setTime(activityBO.getDefaultTime());
        }
        return activityRecordRepoService.createActivityRecord(activityRecordBO);
    }

    /**
     * 通过用户id查询活动记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<ActivityRecordBO> findByUserId(String  userId) {
        return activityRecordRepoService.queryActivityRecordByUserId(userId);
    }

    /**
     * 通过用户id和类型查找活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    @Override
    public List<ActivityRecordBO> findByUserIdAndType(String userId,String type) {
        return activityRecordRepoService.queryActivityRecordByUserIdAndType(userId, type);
    }

    /**
     * 通过活动id统计活动记录条数
     *
     * @param activityId
     * @return
     */
    @Override
    public Long countByActivityId(String activityId) {
        return activityRecordRepoService.countActivityRecordByActivityId(activityId);
    }
}

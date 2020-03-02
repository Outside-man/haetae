/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.builder.ActivityRecordBOBuilder;
import us.betahouse.haetae.activity.dal.service.ActivityRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.basic.ActivityRecordBO;
import us.betahouse.haetae.activity.request.ActivityRecordRequest;
import us.betahouse.util.utils.AssertUtil;

import java.util.ArrayList;
import java.util.Collections;
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
     * 通过用户id查询活动记录
     *
     * @param userId
     * @return
     */
    @Override
    public List<ActivityRecordBO> findByUserId(String userId) {
        return activityRecordRepoService.queryActivityRecordByUserId(userId);
    }

    @Override
    public List<ActivityRecordBO> findByUserIdAndTerm(String userId, String term) {
        return activityRecordRepoService.queryActivityRecordByUserIdAndTerm(userId, term);
    }

    /**
     * 通过用户id和类型查找活动记录
     *
     * @param userId
     * @param type
     * @return
     */
    @Override
    public List<ActivityRecordBO> findByUserIdAndType(String userId, String type) {
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

    @Override
    public List<ActivityRecordBO> batchCreate(ActivityRecordRequest request, List<String> userIds) {
        AssertUtil.assertStringNotBlank(request.getType(), "活动类型不能为空");
        // 活动记录时长处理
        int time = 0;
        if (request.getTime() != null) {
            time = (int) Math.round(request.getTime() * 10);
        }

        ActivityRecordBOBuilder builder = ActivityRecordBOBuilder.getInstance()
                .withActivityId(request.getActivityId())
                .withScannerUserId(request.getScannerUserId())
                .withExtInfo(request.getExtInfo())
                .withTime(time)
                .withType(request.getType())
                .withStatus(request.getStatus())
                .withTerm(request.getTerm())
                .withGrades(request.getGrades());


        List<ActivityRecordBO> activityRecordList = new ArrayList<>();
        for (String userId : userIds) {
            // 绑上用户id
            builder.withUserId(userId);
            activityRecordList.add(builder.build());
        }
        return activityRecordRepoService.batchCreateActivityRecord(activityRecordList);
    }

    @Override
    public List<ActivityRecordBO> fetchUserActivityRecord(String userId, String activityType, String term) {
        return activityRecordRepoService.queryUserTermActivityRecord(userId, Collections.singletonList(activityType), Collections.singletonList(term));
    }

    @Override
    public ActivityRecordBO updateScannerName(String activityRecordId,String scannerName) {
        return activityRecordRepoService.updateScannerName(activityRecordId, scannerName);
    }

    @Override
    public List<ActivityRecordBO> findAll() {
        return activityRecordRepoService.findAll();
    }
}

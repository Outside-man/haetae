/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityStampBuilder;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 活动盖章服务
 *
 * @author MessiahJK
 * @version : ActivityRecordServiceImpl.java 2018/11/22 20:56 MessiahJK
 */
@Service
public class ActivityRecordServiceImpl implements ActivityRecordService {

    @Autowired
    private ActivityRecordManager activityRecordManager;

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_STAMPER)
    public ActivityRecordBO stamp(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.create(request);
    }

    @Override
    public List<ActivityRecordBO> findByUserId(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.findByUserId(request.getUserId());
    }

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_STAMPER)
    public Long countByActivityId(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.countByActivityId(request.getActivityId());
    }

    @Override
    public List<ActivityRecordBO> batchStamp(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.batchCreate(request, request.getUserIds());
    }

    @Override
    public List<ActivityStamp> getUserStamps(ActivityStampRequest request, OperateContext context) {
        AssertUtil.assertStringNotBlank(request.getUserId(), "用户id不能为空");
        AssertUtil.assertStringNotBlank(request.getType(), "活动类型不能为空");

        ActivityStampBuilder stampBuilder = ActivityStampBuilder.getInstance();
        List<ActivityRecordBO> activityRecords = new ArrayList<>();
        // 判断是否请求中带有学期过滤
        if (StringUtils.isBlank(request.getTerm())) {
            activityRecords.addAll(activityRecordManager.findByUserIdAndType(request.getUserId(), request.getType()));
        } else {
            activityRecords.addAll(activityRecordManager.fetchUserActivityRecord(request.getUserId(), request.getType(), request.getTerm()));
        }
        // set 去重
        Set<String> activityIds = CollectionUtils.toStream(activityRecords).filter(Objects::nonNull)
                .map(ActivityRecordBO::getActivityId).collect(Collectors.toSet());
        // TODO dango.yxm 2018年11月25日14:10:44 获取活动信息
        Map<String, ActivityBO> activityMap = new HashMap<>();

        List<ActivityStamp> stamps = new ArrayList<>();
        for (ActivityRecordBO record : activityRecords) {
            stampBuilder.withActivityBO(activityMap.get(record.getActivityId()))
                    .withActivityRecordBO(record);
            stamps.add(stampBuilder.build());
        }
        return stamps;
    }
}

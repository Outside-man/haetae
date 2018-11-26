/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.model.ActivityRecordBO;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityStampBuilder;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityExtInfoKey;
import us.betahouse.haetae.serviceimpl.activity.constant.PermExInfokey;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityPermTypeEnum;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

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

    private final Logger LOGGER = LoggerFactory.getLogger(ActivityRecordServiceImpl.class);

    @Autowired
    private ActivityRecordManager activityRecordManager;

    @Autowired
    private ActivityRepoService activityRepoService;

    @Autowired
    private UserBasicService userBasicService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Override
    public List<String> batchStamp(ActivityStampRequest request, OperateContext context) {
        // 校验盖章权限
        AssertUtil.assertTrue(verifyStampPerm(request), CommonResultCode.FORBIDDEN, "没有该活动的盖章权限");

        // 没有盖章成功的学号
        List<String> notStampStuIds = new ArrayList<>();

        // 盖章的userIds
        List<String> userIds = new ArrayList<>();
        for (String stuId : request.getStuIds()) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuId);
            if (userInfoBO == null) {
                notStampStuIds.add(stuId);
            } else {
                userIds.add(userInfoBO.getUserId());
            }
        }
        // 批量盖章
        activityRecordManager.batchCreate(request, userIds);
        return notStampStuIds;
    }

    @Override
    public List<ActivityStamp> getUserStamps(ActivityStampRequest request, OperateContext context) {
        AssertUtil.assertStringNotBlank(request.getUserId(), "用户id不能为空");
        AssertUtil.assertStringNotBlank(request.getType(), "活动类型不能为空");

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

        // 活动map
        Map<String, ActivityBO> activityMap = new HashMap<>();
        for (String activityId : activityIds) {
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityId);
            if (activityBO == null) {
                LoggerUtil.error(LOGGER, "活动不存在, activityId={0}", activityId);
                throw new BetahouseException(CommonResultCode.SYSTEM_ERROR.getCode(), "活动不存在");
            }
            activityMap.put(activityId, activityBO);
        }

        // 组装活动章
        List<ActivityStamp> stamps = new ArrayList<>();

        ActivityStampBuilder stampBuilder = ActivityStampBuilder.getInstance();
        for (ActivityRecordBO record : activityRecords) {
            stampBuilder.withActivityBO(activityMap.get(record.getActivityId()))
                    .withActivityRecordBO(record);
            stamps.add(stampBuilder.build());
        }
        return stamps;
    }

    @Override
    public Long countByActivityId(ActivityStampRequest request, OperateContext context) {
        return activityRecordManager.countByActivityId(request.getActivityId());
    }

    @Override
    public List<ActivityBO> fetchStampMission(ActivityStampRequest request, OperateContext context) {
        AssertUtil.assertStringNotBlank(request.getScannerUserId(), "用户id不能为空");
        Map<String, PermBO> permMap = userBasicService.fetchUserPerms(request.getScannerUserId());
        List<PermBO> stampPerms = CollectionUtils.toStream(permMap.values())
                .filter(permBO -> StringUtils.equals(permBO.getPermType(), ActivityPermTypeEnum.ACTIVITY_STAMPER.getCode()))
                .collect(Collectors.toList());

        List<String> activityIds = new ArrayList<>();
        for (PermBO permBO : stampPerms) {
            String activityId = permBO.fetchExtInfo(PermExInfokey.ACTIVITY_ID);
            if (StringUtils.isNotBlank(activityId)) {
                activityIds.add(activityId);
            }
        }
        List<ActivityBO> activityMisson = new ArrayList<>();

        return activityRepoService.queryActivityByActivityIds(activityIds);
    }

    /**
     * 校验盖章权限
     *
     * @param request
     * @return
     */
    private boolean verifyStampPerm(ActivityStampRequest request) {
        ActivityBO activity = activityRepoService.queryActivityByActivityId(request.getActivityId());
        String stampPermId = activity.fetchExtInfo(ActivityExtInfoKey.ACTIVITY_STAMP_PERM);
        AssertUtil.assertStringNotBlank(stampPermId, "活动没有盖章权限");
        return userBasicService.verifyPermissionByPermId(request.getScannerUserId(), Collections.singletonList(stampPermId));
    }
}

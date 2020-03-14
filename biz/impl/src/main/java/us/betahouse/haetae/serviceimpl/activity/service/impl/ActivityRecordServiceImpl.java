/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.activity.dal.model.ActivityDO;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;
import us.betahouse.haetae.activity.dal.repo.ActivityDORepo;
import us.betahouse.haetae.activity.dal.repo.ActivityRecordDORepo;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.idfactory.BizIdFactory;
import us.betahouse.haetae.activity.manager.ActivityRecordManager;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.ActivityRecordBO;
import us.betahouse.haetae.activity.model.basic.importModel;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityStampBuilder;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityExtInfoKey;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermExInfoKey;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityPermTypeEnum;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityStampImportTemplateEnum;
import us.betahouse.haetae.serviceimpl.activity.manager.StampManager;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityRecordStatistics;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.user.dal.model.UserInfoDO;
import us.betahouse.haetae.user.dal.repo.UserInfoDORepo;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.CsvUtil;
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

    @Autowired
    private ActivityDORepo activityDORepo;

    @Autowired
    ActivityRecordDORepo activityRecordDORepo;

    @Autowired
    UserInfoDORepo userInfoDORepo;

    @Autowired
    private BizIdFactory activityBizFactory;

    /**
     * 章管理器
     */
    @Autowired
    private StampManager stampManager;

    @Override
    public List<String> batchStamp(ActivityStampRequest request, OperateContext context) {
        // 校验盖章权限
        AssertUtil.assertTrue(verifyStampPerm(request), CommonResultCode.FORBIDDEN, "没有该活动的盖章权限");

        // 没有盖章成功的学号
        List<String> notStampStuIds = new ArrayList<>();

        // 盖章的userIds
        Set<String> userIds = new HashSet<>();
        for (String stuId : request.getStuIds()) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuId);
            if (userInfoBO == null) {
                notStampStuIds.add(stuId);
            } else {
                userIds.add(userInfoBO.getUserId());
            }
        }
        stampManager.batchStamp(request, new ArrayList<>(userIds));
        return notStampStuIds;
    }

    @Override
    public List<String> batchStampJson(importModel[] importModels,ActivityStampRequest request, OperateContext context) {
        List<String> unStampRow = new ArrayList<>();
        for (int i = 0; i < importModels.length; i++) {
            //设置活动id
            ActivityRecordDO activityRecordDO = new ActivityRecordDO();
            ActivityDO activityDO = activityDORepo.findByActivityName(importModels[i].getActivityName());
            if (activityDO == null) {
                unStampRow.add(importModels[i].getRownum());
                continue;
            }
            activityRecordDO.setActivityId(activityDO.getActivityId());
            //设置useid 和scanner_userid
            UserInfoDO userInfoDO = userInfoDORepo.findByStuId(importModels[i].getStuId());
            if (userInfoDO == null) {
                unStampRow.add(importModels[i].getRownum());
                continue;
            }
            activityRecordDO.setUserId(userInfoDO.getUserId());
            //设置审核员id
            activityRecordDO.setScannerUserId(request.getUserId());
            //设置活动类型
            activityRecordDO.setType(activityDO.getType());
            //设置学期
            activityRecordDO.setTerm(TermUtil.getNowTerm());
            //设置时长
            if (importModels[i].getTime() == null) {
                activityRecordDO.setTime(Double.valueOf(0).intValue());
            }else {
                activityRecordDO.setTime(Double.valueOf(importModels[i].getTime()).intValue());
            }
            activityRecordDO.setActivityRecordId(activityBizFactory.getActivityRecordId());
            activityRecordDO.setStatus("ENABLE");
            activityRecordDO.setGmtCreate(new Date());
            activityRecordDORepo.save(activityRecordDO);
        }
        return unStampRow;
    }


    @Override
    public StampRecord getUserStamps(ActivityStampRequest request, OperateContext context) {
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
        Map<String, ActivityBO> activityMap = new HashMap<>(16);
        for (String activityId : activityIds) {
            ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityId);
            if (activityBO == null) {
                LoggerUtil.error(LOGGER, "活动不存在, activityId={0}", activityId);
                ActivityBO activity = new ActivityBO();
                activity.setActivityName("异常活动, 请尽快联系管理员");
                activityMap.put(activityId, activity);
            } else {
                activityMap.put(activityId, activityBO);
            }
        }

        // 组装活动章
        List<ActivityStamp> stamps = new ArrayList<>();

        ActivityStampBuilder stampBuilder = ActivityStampBuilder.getInstance();
        for (ActivityRecordBO record : activityRecords) {
            if (StringUtils.isBlank(record.getScannerName())) {
                String scannerName = userInfoRepoService.queryUserInfoByUserId(record.getScannerUserId()).getRealName();
                activityRecordManager.updateScannerName(record.getActivityRecordId(), scannerName);
                record.setScannerName(scannerName);
            }
            stampBuilder.withActivityBO(activityMap.get(record.getActivityId()))
                    .withActivityRecordBO(record);
            stamps.add(stampBuilder.build());
        }

        return stampManager.parseStampRecord(request.getType(), stamps);
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
            String activityId = permBO.fetchExtInfo(ActivityPermExInfoKey.ACTIVITY_ID);
            if (StringUtils.isNotBlank(activityId)) {
                activityIds.add(activityId);
            }
        }
        List<ActivityBO> activityMission = activityRepoService.queryActivityByActivityIds(activityIds);
        return CollectionUtils.toStream(activityMission).filter(ActivityBO::canStamp).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> importStamp(String url) {
        String[][] csv = CsvUtil.getWithHeader(url);
        AssertUtil.assertEquals(ActivityStampImportTemplateEnum.NAME.getDesc(), csv[0][0].substring(1, csv[0][0].length()));
        AssertUtil.assertEquals(ActivityStampImportTemplateEnum.STU_ID.getDesc(), csv[0][1]);
        AssertUtil.assertEquals(ActivityStampImportTemplateEnum.ACTIVITY_NAME.getDesc(), csv[0][2]);
        List<String> notStampStuIds = new ArrayList<>();

        for (int i = 1; i < csv.length; i++) {
            System.out.println(i + " " + csv[i][0]);
            if (StringUtils.isBlank(csv[i][0])) {
                break;
            }
            ActivityStampRequest request = new ActivityStampRequest();
            ActivityBO activityBO = activityRepoService.queryActivityByActivityName(csv[i][2]);
            activityBO.setState(ActivityStateEnum.RESTARTED.getCode());
            activityRepoService.updateActivity(activityBO);
            request.setActivityId(activityBO.getActivityId());
            String stuId = csv[i][1];
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuId);
            if (userInfoBO == null) {
                notStampStuIds.add(stuId);
                continue;
            }
            request.setScannerUserId("201812010040554783180001201835");
            request.setStatus("ENABLE");
            request.setTerm(activityBO.getTerm());
            List<String> inList = new ArrayList<>();
            inList.add(userInfoBO.getUserId());
            stampManager.batchStamp(request, inList);
            activityBO.setState(ActivityStateEnum.FINISHED.getCode());
            activityRepoService.updateActivity(activityBO);
        }
        return notStampStuIds;
    }

    @Override
    public ActivityRecordStatistics fetchUserRecordStatistics(String userId, String term) {
        UserInfoBO userInfo = userInfoRepoService.queryUserInfoByUserId(userId);
        AssertUtil.assertNotNull(userInfo, "统计活动记录的用户不存在");

        // 组装基础信息
        ActivityRecordStatistics recordStatistics = new ActivityRecordStatistics();
        recordStatistics.setUserId(userId);
        recordStatistics.setStuId(userInfo.getStuId());
        recordStatistics.setRealName(userInfo.getRealName());

        // 组装统计信息
        List<ActivityRecordBO> activityRecords;
        // 判断是否传入学期
        if (StringUtils.isBlank(term)) {
            activityRecords = activityRecordManager.findByUserId(userId);
        } else {
            activityRecords = activityRecordManager.findByUserIdAndTerm(userId, term);
        }
        parseActivityRecordStatistics(recordStatistics, activityRecords);
        return recordStatistics;
    }

    /**
     * 处理 统计结果
     *
     * @param recordStatistics
     * @param activityRecords
     * @return
     */
    private ActivityRecordStatistics parseActivityRecordStatistics(ActivityRecordStatistics recordStatistics, List<ActivityRecordBO> activityRecords) {
        // 构建统计结果
        recordStatistics.initStatisticsKey();
        // 将用户的所有记录统计
        for (ActivityRecordBO activityRecord : activityRecords) {
            recordStatistics.addStatistics(activityRecord.getType());

            // 志愿和义工需要统计时长
            boolean needCountTime = StringUtils.equals(activityRecord.getType(), ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode())
                    || StringUtils.equals(activityRecord.getType(), ActivityTypeEnum.VOLUNTEER_WORK.getCode());
            // 记录时长不为空 才会统计进去
            if (needCountTime && activityRecord.getTime() != null) {
                recordStatistics.addStatisticsTime(activityRecord.getType(), activityRecord.getTime());
            }
        }
        return recordStatistics;
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

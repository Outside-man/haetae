/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.service.ActivityEntryRecordRepoService;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.manager.stamp.StampService;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.LoggerUtil;

import java.util.List;
import java.util.Map;

/**
 * 章管理器
 *
 * @author dango.yxm
 * @version : StampManager.java 2018/11/27 1:40 AM dango.yxm
 */
public class StampManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(StampManager.class);

    @Autowired
    private ActivityRepoService activityRepoService;

    @Autowired
    private ActivityEntryRecordRepoService activityEntryRecordRepoService;

    /**
     * 章服务
     */
    private Map<String, StampService> stampServices;

    /**
     * 组装章记录
     *
     * @param activityType
     * @param activityStamps
     * @return
     */
    public StampRecord parseStampRecord(String activityType, List<ActivityStamp> activityStamps) {
        ActivityTypeEnum type = ActivityTypeEnum.getByCode(activityType);
        AssertUtil.assertNotNull(type, "活动类型不存在");
        return stampServices.get(type.getCode()).parseStampRecord(activityStamps);
    }


    /**
     * 批量盖章
     *
     * @param request
     * @param userIds
     * @return
     */
    public void batchStamp(ActivityStampRequest request, List<String> userIds) {
        AssertUtil.assertStringNotBlank(request.getActivityId(), "活动id不能为空");
        ActivityBO activityBO = activityRepoService.queryActivityByActivityId(request.getActivityId());
        AssertUtil.assertNotNull(activityBO, "活动不存在");
        ActivityTypeEnum type = ActivityTypeEnum.getByCode(activityBO.getType());
        if (type == null) {
            LoggerUtil.error(LOGGER, "活动数据存在异常, activity={0}", activityBO);
            throw new BetahouseException(CommonResultCode.SYSTEM_ERROR, "活动数据存在异常，请尽快联系管理员");
        }
        request.setType(type.getCode());
        stampServices.get(type.getCode()).batchStamp(request, userIds, activityBO);
        for(String userId:userIds){
            activityEntryRecordRepoService.attend(activityBO.getActivityId(),userId);
        }

    }

    public void setStampServices(Map<String, StampService> stampServices) {
        this.stampServices = stampServices;
    }
}

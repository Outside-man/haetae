/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.operate;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityOperationEnum;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.LoggerUtil;

import java.util.Collections;
import java.util.List;

/**
 * 活动发布
 *
 * @author dango.yxm
 * @version : ActivityPublish.java 2018/11/26 2:28 PM dango.yxm
 */
public class ActivityPublish extends CommonActivityOperate {

    @Override
    protected boolean canOperate(ActivityBO activityBO) {
        if (StringUtils.equals(activityBO.getState(), ActivityStateEnum.APPROVED.getCode()) || StringUtils.equals(activityBO.getState(), ActivityStateEnum.RESTARTED.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean skipOperate(ActivityBO activityBO) {
        if (StringUtils.equals(activityBO.getState(), ActivityStateEnum.PUBLISHED.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    protected List<String> fetchVerifyPerms(ActivityBO activityBO) {
        ActivityTypeEnum activityType = ActivityTypeEnum.getByCode(activityBO.getType());
        AssertUtil.assertNotNull(activityType, CommonResultCode.SYSTEM_ERROR.getCode(), "活动类型不存在, 请尽快和管理员联系");
        // 对于活动类型进行 权限分发判断
        switch (activityType) {
            case SCHOOL_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_PUBLISH);
            case LECTURE_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_PUBLISH);
            case VOLUNTEER_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_PUBLISH);
            case PRACTICE_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_PUBLISH);
            case VOLUNTEER_WORK:
                return Collections.singletonList(ActivityPermType.ACTIVITY_PUBLISH);
            case PARTY_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_PUBLISH);
            case PARTY_TIME_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_PUBLISH);
            default:
                LoggerUtil.error(LOGGER, "活动类型不存在 ，activityBO={0}", activityBO);
                throw new BetahouseException(CommonResultCode.SYSTEM_ERROR, "活动类型不存在, 请尽快和管理员联系");
        }
    }


    @Override
    public ActivityBO doOperate(ActivityOperationRequest request) {
        ActivityBO activityBO = request.getActivity();
        activityBO.setState(ActivityStateEnum.PUBLISHED.getCode());
        return activityRepoService.updateActivity(activityBO);
    }

    @Override
    public String getOperate() {
        return ActivityOperationEnum.PUBLISH.getDesc();
    }
}

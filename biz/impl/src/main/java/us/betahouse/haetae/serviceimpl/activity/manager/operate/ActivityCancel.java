/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.operate;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityExtInfoKey;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityOperationEnum;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.LoggerUtil;

import java.util.Collections;
import java.util.List;

/**
 * 活动取消
 *
 * @author dango.yxm
 * @version : ActivityPublish.java 2018/11/26 2:28 PM dango.yxm
 */
public class ActivityCancel extends CommonActivityOperate {

    @Autowired
    private PermManager permManager;

    @Override
    protected boolean canOperate(ActivityBO activityBO) {
        if (StringUtils.equals(activityBO.getState(), ActivityStateEnum.APPROVED.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean skipOperate(ActivityBO activityBO) {
        if (StringUtils.equals(activityBO.getState(), ActivityStateEnum.CANCELED.getCode())) {
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
                return Collections.singletonList(ActivityPermType.ACTIVITY_CREATE);
            case LECTURE_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_CREATE);
            case VOLUNTEER_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_CREATE);
            case PRACTICE_ACTIVITY:
                return Collections.singletonList(ActivityPermType.ACTIVITY_CREATE);
            case VOLUNTEER_WORK:
                return Collections.singletonList(ActivityPermType.ACTIVITY_CREATE);
            default:
                LoggerUtil.error(LOGGER, "活动类型不存在 ，activityBO={0}", activityBO);
                throw new BetahouseException(CommonResultCode.SYSTEM_ERROR, "活动类型不存在, 请尽快和管理员联系");
        }
    }

    @Override
    @Transactional
    public ActivityBO doOperate(ActivityOperationRequest request) {
        ActivityBO activityBO = request.getActivity();
        activityBO.setState(ActivityStateEnum.CANCELED.getCode());
        String activityPermId = activityBO.fetchExtInfo(ActivityExtInfoKey.ACTIVITY_STAMP_PERM);
        // 取消活动需要将权限关系全解除
        if (StringUtils.isNotBlank(activityPermId)) {
            permManager.detachAllUsers(activityPermId);
        }
        return activityRepoService.updateActivity(activityBO);
    }

    @Override
    public String getOperate() {
        return ActivityOperationEnum.CANCEL.getDesc();
    }
}

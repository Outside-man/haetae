/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.operate;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityOperationEnum;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;

/**
 * 活动重启
 *
 * @author dango.yxm
 * @version : ActivityPublish.java 2018/11/26 2:28 PM dango.yxm
 */
public class ActivityRestart extends BaseActivityOperate {

    @Override
    protected boolean canOperate(ActivityBO activityBO) {
        if (StringUtils.equals(activityBO.getState(), ActivityStateEnum.FINISHED.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_RESTART)
    protected ActivityBO doOperate(ActivityOperationRequest request) {
        ActivityBO activityBO = request.getActivity();
        activityBO.setState(ActivityStateEnum.RESTARTED.getCode());
        return activityRepoService.updateActivity(activityBO);
    }

    @Override
    public String getOperate() {
        return ActivityOperationEnum.RESTART.getDesc();
    }
}

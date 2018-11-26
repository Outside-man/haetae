/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.operate;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.util.utils.AssertUtil;

import java.text.MessageFormat;

/**
 * 活动操作
 *
 * @author dango.yxm
 * @version : ActivityOperate.java 2018/11/26 2:27 PM dango.yxm
 */
public abstract class BaseActivityOperate implements ActivityOperate {


    @Autowired
    protected ActivityRepoService activityRepoService;

    /**
     * 活动操作
     *
     * @param activityId
     * @return
     */
    public ActivityBO operate(String activityId, String userId) {
        ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityId);
        AssertUtil.assertNotNull(activityBO, "活动不存在");
        AssertUtil.assertTrue(canOperate(activityBO), MessageFormat.format("该活动目前不能{0}", getOperate()));

        ActivityOperationRequest request = new ActivityOperationRequest();
        request.setActivity(activityBO);

        return doOperate(request);
    }

    /**
     * 能否处理
     *
     * @param activityBO
     * @return
     */
    protected abstract boolean canOperate(ActivityBO activityBO);

    protected abstract ActivityBO doOperate(ActivityOperationRequest request);
}

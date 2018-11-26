/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.operate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.LoggerUtil;

import java.text.MessageFormat;
import java.util.List;

/**
 * 活动操作
 *
 * @author dango.yxm
 * @version : ActivityOperate.java 2018/11/26 2:27 PM dango.yxm
 */
public abstract class CommonActivityOperate implements ActivityOperate {

    protected final Logger LOGGER = LoggerFactory.getLogger(CommonActivityOperate.class);


    @Autowired
    protected ActivityRepoService activityRepoService;

    @Autowired
    private UserBasicService userBasicService;

    /**
     * 活动操作
     *
     * @param activityId
     * @return
     */
    public ActivityBO operate(String activityId, String userId) {
        ActivityBO activityBO = activityRepoService.queryActivityByActivityId(activityId);
        AssertUtil.assertNotNull(activityBO, "活动不存在");

        // 鉴权
        boolean verifyPerm = userBasicService.verifyPermissionByPermType(userId, fetchVerifyPerms());
        if (!verifyPerm) {
            LoggerUtil.warn(LOGGER, "用户无权操作 userId={0}, permType={1}", userId, fetchVerifyPerms());
            throw new BetahouseException(CommonResultCode.FORBIDDEN);
        }

        // 是否需要跳过处理
        if (skipOperate(activityBO)) {
            return activityBO;
        }

        AssertUtil.assertTrue(canOperate(activityBO), MessageFormat.format("该活动目前不能{0}", getOperate()));

        ActivityOperationRequest request = new ActivityOperationRequest();
        request.setActivity(activityBO);
        request.setUserId(userId);

        return doOperate(request);
    }

    /**
     * 能否处理
     *
     * @param activityBO
     * @return
     */
    protected abstract boolean canOperate(ActivityBO activityBO);

    /**
     * 是否需要跳过处理
     *
     * @param activityBO
     * @return
     */
    protected abstract boolean skipOperate(ActivityBO activityBO);

    /**
     * 获取需要鉴权的内容
     *
     * @return
     */
    protected abstract List<String> fetchVerifyPerms();
}

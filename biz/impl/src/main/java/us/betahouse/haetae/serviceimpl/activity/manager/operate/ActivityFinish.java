/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.operate;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityExtInfoKey;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityOperationEnum;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.request.PermManageRequest;
import us.betahouse.util.utils.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 活动结束
 *
 * @author dango.yxm
 * @version : ActivityPublish.java 2018/11/26 2:28 PM dango.yxm
 */
public class ActivityFinish extends CommonActivityOperate {

    @Autowired
    private PermManager permManager;

    @Override
    protected boolean canOperate(ActivityBO activityBO) {
        // 发布的活动可以结束
        if (StringUtils.equals(activityBO.getState(), ActivityStateEnum.PUBLISHED.getCode())) {
            return true;
        }
        // 重启的活动可以结束
        if (StringUtils.equals(activityBO.getState(), ActivityStateEnum.RESTARTED.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean skipOperate(ActivityBO activityBO) {
        if (StringUtils.equals(activityBO.getState(), ActivityStateEnum.FINISHED.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    protected List<String> fetchVerifyPerms() {
        return Collections.singletonList(ActivityPermType.ACTIVITY_FINISH);
    }


    @Override
    @VerifyPerm(permType = ActivityPermType.ACTIVITY_FINISH)
    @Transactional
    public ActivityBO doOperate(ActivityOperationRequest request) {
        ActivityBO activityBO = request.getActivity();
        activityBO.setState(ActivityStateEnum.FINISHED.getCode());
        String activityPermId = activityBO.fetchExtInfo(ActivityExtInfoKey.ACTIVITY_STAMP_PERM);
        // 结束活动时 保留前两个权限关系 其他全去除
        if (StringUtils.isNotBlank(activityPermId)) {
            List<String> userIds = permManager.getPermUsers(activityPermId);
            // 保留两个权限 其他都移除
            permManager.batchUsersUnbindPerms(buildUnbindRequest(activityPermId, CollectionUtils.subList(userIds, 2)));
        }
        return activityRepoService.updateActivity(activityBO);
    }

    @Override
    public String getOperate() {
        return ActivityOperationEnum.FINISH.getDesc();
    }


    /**
     * 生成权限移除请求
     *
     * @param permId
     * @param userIds
     * @return
     */
    private PermManageRequest buildUnbindRequest(String permId, List<String> userIds) {
        PermManageRequest permManageRequest = new PermManageRequest();
        PermBO permBO = new PermBO();
        permBO.setPermId(permId);
        permManageRequest.setUserIds(userIds);
        return permManageRequest;
    }
}

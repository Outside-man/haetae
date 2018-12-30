/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager;

import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityOperationEnum;
import us.betahouse.haetae.serviceimpl.activity.manager.operate.ActivityOperate;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.util.utils.AssertUtil;

import java.util.Map;

/**
 * 活动操作
 *
 * @author dango.yxm
 * @version : ActivityOperateManager.java 2018/11/26 2:52 PM dango.yxm
 */
public class ActivityOperateManager {

    private Map<String, ActivityOperate> operateMap;


    public ActivityBO operate(ActivityManagerRequest request, OperateContext operateContext) {
        AssertUtil.assertNotNull(request);
        AssertUtil.assertStringNotBlank(request.getActivityId(), "操作活动id不能为空");
        AssertUtil.assertStringNotBlank(request.getVerifyUserId(), "操作员id不能为空");


        ActivityOperationEnum operation = ActivityOperationEnum.getByCode(request.getOperation());
        AssertUtil.assertNotNull(operation, "不是有效的操作指令");
        // 分发处理
        return operateMap.get(operation.getCode()).operate(request.getActivityId(), request.getVerifyUserId());
    }

    public void setOperateMap(Map<String, ActivityOperate> operateMap) {
        this.operateMap = operateMap;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.template.RestOperateCallBack;
import us.betahouse.haetae.template.RestOperateTemplate;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动接口
 *
 * @author MessiahJK
 * @version : ActivityController.java 2018/11/25 13:16 MessiahJK
 */
public class ActivityController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    public Result<ActivityBO> add(ActivityRequest request, HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "添加活动", request, new RestOperateCallBack<ActivityBO>() {
            @Override
            public void before(){
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动名不能为空");
                AssertUtil.assertStringNotBlank(request.getTerm(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动学期不能为空");
            }

            @Override
            public Result<ActivityBO> execute() {
                return null;
            }
        });

    }
}

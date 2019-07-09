package us.betahouse.haetae.controller.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.activity.request.ActivityEntryRestRequest;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityEntryQueryRequest;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动报名信息接口
 *
 * @author zjb
 * @version : ActivityEntryController.java 2019/7/8 1:27 zjb
 */
@RestController
@RequestMapping(value = "/activityEntry")
public class ActivityEntryController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityEntryService activityEntryService;

    @CheckLogin
    @GetMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryList> assignPastActivity(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取活动报名信息列表", request, new RestOperateCallBack<ActivityEntryList>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<ActivityEntryList> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityEntryQueryRequest activityEntryQueryRequest = new ActivityEntryQueryRequest(request.getActivityId(), request.getState(), request.getTerm(),
                                                                                                    request.getActivityType(),request.getPage(),request.getLimit());
                    return RestResultUtil.buildSuccessResult(activityEntryService.activityEntryQuery(activityEntryQueryRequest, request.getUserId()), "获取报名信息列表列表成功");
            }
        });
    }
}

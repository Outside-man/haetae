/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.controller.user.UserController;
import us.betahouse.haetae.model.user.request.StamperRequest;
import us.betahouse.haetae.model.user.request.UserActivityStampRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityStampRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityStampStatusEnum;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动章管理
 *
 * @author dango.yxm
 * @version : ActivityStampController.java 2018/11/25 3:28 PM dango.yxm
 */
@RequestMapping(value = "/activityStamp")
@RestController
public class ActivityStampController {

    @Autowired
    private ActivityRecordService activityRecordService;

    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * 校园活动盖章
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping
    @CheckLogin
    @Log(loggerName = LoggerName.USER_DIGEST)
    public Result<List<ActivityStamp>> fetchUserStamp(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "盖活动章", request, new RestOperateCallBack<List<ActivityStamp>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "盖章员id不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertNotNull(request.getParticipants(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "参与者不能为空");
            }

            @Override
            public Result<List<ActivityStamp>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityStampRequestBuilder builder = ActivityStampRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withActivityId(request.getActivityId())
                        .withScannerUserId(request.getUserId())
                        .withUserIds(request.getParticipants())
                        .withTerm(TermUtil.getNowTerm())
                        .withStatus(ActivityStampStatusEnum.ENABLE.getCode());
                List<ActivityStamp> stamps = activityRecordService.batchStamp(builder.build(), context);
                return RestResultUtil.buildSuccessResult(stamps, "获取活动章成功");
            }
        });
    }
}

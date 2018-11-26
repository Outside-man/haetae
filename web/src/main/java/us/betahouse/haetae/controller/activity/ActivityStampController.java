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
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.StamperRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityStampRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityStampStatusEnum;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.activity.request.builder.ActivityManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
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

    @Autowired
    private ActivityService activityService;

    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityStampController.class);

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
    public Result<List<String>> fetchUserStamp(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "盖活动章", request, new RestOperateCallBack<List<String>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "盖章员id不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertNotNull(request.getParticipants(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "参与者不能为空");
            }

            @Override
            public Result<List<String>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityStampRequestBuilder builder = ActivityStampRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withActivityId(request.getActivityId())
                        .withScannerUserId(request.getUserId())
                        .withStuIds(request.getParticipants())
                        .withTerm(TermUtil.getNowTerm())
                        .withStatus(ActivityStampStatusEnum.ENABLE.getCode());
                List<String> notStampStuId = activityRecordService.batchStamp(builder.build(), context);
                if (CollectionUtils.isEmpty(notStampStuId)) {
                    return RestResultUtil.buildSuccessResult(notStampStuId, "盖章成功");
                } else {
                    return RestResultUtil.buildSuccessResult(notStampStuId, MessageFormat.format("部分学号盖章失败, {0}", notStampStuId));
                }
            }
        });
    }

    /**
     * 校园活动盖章 添加盖章员
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping(value = "/stamper")
    @CheckLogin
    @Log(loggerName = LoggerName.USER_DIGEST)
    public Result<List<ActivityStamp>> stamperAdd(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "添加盖章员", request, new RestOperateCallBack<List<ActivityStamp>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getStamperStuId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "盖章员学号不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
            }

            @Override
            public Result<List<ActivityStamp>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequestBuilder builder = ActivityManagerRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withUserId(request.getUserId())
                        .withActivityId(request.getActivityId());
                ActivityManagerRequest activityManagerRequest = builder.build();
                activityManagerRequest.putStamperStuId(request.getStamperStuId());

                activityService.bindStamper(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult("分配盖章员成功");
            }
        });
    }

    /**
     * 获取盖章任务
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = "/mission")
    @CheckLogin
    @Log(loggerName = LoggerName.USER_DIGEST)
    public Result<List<ActivityBO>> getMission(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取盖章任务", request, new RestOperateCallBack<List<ActivityBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
            }

            @Override
            public Result<List<ActivityBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityStampRequestBuilder builder = ActivityStampRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withScannerUserId(request.getUserId());

                List<ActivityBO> activityBOS = activityRecordService.fetchStampMission(builder.build(), context);
                return RestResultUtil.buildSuccessResult(activityBOS, "获取盖章任务成功");
            }
        });
    }
}

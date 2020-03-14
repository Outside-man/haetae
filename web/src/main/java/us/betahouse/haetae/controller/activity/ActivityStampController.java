/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.activity;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.basic.importModel;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.StamperRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityStampRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityStampStatusEnum;
import us.betahouse.haetae.serviceimpl.activity.enums.GradesEnum;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;
import us.betahouse.haetae.serviceimpl.activity.request.builder.ActivityManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
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
@CrossOrigin
@RestController
@RequestMapping(value = "/activityStamp")
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
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<String>> fetchUserStamp(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "盖活动章", request, new RestOperateCallBack<List<String>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "盖章员id不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertNotNull(request.getParticipants(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "参与者不能为空");

                // 社会实践 等第参数强校验
                if (StringUtils.isNotBlank(request.getGrades())) {
                    GradesEnum gradesEnum = GradesEnum.getByCode(request.getGrades());
                    AssertUtil.assertNotNull(gradesEnum, "等第格式错误");
                    request.setGrades(gradesEnum.getCode());
                }
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
                        .withStatus(ActivityStampStatusEnum.ENABLE.getCode())
                        .withGrades(request.getGrades())
                        .withTime(request.getTime());
                ActivityStampRequest stampRequest = builder.build();

                // 义工逻辑 需要传递义工名称
                if (StringUtils.isNotBlank(request.getVolunteerWorkName())) {
                    stampRequest.setVolunteerWorkName(request.getVolunteerWorkName());
                }
                List<String> notStampStuId = activityRecordService.batchStamp(stampRequest, context);
                if (CollectionUtils.isEmpty(notStampStuId)) {
                    return RestResultUtil.buildSuccessResult(notStampStuId, "盖章成功");
                } else {
                    Result<List<String>> result = RestResultUtil.buildResult(RestResultCode.PARTIAL_CONTENT, MessageFormat.format("部分学号盖章失败, {0}", notStampStuId));
                    result.setSuccess(true);
                    result.setData(notStampStuId);
                    return result;
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
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result stamperAdd(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "添加盖章员", request, new RestOperateCallBack<Result>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getStamperStuId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "盖章员学号不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
            }

            @Override
            public Result execute() {
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
     * 校园活动盖章 获取盖章员
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/stamper")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<UserInfoBO>> getStamper(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取盖章员", request, new RestOperateCallBack<List<UserInfoBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
            }

            @Override
            public Result<List<UserInfoBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequestBuilder builder = ActivityManagerRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withUserId(request.getUserId())
                        .withActivityId(request.getActivityId());
                ActivityManagerRequest activityManagerRequest = builder.build();

                List<UserInfoBO> stampers = activityService.getStampers(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult(stampers, "获取盖章员成功");
            }
        });
    }


    /**
     * 校园活动盖章 删除盖章员
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping(value = "/stamper")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result stamperRemove(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "删除盖章员", request, new RestOperateCallBack<Result>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getStamperStuId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "盖章员学号不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
            }

            @Override
            public Result execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequestBuilder builder = ActivityManagerRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withUserId(request.getUserId())
                        .withActivityId(request.getActivityId());
                ActivityManagerRequest activityManagerRequest = builder.build();
                activityManagerRequest.putStamperStuId(request.getStamperStuId());

                activityService.unbindStamper(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult("删除盖章员成功");
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
    @Log(loggerName = LoggerName.WEB_DIGEST)
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

    /**
     * 获取已盖活动章数
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @GetMapping(value = "/count")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<Long> count(StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取已盖活动章数", request, new RestOperateCallBack<Long>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
            }

            @Override
            public Result<Long> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityStampRequestBuilder builder = ActivityStampRequestBuilder.getInstance()
                        .withRequestId(request.getRequestId())
                        .withActivityId(request.getActivityId());
                Long ans = activityRecordService.countByActivityId(builder.build(), context);
                return RestResultUtil.buildSuccessResult(ans, "获取已盖活动章数成功");
            }
        });
    }


    /**
     * 导入活动盖章
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/importStamps")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<String>> importStamps(@RequestBody importModel[] importModels, StamperRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "导入活动盖章", request, new RestOperateCallBack<List<String>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
            }

            @Override
            public Result<List<String>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityStampRequest activityStampRequest=new ActivityStampRequest();
                activityStampRequest.setUserId(request.getUserId());
                List<String> unbathRows=activityRecordService.batchStampJson(importModels,activityStampRequest,context);
                return RestResultUtil.buildSuccessResult(unbathRows, "导入活动盖章成功");
            }
        });
    }


}

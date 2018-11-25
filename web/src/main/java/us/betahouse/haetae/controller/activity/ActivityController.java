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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.activity.request.ActivityRestRequest;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityManagerRequest;
import us.betahouse.haetae.serviceimpl.activity.request.builder.ActivityManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 活动接口
 *
 * @author MessiahJK
 * @version : ActivityController.java 2018/11/25 13:16 MessiahJK
 */
@RestController
public class ActivityController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;

    /**
     * 添加活动
     *
     * @param request
     * @param httpServletRequest
     * @return
     */

    @CheckLogin
    @PostMapping(value = "/activity")
    @Log(loggerName=LoggerName.ACTIVITY_DIGEST)
    public Result<ActivityBO> add(ActivityRestRequest request, HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "增添活动", request, new RestOperateCallBack<ActivityBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动名不能为空");
                AssertUtil.assertStringNotBlank(request.getTerm(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动学期不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<ActivityBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequest activityManagerRequest=ActivityManagerRequestBuilder.getInstance()
                        .withActivityName(request.getActivityName())
                        .withType(request.getType())
                        .withOrganizationMessage(request.getOrganizationMessage())
                        .withLocation(request.getLocation())
                        .withDefaultTime(request.getDefaultTime())
                        .withStart(request.getStart())
                        .withEnd(request.getEnd())
                        .withScore(request.getScore())
                        .withDescription(request.getDescription())
                        .withUserId(request.getUserId())
                        .withState(request.getState())
                        .withTerm(request.getTerm())
                        .build();
                ActivityBO activityBO=activityService.create(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult(activityBO,"成功创建活动");
            }
        });
    }

    /**
     * 获取所有活动列表
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value="/activity/all")
    @Log(loggerName = LoggerName.ACTIVITY_DIGEST)
    public Result<List<ActivityBO>> getActivityList(ActivityRestRequest request, HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "获取活动列表", request, new RestOperateCallBack<List<ActivityBO>>() {

            @Override
            public void before() {
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<ActivityBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                List<ActivityBO> activityBOList=activityService.findAll(context);
                return RestResultUtil.buildSuccessResult(activityBOList,"获取活动列表成功");
            }
        });
    }

    /**
     * 通过活动
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping(value = "activity/pass")
    @Log(loggerName = LoggerName.ACTIVITY_DIGEST)
    public Result<ActivityBO> pass(ActivityRestRequest request,HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "通过活动", request, new RestOperateCallBack<ActivityBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<ActivityBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequest activityManagerRequest=ActivityManagerRequestBuilder.getInstance()
                        .withActivityId(request.getActivityId())
                        .withUserId(request.getUserId())
                        .build();
                ActivityBO activityBO=activityService.pass(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult(activityBO,"活动成功通过");
            }
        });
    }

    /**
     * 发布活动
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping(value = "activity/publish")
    @Log(loggerName = LoggerName.ACTIVITY_DIGEST)
    public Result<ActivityBO> publish(ActivityRestRequest request,HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "发布活动", request, new RestOperateCallBack<ActivityBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<ActivityBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequest activityManagerRequest=ActivityManagerRequestBuilder.getInstance()
                        .withActivityId(request.getActivityId())
                        .withUserId(request.getUserId())
                        .build();
                ActivityBO activityBO=activityService.publish(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult(activityBO,"活动成功发布");
            }
        });
    }
    /**
     * 结束活动
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping(value = "activity/finish")
    @Log(loggerName = LoggerName.ACTIVITY_DIGEST)
    public Result<ActivityBO> finish(ActivityRestRequest request,HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "结束活动", request, new RestOperateCallBack<ActivityBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<ActivityBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequest activityManagerRequest=ActivityManagerRequestBuilder.getInstance()
                        .withActivityId(request.getActivityId())
                        .withUserId(request.getUserId())
                        .build();
                ActivityBO activityBO=activityService.finish(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult(activityBO,"活动成功结束");
            }
        });
    }

    /**
     * 拉起活动
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping(value = "activity/republish")
    @Log(loggerName = LoggerName.ACTIVITY_DIGEST)
    public Result<ActivityBO> republish(ActivityRestRequest request,HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "拉起活动", request, new RestOperateCallBack<ActivityBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<ActivityBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequest activityManagerRequest=ActivityManagerRequestBuilder.getInstance()
                        .withActivityId(request.getActivityId())
                        .withUserId(request.getUserId())
                        .build();
                ActivityBO activityBO=activityService.republish(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult(activityBO,"活动成功拉起");
            }
        });
    }

    /**
     * 取消活动
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping(value = "activity/remove")
    @Log(loggerName = LoggerName.ACTIVITY_DIGEST)
    public Result<ActivityBO> remove(ActivityRestRequest request,HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "取消活动", request, new RestOperateCallBack<ActivityBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动id不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }
            @Override
            public Result<ActivityBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityManagerRequest activityManagerRequest=ActivityManagerRequestBuilder.getInstance()
                        .withActivityId(request.getActivityId())
                        .withUserId(request.getUserId())
                        .build();
                ActivityBO activityBO=activityService.remove(activityManagerRequest, context);
                return RestResultUtil.buildSuccessResult(activityBO,"活动成功取消");
            }
        });
    }
}

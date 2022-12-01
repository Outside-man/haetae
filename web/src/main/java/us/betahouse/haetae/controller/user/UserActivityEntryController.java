package us.betahouse.haetae.controller.user;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.haetae.activity.request.ActivityEntryRecordRequest;
import us.betahouse.haetae.activity.request.ActivityEntryRequest;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.controller.activity.ActivityController;
import us.betahouse.haetae.model.activity.request.ActivityEntryRestRequest;
import us.betahouse.haetae.model.activity.request.ActivitySubscribeRestRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityEntryRecordRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityEntryRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryList;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryPublish;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityBlacklistService;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.utils.SubscribeUtil;
import us.betahouse.haetae.serviceimpl.common.utils.TermUtil;
import us.betahouse.haetae.serviceimpl.schedule.ScheduleTaskMap;
import us.betahouse.haetae.serviceimpl.schedule.dto.RealTask;
import us.betahouse.haetae.serviceimpl.schedule.manager.AccessTokenManage;
import us.betahouse.haetae.serviceimpl.user.service.UserService;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户已报名活动报名信息接口
 *
 * @author zjb
 * @version : UserActivityEntryController.java 2019/7/8 23:18 zjb
 */
@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserActivityEntryController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityEntryService activityEntryService;

    @Autowired
    private ActivityBlacklistService activityBlacklistService;

    @Autowired
    private UserService userService;


    @CheckLogin
    @GetMapping("/registeredActivityEntry")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryList> queryUserRegisteredActivityEntry(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取活动报名信息列表", request, new RestOperateCallBack<ActivityEntryList>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(request.getPage(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "page参数不能为空");
                AssertUtil.assertNotNull(request.getLimit(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "limit参数不能为空");
            }

            @Override
            public Result<ActivityEntryList> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityEntryRequest activityEntryRequest = ActivityEntryRequestBuilder.anActivityEntryRequest()
                        .withActivityId(request.getActivityId())
                        .withState(request.getState())
                        .withTerm(request.getTerm())
                        .withType(request.getActivityType())
                        .withPage(request.getPage())
                        .withLimit(request.getLimit())
                        .build();
                return RestResultUtil.buildSuccessResult(activityEntryService.registeredActivityEntryQuery(activityEntryRequest, request.getUserId()), "获取报名信息列表列表成功");
            }
        });
    }


    /**
     * 活动报名
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping("/signUp")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryRecordBO> signUp(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "活动报名", request, new RestOperateCallBack<ActivityEntryRecordBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityEntryId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名信息id不能为空");
            }

            @Override
            public Result<ActivityEntryRecordBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                if( 0 >= activityBlacklistService.getCreditScoreByUserIdAndTerm(request.getUserId(),TermUtil.getNowTerm())) {
                    return RestResultUtil.buildSuccessResult(null, "信用分不足，不允许报名");
                }
                ActivityEntryRecordRequest activityEntryRecordRequest = ActivityEntryRecordRequestBuilder.anActivityEntryRecordRequest()
                        .withActivityEntryId(request.getActivityEntryId())
                        .withActivityEntryRecordId(request.getActivityEntryRecordId())
                        .withUserId(request.getUserId())
                        .withNote(request.getRecordNote())
                        .withChoose(request.getRecordChoose())
                        .build();
                ActivityEntryRecordBO activityEntryRecordBO = activityEntryService.createActivityEntryRecord(activityEntryRecordRequest);
                if(activityEntryRecordBO == null){
                    return RestResultUtil.buildSuccessResult(null, "人已满");
                }else{
                    return RestResultUtil.buildSuccessResult(activityEntryRecordBO, "报名成功");
                }
            }
        });
    }


    /**
     * 活动取消报名
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @DeleteMapping("/undoSignUp")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryRecordBO> undoSignUp(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "活动取消报名", request, new RestOperateCallBack<ActivityEntryRecordBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityEntryId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名信息id不能为空");
            }

            @Override
            public Result<ActivityEntryRecordBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityEntryRecordRequest activityEntryRecordRequest = ActivityEntryRecordRequestBuilder.anActivityEntryRecordRequest()
                        .withActivityEntryId(request.getActivityEntryId())
                        .withActivityEntryRecordId(request.getActivityEntryRecordId())
                        .withUserId(request.getUserId())
                        .withNote(request.getRecordNote())
                        .withChoose(request.getRecordChoose())
                        .build();
                activityEntryService.undoSignUp(activityEntryRecordRequest);
                return RestResultUtil.buildSuccessResult(null, "取消报名成功");
            }
        });
    }

    /**
     * 增添定时任务
     * @param request
     * @param httpServletRequest
     * @return
     */
       @CheckLogin
       @PostMapping("/Subscribe")
       @Log(loggerName = LoggerName.WEB_DIGEST)
      public Result<ActivityEntryPublish> ActivitySubscribe(ActivitySubscribeRestRequest request , HttpServletRequest httpServletRequest) {
           return RestOperateTemplate.operate(LOGGER, "活动开始信息订阅", request, new RestOperateCallBack<ActivityEntryPublish>() {
               @Override
               public void before() {
                   AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                   AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                   AssertUtil.assertStringNotBlank(request.getSubscribeId(),RestResultCode.ILLEGAL_PARAMETERS.getCode(), "当前订阅id不能为空");
                   AssertUtil.assertStringNotBlank(request.getActivityName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动名不能为空");
                   AssertUtil.assertStringNotBlank(request.getStart(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动开始时间不能为空");
                   AssertUtil.assertStringNotBlank(request.getActivityTime(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动时间不能为空");
                   AssertUtil.assertStringNotBlank(request.getLocation(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动地点不能为空");
               }

               @Override
               public Result<ActivityEntryPublish> execute() {
                   OperateContext context = new OperateContext();
                   context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                   if (request.getAdvanceTime()==null || !StringUtils.isNumeric(request.getAdvanceTime())){
                       request.setAdvanceTime("0");
                   }
                    UserBO userBO =  userService.queryByUserId(request.getUserId(),context);
                   if (userBO==null){
                       return RestResultUtil.buildSuccessResult("用户id错误");
                   }
                   String openid = userBO.getOpenId();
                   ActivityEntryPublish activityEntryPublish = new ActivityEntryPublish();
                   BeanUtils.copyProperties(request,activityEntryPublish);
                   ScheduleTaskMap.getInstance().putMap(Integer.parseInt(request.getAdvanceTime()),request.getPage(),activityEntryPublish,openid);
                   return RestResultUtil.buildSuccessResult(activityEntryPublish, "用户已订阅该活动");
               }
           });
       }

    /**
     * 查询是否有订阅
     */
    @CheckLogin
    @GetMapping("/Subscribe")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryPublish> ActivitySubscribeFind(ActivitySubscribeRestRequest request , HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "活动订阅查询", request, new RestOperateCallBack<ActivityEntryPublish>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getSubscribeId(),RestResultCode.ILLEGAL_PARAMETERS.getCode(), "当前订阅id不能为空");
            }

            @Override
            public Result<ActivityEntryPublish> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                return RestResultUtil.buildSuccessResult(ScheduleTaskMap.getInstance().ifExist(request.getSubscribeId()) , "查询成功");
            }
        });
    }
    /**
     * 取消订阅
     */
    @CheckLogin
    @DeleteMapping("/Subscribe")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryPublish> ActivitySubscribeDel(ActivitySubscribeRestRequest request , HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "活动订阅查询", request, new RestOperateCallBack<ActivityEntryPublish>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getSubscribeId(),RestResultCode.ILLEGAL_PARAMETERS.getCode(), "当前订阅id不能为空");
            }

            @Override
            public Result<ActivityEntryPublish> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                return RestResultUtil.buildSuccessResult(ScheduleTaskMap.getInstance().delMap(request.getSubscribeId()) , "取消订阅成功");
            }
        });
    }

    /**
     *
     * 获取报名记录
     * @param request
     * @param httpServletRequest
     * @return
     */
       @CheckLogin
       @GetMapping("/getActivityEntryRecord")
       @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<ActivityEntryRecordBO> getRecord(ActivityEntryRestRequest request, HttpServletRequest httpServletRequest) {
           return RestOperateTemplate.operate(LOGGER, "查询报名记录", request, new RestOperateCallBack<ActivityEntryRecordBO>() {
               @Override
               public void before() {
                   AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                   AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                   AssertUtil.assertStringNotBlank(request.getActivityEntryId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "报名信息id不能为空");
               }

               @Override
               public Result<ActivityEntryRecordBO> execute() {
                   OperateContext context = new OperateContext();
                   context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                   ActivityEntryRecordRequest activityEntryRecordRequest = ActivityEntryRecordRequestBuilder.anActivityEntryRecordRequest()
                           .withActivityEntryId(request.getActivityEntryId())
                           .withUserId(request.getUserId())
                           .build();
                   ActivityEntryRecordBO result =  activityEntryService.findByActivityEntryIdAndUserId(activityEntryRecordRequest);
                   return RestResultUtil.buildSuccessResult(result, "查询成功");
               }
           });
       }



}

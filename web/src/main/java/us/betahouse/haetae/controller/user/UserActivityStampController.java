/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.user;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.activity.dal.service.ActivityRepoService;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.UserActivityStampRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityStampRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户活动章操作
 *
 * @author dango.yxm
 * @version : UserStampController.java 2018/11/25 1:07 PM dango.yxm
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserActivityStampController {
    
    @Autowired
    private ActivityRecordService activityRecordService;
    
    @Autowired
    private ActivityRepoService activityRepoService;
    
    /**
     * 日志
     */
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @GetMapping(value = "/activityStamp")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<StampRecord> fetchUserStamp(UserActivityStampRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户查询活动章", request, new RestOperateCallBack<StampRecord>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动类型不能为空");
            }
            
            @Override
            public Result<StampRecord> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                ActivityStampRequestBuilder builder = ActivityStampRequestBuilder.getInstance()
                        .withTerm(request.getTerm())
                        .withUserId(request.getUserId())
                        .withType(request.getActivityType());
                StampRecord stampRecord = activityRecordService.getUserStamps(builder.build(), context);
                return RestResultUtil.buildSuccessResult(stampRecord, "获取活动章成功");
            }
        });
    }
    
    @GetMapping(value = "/analysisData")
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<Map<String, String>>> analysisUserAllStampData(UserActivityStampRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "用户查询每种活动章和活动报名状态", request, new RestOperateCallBack<List<Map<String, String>>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
                AssertUtil.assertStringNotBlank(request.getActivityType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "活动类型不能为空");
            }
            
            @Override
            public Result<List<Map<String, String>>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                List<Map<String, String>> data = new ArrayList<>();
    
                double[] stampCount = new double[5];
                ActivityStampRequestBuilder builder = ActivityStampRequestBuilder.getInstance()
                        .withTerm(request.getTerm())
                        .withUserId(request.getUserId());
    
                builder.withType("schoolActivity");
                StampRecord schoolActivityStampRecord = activityRecordService.getUserStamps(builder.build(), context);
                stampCount[0] = schoolActivityStampRecord.getActivityStamps().size();
    
                builder.withType("lectureActivity");
                StampRecord lectureActivityStampRecord = activityRecordService.getUserStamps(builder.build(), context);
                stampCount[1] = lectureActivityStampRecord.getActivityStamps().size();
    
                builder.withType("practiceActivity");
                StampRecord practiceActivityStampRecord = activityRecordService.getUserStamps(builder.build(), context);
                stampCount[2] = practiceActivityStampRecord.getActivityStamps().size();
    
                builder.withType("volunteerActivity");
                StampRecord volunteerActivityStampRecord = activityRecordService.getUserStamps(builder.build(), context);
                List<ActivityStamp> volunteerActivityStamps = volunteerActivityStampRecord.getActivityStamps();
                double volunteerActivityTime = 0;
                for (ActivityStamp stamp : volunteerActivityStamps) {
                    volunteerActivityTime += Double.parseDouble(stamp.getActivityTime());
                }
                stampCount[3] = volunteerActivityTime;
    
                builder.withType("volunteerWork");
                StampRecord volunteerWorkStampRecord = activityRecordService.getUserStamps(builder.build(), context);
                List<ActivityStamp> volunteerWorkStamps = volunteerWorkStampRecord.getActivityStamps();
                double volunteerWorkTime = 0;
                for (ActivityStamp stamp : volunteerWorkStamps) {
                    volunteerWorkTime += Double.parseDouble(stamp.getActivityTime());
                }
                stampCount[4] = volunteerWorkTime;
    
                // 正在报名 1 即将开始 0 过期 -1
                int[] activityEntry = new int[]{-1};
                Date current = new Date();
                List<ActivityBO> activityBOS = activityRepoService.queryAllActivity();
                for (ActivityBO activityBO : activityBOS) {
                    Date start = activityBO.getStart();
                    Date end = activityBO.getEnd();
                    if (!current.before(start) && current.before(end)) {
                        activityEntry[0] = 1;
                        break;
                    } else if (current.before(start)){
                        activityEntry[0] = 0;
                    }
                }
    
                data.add(new HashMap<String, String>(){{ put("stamp", JSON.toJSONString(stampCount)); }});
                data.add(new HashMap<String, String>(){{ put("activityEntry", JSON.toJSONString(activityEntry)); }});
                return RestResultUtil.buildSuccessResult(data, "获取每种活动章和活动报名状态成功");
            }
        });
    }
}

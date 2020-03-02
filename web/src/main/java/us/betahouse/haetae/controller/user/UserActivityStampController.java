/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.user.request.UserActivityStampRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityStampRequestBuilder;
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
}

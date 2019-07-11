/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.controller.locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.model.locale.request.LocaleAreaRestRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleAreaManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.request.builder.LocaleAreaManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleAreaService;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-04 22:10 NathanDai
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/localearea")
public class LocaleAreaController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(LocaleAreaController.class);

    @Autowired
    private LocaleAreaService localeAreaService;

    /**
     * 通过 场地id 日期 状态 查询哪个时间段被占用
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/areas")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<LocaleAreaBO>> getLocaleArea(LocaleAreaRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取状态对应的场地", restRequest, new RestOperateCallBack<List<LocaleAreaBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(restRequest.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地Id不能为空");
                AssertUtil.assertNotNull(restRequest.getTimeDate(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "日期不能为空");
            }

            @Override
            public Result<List<LocaleAreaBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleAreaManagerRequest localeAreaManagerRequest = LocaleAreaManagerRequestBuilder.getInstance()
                        //填充场地id
                        .withLocaleId(restRequest.getLocaleId())
                        //填充日期
                        .withTimeDate(restRequest.getTimeDate())
                        //鉴权的时候要用
                        .withUserId(restRequest.getUserId())
                        .build();
                List<LocaleAreaBO> localeAreaBOList = localeAreaService.findAllByLocaleIdAndTimeDateAndStatus(localeAreaManagerRequest, context);

                return RestResultUtil.buildSuccessResult(localeAreaBOList, "获取全部占用时间段成功");
            }
        });

    }

    /**
     * 提交场地占用
     *
     * @param restRequest        LocaleAreaRestRequest
     * @param httpServletRequest HttpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping("/occupy")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleAreaBO> submitOccupy(LocaleAreaRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "提交场地占用", restRequest, new RestOperateCallBack<LocaleAreaBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(restRequest.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地id不能为空");
                AssertUtil.assertNotNull(restRequest.getStatus(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地状态不能为空");
                AssertUtil.assertNotNull(restRequest.getTimeBucket(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地时间段不能为空");
                AssertUtil.assertNotNull(restRequest.getTimeDate(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地日期不能为空");
            }

            @Override
            public Result<LocaleAreaBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleAreaManagerRequest localeAreaManagerRequest = LocaleAreaManagerRequestBuilder.getInstance()
                        //填充场地id
                        .withLocaleId(restRequest.getLocaleId())
                        //填充日期
                        .withTimeDate(restRequest.getTimeDate())
                        //填充时间段
                        .withTimeBucket(restRequest.getTimeBucket())
                        //鉴定权限使用
                        .withUserId(restRequest.getUserId())
                        //填充场地占用状态
                        .withStatus(restRequest.getStatus())
                        //鉴权的时候要用
                        .withUserId(restRequest.getUserId())
                        .build();

                LocaleAreaBO localeAreaBO = localeAreaService.create(localeAreaManagerRequest, context);
                return RestResultUtil.buildSuccessResult(localeAreaBO, "场地占用创建成功");
            }
        });
    }

    /**
     * 修改占用场地的状态 场地占用id 修改的状态
     *
     * @param restRequest        LocaleAreaRestRequest
     * @param httpServletRequest HttpServletRequest
     * @return Result<LocaleAreaBO>
     */
    @CheckLogin
    @PutMapping("/occupy")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleAreaBO> changeOccupy(LocaleAreaRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "更新场地占用信息", restRequest, new RestOperateCallBack<LocaleAreaBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(restRequest.getStatus(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地状态不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleAreaId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地占用id不能为空");
            }

            @Override
            public Result<LocaleAreaBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleAreaManagerRequest localeAreaManagerRequest = LocaleAreaManagerRequestBuilder.getInstance()
                        //填充场地占用id
                        .withLocaleAreaId(restRequest.getLocaleAreaId())
                        //填充场地占用状态
                        .withStatus(restRequest.getStatus())
                        //鉴权的时候要用
                        .withUserId(restRequest.getUserId())
                        .build();

                LocaleAreaBO localeAreaBO = localeAreaService.update(localeAreaManagerRequest, context);
                return RestResultUtil.buildSuccessResult(localeAreaBO, "更新场地占用成功");
            }
        });
    }
}

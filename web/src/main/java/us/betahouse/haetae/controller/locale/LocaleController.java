/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.locale;

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
import us.betahouse.haetae.locale.enums.LocaleStatusEnum;
import us.betahouse.haetae.locale.model.basic.LocaleBO;
import us.betahouse.haetae.model.locale.request.LocaleRestRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.request.builder.LocaleManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleService;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 场地接口
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/locale")
public class LocaleController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(LocaleController.class);

    @Autowired
    private LocaleService localeService;

    /**
     * 申请人获取可用的场地
     * 获取场地之前申请人需要登录
     * 使用角色 申请者
     * 需要传场地status=USABLE/DISABLE
     *
     * @param restRequest        LocaleRestRequest
     * @param httpServletRequest HttpServletRequest
     * @return 状态对应的全部场地
     */
    @CheckLogin
    @GetMapping(value = "/locales")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<LocaleBO>> getAllLocales(LocaleRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取状态对应的场地", restRequest, new RestOperateCallBack<List<LocaleBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(restRequest.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(restRequest.getStatus(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地状态不能为空");
            }

            @Override
            public Result<List<LocaleBO>> execute() {
                //强校验场地状态
                LocaleStatusEnum status = LocaleStatusEnum.getByCode(restRequest.getStatus());
                AssertUtil.assertNotNull(status, "场地状态不存在");

                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleManagerRequest localeManagerRequest = LocaleManagerRequestBuilder.getInstance()
                        //填充场地状态
                        .withStatus(restRequest.getStatus())
                        //鉴权的时候要用
                        .withUserId(restRequest.getUserId())
                        .build();

                List<LocaleBO> localeBOList = localeService.findAllLocalesByStatus(localeManagerRequest, context);
                return RestResultUtil.buildSuccessResult(localeBOList, "获取全部对应状态场地成功");
            }
        });
    }

    /**
     * 通过场地代码查询场地名称
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/localename")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<LocaleBO> getLocalesName(LocaleRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取状态对应的场地", restRequest, new RestOperateCallBack<LocaleBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(restRequest.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleCode(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地代号不能为空");
            }

            @Override
            public Result<LocaleBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleManagerRequest localeManagerRequest = LocaleManagerRequestBuilder.getInstance()
                        .withLocaleCode(restRequest.getLocaleCode())
                        .withUserId(restRequest.getUserId())
                        .build();

                LocaleBO localeBO = localeService.findLocaleNameByLocaleCode(localeManagerRequest, context);
                return RestResultUtil.buildSuccessResult(localeBO, "获取对应场地名称成功");
            }
        });
    }
}

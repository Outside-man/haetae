/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.locale;

import org.apache.commons.lang.StringUtils;
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
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

/**
 * 场地接口
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
     * 获取场地
     * 获取场地之前需要登录
     * 需要传场地status=USABLE/DISABLE
     *
     * @param restRequest        LocaleRestRequest
     * @param httpServletRequest HttpServletRequest
     * @return Result<List < LocaleBO>>
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

                List<LocaleBO> localeBOList = localeService.findAllLocaleByStatus(localeManagerRequest, context);
                return RestResultUtil.buildSuccessResult(localeBOList, "获取全部场地成功");
            }
        });
    }


}

/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.locale.model.basic.LocaleBO;
import us.betahouse.haetae.model.locale.request.LocaleRestRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
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
     * 获取可以使用的场地
     * 获取场地之前需要登录
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/locales")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<LocaleBO>> getAll(LocaleRestRequest restRequest, HttpServletRequest httpServletRequest) {
        System.out.println(restRequest.getUserId());
        return RestOperateTemplate.operate(LOGGER, "获取特定组织或全部物资信息", restRequest, new RestOperateCallBack<List<LocaleBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
            }

            @Override
            public Result<List<LocaleBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleManagerRequestBuilder builder = LocaleManagerRequestBuilder.getInstance();
                // 添加场地状态
                if (StringUtils.isNotBlank(restRequest.getStatus())) {
                    builder.withStatus(restRequest.getStatus());
                }

                return RestResultUtil.buildSuccessResult(localeService.findLocaleByStatus(builder.build(), context), "获取全部场地成功");
            }
        });
    }
}

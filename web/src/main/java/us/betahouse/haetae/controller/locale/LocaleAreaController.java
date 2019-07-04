/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.controller.locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.model.locale.request.LocaleAreaRestRequest;
import us.betahouse.util.common.Result;
import us.betahouse.util.log.Log;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 通过 场地id 日期 状态 查询哪个时间段可用使用
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/areas")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<PageList<LocaleAreaBO>> getLocaleArea(LocaleAreaRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return null;
    }

    /**
     * 提交场地占用
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping("/occupy")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleAreaBO> submitOccupy(LocaleAreaRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return null;
    }

    /**
     * 修改占用场地的状态
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping("/occupy")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleAreaBO> changeOccupy(LocaleAreaRestRequest request, HttpServletRequest httpServletRequest) {
        return null;
    }
}

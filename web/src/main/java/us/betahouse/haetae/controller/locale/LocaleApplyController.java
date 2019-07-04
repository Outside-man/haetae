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
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.model.locale.request.LocaleApplyRestRequest;
import us.betahouse.util.common.Result;
import us.betahouse.util.log.Log;

import javax.servlet.http.HttpServletRequest;

/**
 * @author NathanDai
 * @version :  2019-07-04 22:10 NathanDai
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/localeapply")
public class LocaleApplyController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(LocaleApplyController.class);

    /**
     * 提交场地申请
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping("/apply")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleApplyBO> submitApply(LocaleApplyRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return null;
    }

    /**
     * 场地申请查询 管理员
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/applyadmin")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<PageList<LocaleApplyBO>> getLocaleAreaAdmin(LocaleApplyRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return null;
    }

    /**
     * 场地申请查询 申请人
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/applyuser")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<PageList<LocaleApplyBO>> getLocaleAreaUser(LocaleApplyRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return null;
    }

    /**
     * 场地申请状态修改 管理员 需要验证这个是不是管理员
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping("/applyadmin")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleApplyBO> changeApplyadmin(LocaleApplyRestRequest request, HttpServletRequest httpServletRequest) {
        return null;
    }

    /**
     * 用户自己取消 只需要验证是不是用户自己
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping("/applyadmin")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleApplyBO> changeApplyuser(LocaleApplyRestRequest request, HttpServletRequest httpServletRequest) {
        return null;
    }
}

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
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.model.locale.request.LocaleApplyRestRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleApplyManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.request.builder.LocaleApplyManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleApplyService;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

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

    @Autowired
    LocaleApplyService localeApplyService;

    /**
     * 提交场地申请
     *
     * @param restRequest
     * @param httpServletRequest
     * @return Result<LocaleApplyBO>
     */
    @CheckLogin
    @PostMapping("/apply")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleApplyBO> submitApply(LocaleApplyRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "提交场地申请", restRequest, new RestOperateCallBack<LocaleApplyBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(restRequest.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertNotNull(restRequest.getStatus(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地状态不能为空");
                AssertUtil.assertNotNull(restRequest.getTel(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "申请电话不能为空");
                AssertUtil.assertNotNull(restRequest.getUsages(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "申请用途不能为空");
                AssertUtil.assertNotNull(restRequest.getRemark(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "申请备注不能为空");
                AssertUtil.assertNotNull(restRequest.getDocument(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "申请文件不能为空");
                AssertUtil.assertNotNull(restRequest.getStatus(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "申请状态不能为空");
                AssertUtil.assertNotNull(restRequest.getTimeBucket(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "申请时间段不能为空");
                AssertUtil.assertNotNull(restRequest.getTimeDate(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "申请日期不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleCode(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地代码不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地ID不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleAreaId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "占用场地ID不能为空");
            }

            @Override
            public Result<LocaleApplyBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleApplyManagerRequest localeApplyManagerRequest = LocaleApplyManagerRequestBuilder.getInstance()
                        .withTel(restRequest.getTel())
                        .withUsages(restRequest.getUsages())
                        .withRemark(restRequest.getRemark())
                        .withDocument(restRequest.getDocument())
                        .withLocaleCode(restRequest.getLocaleCode())
                        .withLocaleId(restRequest.getLocaleId())
                        .withLocaleAreaId(restRequest.getLocaleAreaId())
                        .withTimeDate(restRequest.getTimeDate())
                        .withTimeBucket(restRequest.getTimeBucket())
                        .withUserId(restRequest.getUserId())
                        .withStatus(restRequest.getStatus())
                        .build();

                LocaleApplyBO localeApplyBO = localeApplyService.create(localeApplyManagerRequest, context);
                return RestResultUtil.buildSuccessResult(localeApplyBO, "场地申请成功创建");

            }
        });
    }

    /**
     * 场地申请查询 管理员
     *
     * @param restRequest
     * @param httpServletRequest
     * @return Result<PageList < LocaleApplyBO>>
     */
    @CheckLogin
    @GetMapping(value = "/applyadmin")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<PageList<LocaleApplyBO>> getLocaleAreaAdmin(LocaleApplyRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取场地申请列表", restRequest, new RestOperateCallBack<PageList<LocaleApplyBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(restRequest.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<PageList<LocaleApplyBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleApplyManagerRequestBuilder builder = LocaleApplyManagerRequestBuilder.getInstance();

                // 填充状态选择条件
                if (StringUtils.isNotBlank(restRequest.getStatus())) {
                    builder.withStatus(restRequest.getStatus());
                }

                //添加页码
                if (restRequest.getPage() != null && restRequest.getPage() != 0) {
                    builder.withPage(restRequest.getPage());
                }

                //添加每页条数
                if (restRequest.getLimit() != null && restRequest.getLimit() != 0) {
                    builder.withLimit(restRequest.getLimit());
                }

                //添加排序規則
                if (StringUtils.isBlank(restRequest.getOrderRule())) {
                    builder.withOrderRule(restRequest.getOrderRule());
                }
                return RestResultUtil.buildSuccessResult(localeApplyService.findAllByStatus(builder.build(), context), "获取场地申请列表成功");
            }

        });
    }

    /**
     * 场地申请查询 申请人
     *
     * @param restRequest
     * @param httpServletRequest
     * @return Result<PageList < LocaleApplyBO>>
     */
    @CheckLogin
    @GetMapping(value = "/applyuser")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<PageList<LocaleApplyBO>> getLocaleAreaUser(LocaleApplyRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取场地申请列表", restRequest, new RestOperateCallBack<PageList<LocaleApplyBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(restRequest.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<PageList<LocaleApplyBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));

                LocaleApplyManagerRequestBuilder builder = LocaleApplyManagerRequestBuilder.getInstance();

                // 填充用户id选择条件
                if (StringUtils.isNotBlank(restRequest.getUserId())) {
                    builder.withUserId(restRequest.getUserId());
                }

                //添加页码
                if (restRequest.getPage() != null && restRequest.getPage() != 0) {
                    builder.withPage(restRequest.getPage());
                }

                //添加每页条数
                if (restRequest.getLimit() != null && restRequest.getLimit() != 0) {
                    builder.withLimit(restRequest.getLimit());
                }

                //添加排序規則
                if (StringUtils.isBlank(restRequest.getOrderRule())) {
                    builder.withOrderRule(restRequest.getOrderRule());
                }
                return RestResultUtil.buildSuccessResult(localeApplyService.findAllByUserId(builder.build(), context), "获取场地申请列表成功");
            }

        });
    }


    /**
     * 场地申请状态修改 管理员 需要验证这个是不是管理员
     *
     * @param restRequest
     * @param httpServletRequest
     * @return Result<LocaleApplyBO>
     */
    @CheckLogin
    @PutMapping("/applyadmin")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleApplyBO> changeApplyAdmin(LocaleApplyRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "更新场地占用信息", restRequest, new RestOperateCallBack<LocaleApplyBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(restRequest.getStatus(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地状态不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleApplyId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地申请id不能为空");
            }

            @Override
            public Result<LocaleApplyBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                //获取组织的名字 断言工具判断更改的组织是否存在

                LocaleApplyManagerRequest localeApplyManagerRequest = LocaleApplyManagerRequestBuilder.getInstance()
                        .withLocaleApplyId(restRequest.getLocaleApplyId())
                        .withStatus(restRequest.getStatus())
                        //鉴权的时候要用
                        .withUserId(restRequest.getUserId())
                        .build();
                LocaleApplyBO localeApplyBO = localeApplyService.update(localeApplyManagerRequest, context);
                return RestResultUtil.buildSuccessResult(localeApplyBO, "更新场地占用成功");
            }
        });
    }

    /**
     * 用户自己取消 只需要验证是不是用户自己
     *
     * @param restRequest
     * @param httpServletRequest
     * @return Result<LocaleApplyBO>
     */
    @CheckLogin
    @PutMapping("/applyuser")
    @Log(loggerName = LoggerName.FINANCE_DIGEST)
    public Result<LocaleApplyBO> changeApplyUser(LocaleApplyRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "更新场地占用信息", restRequest, new RestOperateCallBack<LocaleApplyBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(restRequest.getStatus(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地状态不能为空");
                AssertUtil.assertNotNull(restRequest.getLocaleApplyId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "场地申请id不能为空");
            }

            @Override
            public Result<LocaleApplyBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                //获取组织的名字 断言工具判断更改的组织是否存在

                LocaleApplyManagerRequest localeApplyManagerRequest = LocaleApplyManagerRequestBuilder.getInstance()
                        .withLocaleApplyId(restRequest.getLocaleApplyId())
                        .withStatus(restRequest.getStatus())
                        //鉴权的时候要用
                        .withUserId(restRequest.getUserId())
                        .build();
                LocaleApplyBO localeApplyBO = localeApplyService.update(localeApplyManagerRequest, context);
                return RestResultUtil.buildSuccessResult(localeApplyBO, "更新场地占用成功");
            }
        });
    }
}

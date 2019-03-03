/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.asset.request.AssetBackRecordRestRequest;
import us.betahouse.haetae.serviceimpl.asset.request.AssetBackRecordManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.request.builder.AssetBackRecordManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.asset.request.builder.AssetManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.asset.service.AssetBackRecordService;
import us.betahouse.haetae.serviceimpl.asset.service.AssetService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordController.java 2019/02/12 17:53 yiyuk.hxy
 */
@CrossOrigin(origins = "http://119.23.188.92/")
@RestController
@RequestMapping(value = "/assetBackRecord")
public class AssetBackRecordController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(AssetBackRecordController.class);

    @Autowired
    private AssetBackRecordService assetBackRecordService;

    /**
     * 归还物资记录
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<AssetBackRecordBO> add(AssetBackRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "归还物资", request, new RestOperateCallBack<AssetBackRecordBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户id不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资id不能为空");
                AssertUtil.assertNotNull(request.getAmount(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "归还数量不能为空");
                AssertUtil.assertNotNull(request.getLoanRecordId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用记录id不能为空");
                AssertUtil.assertNotNull(request.getType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "归还类型不能为空");
            }

            @Override
            public Result<AssetBackRecordBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetBackRecordManagerRequest assetBackRecordManagerRequest = AssetBackRecordManagerRequestBuilder.getInstance()
                        .withAssetId(request.getAssetId())
                        .withAmount(request.getAmount())
                        .withExtInfo(request.getExtInfo())
                        .withLoanRecoedId(request.getLoanRecordId())
                        .withRemark(request.getRemark())
                        .withType(request.getType())
                        .withUserId(request.getUserId())
                        .build();
                AssetBackRecordBO assetBackRecordBO = assetBackRecordService.create(assetBackRecordManagerRequest, context);
                AssertUtil.assertNotNull(assetBackRecordBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "归还物资失败");
                return RestResultUtil.buildSuccessResult(assetBackRecordBO, "归还物资成功");
            }
        });
    }

    /**
     * 消耗品物资直接完结
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping(value = "Comsumables")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<String> Comsumables(AssetBackRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "消耗品状态完结", request, new RestOperateCallBack<String>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request.getUserId(), "用户id不能为空");
                AssertUtil.assertNotNull(request.getLoanRecordId(), "物资借用id不能为空");
                AssertUtil.assertNotNull(request.getAssetId(), "物资id不能为空");
            }

            @Override
            public Result<String> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetBackRecordManagerRequest assetBackRecordManagerRequest = AssetBackRecordManagerRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withLoanRecoedId(request.getLoanRecordId())
                        .withAssetId(request.getAssetId())
                        .build();
                AssetBackRecordBO assetBackRecordBO = assetBackRecordService.consume(assetBackRecordManagerRequest, context);
                return RestResultUtil.buildSuccessResult("success", "物资状态完结状态成功");
            }
        });
    }

    /**
     * 获取归还记录列表
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/byAssetId")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetBackRecordBO>> getAssetBackRecordList(AssetBackRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取归还记录列表", request, new RestOperateCallBack<List<AssetBackRecordBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资id不能为空");
            }

            @Override
            public Result<List<AssetBackRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetBackRecordManagerRequest builder = AssetBackRecordManagerRequestBuilder.getInstance()
                        .withAssetId(request.getAssetId())
                        .build();
                return RestResultUtil.buildSuccessResult(assetBackRecordService.findAllAssetBackRecordByAssetId(builder, context), "获取列表成功");
            }
        });
    }

    /**
     * 获取归还记录列表
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/byUserId")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetBackRecordBO>> getAssetBackRecordListByUserId(AssetBackRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取归还记录列表", request, new RestOperateCallBack<List<AssetBackRecordBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<AssetBackRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetBackRecordManagerRequest builder = AssetBackRecordManagerRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .build();
                return RestResultUtil.buildSuccessResult(assetBackRecordService.findAllAssetBackRecordByUserId(builder, context), "获取列表成功");
            }
        });
    }

    /**
     * 获取归还记录列表
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/byLoanRecordId")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetBackRecordBO>> getAssetBackRecordListByLoanRecordId(AssetBackRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取归还记录列表", request, new RestOperateCallBack<List<AssetBackRecordBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getLoanRecordId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用id不能为空");
            }

            @Override
            public Result<List<AssetBackRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetBackRecordManagerRequest builder = AssetBackRecordManagerRequestBuilder.getInstance()
                        .withLoanRecoedId(request.getLoanRecordId())
                        .build();
                return RestResultUtil.buildSuccessResult(assetBackRecordService.findAssetBackRecordByLoanRecordId(builder, context), "获取列表成功");
            }
        });
    }
}

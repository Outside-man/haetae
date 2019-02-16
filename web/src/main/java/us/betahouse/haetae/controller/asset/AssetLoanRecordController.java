/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.asset.request.AssetLoanRecordRestRequest;
import us.betahouse.haetae.serviceimpl.asset.request.AssetLoanRecordManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.request.builder.AssetLoanRecordManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.asset.service.AssetBackRecordService;
import us.betahouse.haetae.serviceimpl.asset.service.AssetLoanRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 物资借用接口
 *
 * @author yiyuk.hxy
 * @version : AssetLoanRecordController.java 2019/01/25 23:57 yiyuk.hxy
 */
@RestController
@RequestMapping(value = "/assetLoanRecord")
public class AssetLoanRecordController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(AssetLoanRecordController.class);

    @Autowired
    private AssetLoanRecordService assetLoanRecordService;

    @Autowired
    private AssetBackRecordService assetBackRecordService;

    /**
     * 添加借用物资信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetLoanRecordBO>> add(AssetLoanRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "借用物资", request, new RestOperateCallBack<List<AssetLoanRecordBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资不能为空");
                AssertUtil.assertNotNull(request.getAmount(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用数量不能为空");
            }

            @Override
            public Result<List<AssetLoanRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                List<AssetLoanRecordBO> assetLoanRecordBOS = new ArrayList<AssetLoanRecordBO>();
                if (request.getLoanRecordId() == null) {//create
                    AssetLoanRecordManagerRequest assetLoanRecordManagerRequest = AssetLoanRecordManagerRequestBuilder.getInstance()
                            .withUserId(request.getUserId())
                            .withAmount(Integer.valueOf(request.getAmount()))
                            .withRemain(Integer.valueOf(request.getAmount()))
                            .withDistory(Integer.valueOf(0))
                            .withAssetId(request.getAssetId())
                            .withAssetType(request.getAssetType())
                            .withLoanRecordId(request.getLoanRecordId())
                            .withRemark(request.getRemark())
                            .withStatus("assetLoan")
                            .withExtInfo(request.getExtInfo())
                            .withAssetInfo(request.getAssetInfo())
                            .build();
                    assetLoanRecordBOS = assetLoanRecordService.create(assetLoanRecordManagerRequest, context);
                } else {    //update
                    AssetLoanRecordManagerRequest assetLoanRecordManagerRequest = AssetLoanRecordManagerRequestBuilder.getInstance()
                            .withLoanRecordId(request.getLoanRecordId())
                            .withUserId(request.getUserId())
                            .withAmount(Integer.valueOf(request.getAmount()))
                            .withRemain(Integer.valueOf(request.getRemain()))
                            .withDistory(Integer.valueOf(request.getDistory()))
                            .withAssetId(request.getAssetId())
                            .withAssetType(request.getAssetType())
                            .withLoanRecordId(request.getLoanRecordId())
                            .withRemark(request.getRemark())
                            .withStatus(request.getStatus())
                            .withExtInfo(request.getExtInfo())
                            .withAssetInfo(request.getAssetInfo())
                            .build();
                    AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordService.update(assetLoanRecordManagerRequest, context);
                    assetLoanRecordBOS.add(assetLoanRecordBO);
                }
                if (assetLoanRecordBOS != null && assetLoanRecordBOS.size() == 1) {
                    return RestResultUtil.buildSuccessResult(assetLoanRecordBOS, "借用/更新物资成功");
                } else {
                    return RestResultUtil.buildSuccessResult(assetLoanRecordBOS, "借用物资失败");
                }
            }
        });
    }

    /**
     * 获取列表
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/getByAssetId")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetLoanRecordBO>> getAssetLoanRecordList(AssetLoanRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取借用列表", request, new RestOperateCallBack<List<AssetLoanRecordBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<AssetLoanRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetLoanRecordManagerRequest builder = AssetLoanRecordManagerRequestBuilder.getInstance()
                        .withAssetId(request.getAssetId())
                        .build();
                return RestResultUtil.buildSuccessResult(assetLoanRecordService
                        .findAllAssetLoanRecordByAssetId(builder, context), "获取借用列表成功");
            }
        });
    }

    /**
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/getByUserId")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetLoanRecordBO>> getAssetLoanRecordListByUserId(AssetLoanRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取借用列表", request, new RestOperateCallBack<List<AssetLoanRecordBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
            }

            @Override
            public Result<List<AssetLoanRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetLoanRecordManagerRequest builder = AssetLoanRecordManagerRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .build();
                return RestResultUtil.buildSuccessResult(assetLoanRecordService
                        .findAllAssetLoanRecordByUserId(builder, context), "获取借用列表成功");
            }
        });
    }
}

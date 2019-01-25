/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.asset.request.AssetLoanRecordRequest;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.serviceimpl.asset.request.AssetLoanRecordManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.request.builder.AssetLoanRecordManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.asset.service.AssetLoanRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.haetae.utils.RestResultUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    public Result<AssetLoanRecordBO> add(AssetLoanRecordRequest request, HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "借用物资", request, new RestOperateCallBack<AssetLoanRecordBO>(){

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资类型不能为空");
                AssertUtil.assertNotNull(request.getAmount(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用数量不能为空");
            }

            @Override
            public Result<AssetLoanRecordBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetLoanRecordManagerRequest assetLoanRecordManagerRequest = AssetLoanRecordManagerRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withAmount(request.getAmount())
                        .withAssetId(request.getAssetId())
                        .withAssetType(request.getAssetType())
                        .withLoanTime(request.getLoanTime())
                        .withBackTime(request.getBackTime())
                        .withLoanRecordId(request.getLoanRecordId())
                        .withRemark(request.getRemark())
                        .withRemark(request.getStatus())
                        .withExtInfo(request.getExtInfo())
                        .build();
                AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordService.create(assetLoanRecordManagerRequest, context);
                return RestResultUtil.buildSuccessResult(assetLoanRecordBO, "借用物资成功");
            }

        });
    }

}

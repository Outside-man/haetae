/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.asset.enums.AssetLoanRecordStatusEnum;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.asset.request.AssetLoanRecordRestRequest;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 物资借用接口
 *
 * @author yiyuk.hxy
 * @version : AssetLoanRecordController.java 2019/01/25 23:57 yiyuk.hxy
 */
@CrossOrigin(origins = "http://119.23.188.92/")
@RestController
@RequestMapping(value = "/assetLoanRecord")
public class AssetLoanRecordController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(AssetLoanRecordController.class);

    private final int zero = 0;

    @Autowired
    private AssetLoanRecordService assetLoanRecordService;

    /**
     * 创建借用记录
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<AssetLoanRecordBO> createLoanRecordService(AssetLoanRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "借用物资", request, new RestOperateCallBack<AssetLoanRecordBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户ID不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资ID不能为空");
                AssertUtil.assertNotNull(request.getAmount(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用数量不能为空");
                AssertUtil.assertTrue(request.getAmount() > 0, "借用数量不能小于等于0");
            }

            @Override
            public Result<AssetLoanRecordBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetLoanRecordManagerRequest assetLoanRecordManagerRequest = AssetLoanRecordManagerRequestBuilder.getInstance()
                        .withUserId(request.getUserId())
                        .withAmount(request.getAmount())
                        .withRemain(request.getAmount())
                        .withDistory(zero)
                        .withAssetId(request.getAssetId())
                        .withAssetType(request.getAssetType())
                        .withRemark(request.getRemark())
                        //设置借用记录中状态为Loading (借出)
                        .withStatus(AssetLoanRecordStatusEnum.LOAN.getCode())
                        .withExtInfo(request.getExtInfo())
                        .withAssetInfo(request.getAssetInfo())
                        .build();
                AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordService.create(assetLoanRecordManagerRequest, context);
                return RestResultUtil.buildSuccessResult(assetLoanRecordBO, "物资借用成功");
            }
        });
    }

    /**
     * 两个获取借用记录列表合并 物资借用记录详细信息列表合并
     * 情况1:通过物资id来获取该物资借用列表
     * 情况2:通过用户id来获取物资借用列表
     * 情况3:通过物资借用id来获取该条物资借用信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "records")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetLoanRecordBO>> getAssetLoanRecordList(AssetLoanRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取借用列表", request, new RestOperateCallBack<List<AssetLoanRecordBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户ID不能为空");
            }

            @Override
            public Result<List<AssetLoanRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetLoanRecordManagerRequestBuilder builder = AssetLoanRecordManagerRequestBuilder.getInstance()
                        .withUserId(request.getUserId());
                List<AssetLoanRecordBO> assetLoanRecordBOS=new ArrayList<>();
                //接收参数若有借用记录id则 查找该条记录详细信息
                if (request.getLoanRecordId() != null) {
                    builder.withLoanRecordId(request.getLoanRecordId());
                    AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordService.findAssetLoanRecordByLoanRecordId(builder.build(), context);
                    assetLoanRecordBOS.add(assetLoanRecordBO);
                } else if (request.getAssetId() != null) {
                    //接收参数若有物资id，则查询该物资的借用记录集合
                    builder.withAssetId(request.getAssetId());
                    assetLoanRecordBOS = assetLoanRecordService.findAllAssetLoanRecordByAssetId(builder.build(), context);
                } else {
                    //否则根据用户id找出该用户全部借用记录
                    assetLoanRecordBOS = assetLoanRecordService.findAllAssetLoanRecordByUserId(builder.build(), context);
                }
                return RestResultUtil.buildSuccessResult(assetLoanRecordBOS, "获取借用列表成功");
            }
        });
    }

}

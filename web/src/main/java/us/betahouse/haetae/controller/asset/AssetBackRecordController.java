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
import us.betahouse.haetae.serviceimpl.asset.request.builder.AssetBackRecordManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.asset.service.AssetBackRecordService;
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
 * @author yiyuk.hxy
 * @version : AssetBackRecordController.java 2019/02/12 17:53 yiyuk.hxy
 */
@CrossOrigin
@RestController
@RequestMapping(value = "assetBackRecord")
public class AssetBackRecordController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(AssetBackRecordController.class);

    @Autowired
    private AssetBackRecordService assetBackRecordService;

    /**
     * 创建归还物资记录
     * 消耗品和耐用品用同一个接口
     * 消耗品归还类型为归还和报损 使用这个接口
     * 耐用品归还类型为归还和消耗
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
                AssertUtil.assertNotNull(request.getAmount(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "归还数量不能为空");
                AssertUtil.assertTrue(request.getAmount() > 0, "归还/报损数量不能为小于等于0");
                AssertUtil.assertNotNull(request.getLoanRecordId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用记录id不能为空");
                AssertUtil.assertNotNull(request.getType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "归还类型不能为空");
                AssertUtil.assertNotNull(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资ID不存在");
            }

            @Override
            public Result<AssetBackRecordBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetBackRecordManagerRequest assetBackRecordManagerRequest = AssetBackRecordManagerRequestBuilder.getInstance()
                        .withAssetId(request.getAssetId())
                        .withAmount(request.getAmount())
                        .withLoanRecoedId(request.getLoanRecordId())
                        .withRemark(request.getRemark())
                        .withType(request.getType())
                        .withUserId(request.getUserId())
                        .build();
                AssetBackRecordBO assetBackRecordBO = assetBackRecordService.create(assetBackRecordManagerRequest, context);
                AssertUtil.assertNotNull(assetBackRecordBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "归还/报损物资失败");
                return RestResultUtil.buildSuccessResult(assetBackRecordBO, "归还/报损物资成功");
            }
        });
    }

    /**
     * 获取归还记录列表分情况
     * 情况1：接收参数为物资id 返回该物资的所有归还列表
     * 情况2:接收参数为userid 返回该用户的所有归还记录
     * 情况3：接收参数为物资借用id，返回该条借用记录的所有归还记录集合
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "records")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetBackRecordBO>> getAssetBackRecordList(AssetBackRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取归还记录列表", request, new RestOperateCallBack<List<AssetBackRecordBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
            }

            @Override
            public Result<List<AssetBackRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetBackRecordManagerRequestBuilder builder = AssetBackRecordManagerRequestBuilder.getInstance();
                List<AssetBackRecordBO> assetBackRecordBOS = new ArrayList<>();
                //情况3 传入借用记录id不为空
                if (request.getLoanRecordId() != null) {
                    builder.withLoanRecoedId(request.getLoanRecordId());
                    assetBackRecordBOS = assetBackRecordService.findAllAssetBackRecordByLoanRecordId(builder.build(), context);
                } else if (request.getAssetId() != null) {
                    //情况1 传入物资id
                    builder.withAssetId(request.getAssetId());
                    assetBackRecordBOS = assetBackRecordService.findAllAssetBackRecordByAssetId(builder.build(), context);
                } else {
                    //情况3 默认获取用户的所有信息
                    builder.withUserId(request.getUserId());
                    assetBackRecordBOS = assetBackRecordService.findAllAssetBackRecordByUserId(builder.build(), context);
                }
                return RestResultUtil.buildSuccessResult(assetBackRecordBOS, "获取列表成功");
            }
        });
    }

}

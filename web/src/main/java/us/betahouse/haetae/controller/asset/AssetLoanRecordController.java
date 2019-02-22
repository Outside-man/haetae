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
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.asset.request.AssetLoanRecordRestRequest;
import us.betahouse.haetae.serviceimpl.asset.request.AssetLoanRecordManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.request.builder.AssetLoanRecordManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.asset.request.builder.AssetManagerRequestBuilder;
import us.betahouse.haetae.serviceimpl.asset.service.AssetLoanRecordService;
import us.betahouse.haetae.serviceimpl.asset.service.AssetService;
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
    private AssetService assetService;

    @Autowired
    private AssetLoanRecordService assetLoanRecordService;

    @Autowired
    private AssetRepoService assetRepoService;

    /**
     * 添加借用物资信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping(value = "/create")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetLoanRecordBO>> add(AssetLoanRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "借用物资", request, new RestOperateCallBack<List<AssetLoanRecordBO>>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUserId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资不能为空");
                AssertUtil.assertNotNull(request.getAmount(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用数量不能为空");
                AssertUtil.assertNotNull(request.getAmount() <= 0? null:"1", RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借用数量不能小于等于0");
            }
            @Override
            public Result<List<AssetLoanRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                List<AssetLoanRecordBO> assetLoanRecordBOS = new ArrayList<AssetLoanRecordBO>();
                if (request.getLoanRecordId() == null) {//create
                    //AssertUtil.assertNotNull(assetRepoService.findByAssetId(request.getAssetId()).getAssetRemain() < request.getAmount() ? "1": null, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "借物资数量大于该物资剩余数量");
                    AssetLoanRecordManagerRequest assetLoanRecordManagerRequest = AssetLoanRecordManagerRequestBuilder.getInstance()
                            .withUserId(request.getUserId())
                            .withAmount(request.getAmount())
                            .withRemain(request.getAmount())
                            .withDistory(0)
                            .withAssetId(request.getAssetId())
                            .withAssetType(request.getAssetType())
                            .withRemark(request.getRemark())
                            .withStatus("Loading")
                            .withExtInfo(request.getExtInfo())
                            .withAssetInfo(request.getAssetInfo())
                            .build();
                    assetLoanRecordBOS = assetLoanRecordService.create(assetLoanRecordManagerRequest, context);
                } else {    //update
                    AssetLoanRecordManagerRequest assetLoanRecordManagerRequest = AssetLoanRecordManagerRequestBuilder.getInstance()
                            .withLoanRecordId(request.getLoanRecordId())
                            .withRemain(request.getRemain())
                            .withDistory(request.getDistory())
                            .withAssetId(request.getAssetId())
                            .withRemark(request.getRemark())
                            .withExtInfo(request.getExtInfo())
                            .withAssetInfo(request.getAssetInfo())
                            .build();
                    AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordService.update(assetLoanRecordManagerRequest, context);
                    assetLoanRecordBOS.add(assetLoanRecordBO);
                }
                if (assetLoanRecordBOS != null && assetLoanRecordBOS.size() == 1) {
                    AssetManagerRequest assetManagerRequest = AssetManagerRequestBuilder.getInstance()
                            .withAssetId(request.getAssetId())
                            .builder();
                    assetLoanRecordBOS.get(0).setAssetName(assetService.findAssetByAssetId(assetManagerRequest, context).getAssetName());
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
                AssertUtil.assertNotNull(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资不存在");
            }

            @Override
            public Result<List<AssetLoanRecordBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetLoanRecordManagerRequest builder = AssetLoanRecordManagerRequestBuilder.getInstance()
                        .withAssetId(request.getAssetId())
                        .build();
                List<AssetLoanRecordBO> assetLoanRecordBOS = assetLoanRecordService.findAllAssetLoanRecordByAssetId(builder, context);
                AssetManagerRequest assetManagerRequest = AssetManagerRequestBuilder.getInstance()
                        .withAssetId(request.getAssetId())
                        .builder();
                String name = assetService.findAssetByAssetId(assetManagerRequest, context).getAssetName();
                for(int i = 0; i < assetLoanRecordBOS.size(); ++i){
                    assetLoanRecordBOS.get(i).setAssetName(name);
                }
                return RestResultUtil.buildSuccessResult(assetLoanRecordBOS, "获取借用列表成功");
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
                List<AssetLoanRecordBO> assetLoanRecordBOS = assetLoanRecordService.findAllAssetLoanRecordByUserId(builder, context);
                for(int i = 0; i < assetLoanRecordBOS.size(); ++i){
                    AssetManagerRequest assetManagerRequest = AssetManagerRequestBuilder.getInstance()
                            .withAssetId(assetLoanRecordBOS.get(i).getAssetId())
                            .builder();
                    assetLoanRecordBOS.get(i).setAssetName(assetService.findAssetByAssetId(assetManagerRequest, context).getAssetName());
                }
                return RestResultUtil.buildSuccessResult(assetLoanRecordBOS, "获取借用列表成功");
            }
        });
    }

    /**
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/getByLoanRecordId")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<AssetLoanRecordBO> getAssetLoanRecordListByLoanRecordId(AssetLoanRecordRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取借用记录", request, new RestOperateCallBack<AssetLoanRecordBO>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getLoanRecordId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "id不能为空");
            }

            @Override
            public Result<AssetLoanRecordBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetLoanRecordManagerRequest builder = AssetLoanRecordManagerRequestBuilder.getInstance()
                        .withLoanRecordId(request.getLoanRecordId())
                        .build();
                AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordService.findAssetLoanRecordByLoanRecordId(builder, context);
                AssetManagerRequest assetManagerRequest = AssetManagerRequestBuilder.getInstance()
                        .withAssetId(assetLoanRecordBO.getAssetId())
                        .builder();
                assetLoanRecordBO.setAssetName(assetService.findAssetByAssetId(assetManagerRequest, context).getAssetName());
                return RestResultUtil.buildSuccessResult(assetLoanRecordBO, "获取借用记录成功");
            }
        });
    }
}

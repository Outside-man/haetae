/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.activity.dal.service.OrganizationRepoService;
import us.betahouse.haetae.activity.model.basic.OrganizationBO;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.common.log.LoggerName;
import us.betahouse.haetae.common.session.CheckLogin;
import us.betahouse.haetae.common.template.RestOperateCallBack;
import us.betahouse.haetae.common.template.RestOperateTemplate;
import us.betahouse.haetae.model.asset.request.AssetRestRequest;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.request.builder.AssetManagerRequestBuilder;
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
 * 物资接口
 *
 * @author guofan.cp
 * @version : AssetController.java 2019/01/23 9:06 guofan.cp
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/asset")
public class AssetController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(AssetController.class);

    private final Integer zero = 0;

    @Autowired
    private AssetService assetService;

    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Autowired
    private AssetRepoService assetRepoService;

    /**
     * 创建物资
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PostMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<AssetBO> addAsset(AssetRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "新增物资", request, new RestOperateCallBack<AssetBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资名不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资类型不能为空");
                AssertUtil.assertStringNotBlank(request.getOrganizationName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资归属组织不能为空");
                AssertUtil.assertNotNull(request.getAssetAmount(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资数量不能为空");
                AssertUtil.assertTrue(request.getAssetAmount() > 0, "物资数量不能小于0");
            }

            @Override
            public Result<AssetBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                /**
                 * 通过组织名字查找组织id
                 */
                OrganizationBO organizationBo = organizationRepoService.queryOrganizationByName(request.getOrganizationName());
                AssertUtil.assertNotNull(organizationBo, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资归属组织不存在");
                String organizationId = organizationBo.getOrganizationId();
                AssetManagerRequest assetManagerRequest = AssetManagerRequestBuilder.getInstance()
                        .withAssetName(request.getAssetName())
                        .withAmount(Integer.valueOf(request.getAssetAmount()))
                        //组织id在上面获得
                        .withOrginazationId(organizationId)
                        //鉴权的时候要用
                        .withUserId(request.getUserId())
                        .withType(request.getAssetType())
                        .withAssetOrganizationName(request.getOrganizationName())
                        .withReamain(Integer.valueOf(request.getAssetAmount()))
                        .withStatus(AssetStatusEnum.ASSET_LOAN.getCode())
                        .withAssetStatusCode(AssetStatusEnum.ASSET_LOAN.getCode())
                        .withDestroy(zero)
                        //以下是可选参数
                        //额外信息
                        .withExtInfo(request.getExtInfo())
                        .builder();
                AssetBO assetBo = assetService.create(assetManagerRequest, context);
                return RestResultUtil.buildSuccessResult(assetBo, "物资创建");
            }
        });
    }

    /**
     * 更新物资接口，更改物资的名称和添加物资剩余的数量
     * 获取的是更新后剩余的数量
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @PutMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<AssetBO> updateAsset(AssetRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "更新物资信息", request, new RestOperateCallBack<AssetBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资名不能为空");
                AssertUtil.assertNotNull(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资id不能为空");
                AssertUtil.assertNotNull(request.getAssetRemain(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资剩余数量不能为空");
                AssertUtil.assertTrue(request.getAssetRemain() >= 0, "物资剩余数量不能小于0");
            }

            @Override
            public Result<AssetBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                //获取组织的名字 断言工具判断更改的组织是否存在
                AssetManagerRequest assetManagerRequest = AssetManagerRequestBuilder.getInstance()
                        .withAssetId(request.getAssetId())
                        .withAssetName(request.getAssetName())
                        //鉴权的时候要用
                        .withUserId(request.getUserId())
                        .withReamain(Integer.valueOf(request.getAssetRemain()))
                        .builder();
                AssetBO assetBo = assetService.update(assetManagerRequest, context);
                return RestResultUtil.buildSuccessResult(assetBo, "更新物资成功");
            }
        });
    }

    /**
     * 判断物资状态返回物资实体
     *
     * @param restRequest
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<AssetBO> AssetStatus(AssetRestRequest restRequest, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "判断物资状态", restRequest, new RestOperateCallBack<AssetBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(restRequest, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertNotNull(restRequest.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资ID不能为空");
            }

            @Override
            public Result<AssetBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                //根据物资id判断物资状态
                String assetStatusCode = assetRepoService.judgeStatusByAssetId(restRequest.getAssetId());
                AssetManagerRequest assetManagerRequest = AssetManagerRequestBuilder.getInstance()
                        .withAssetId(restRequest.getAssetId())
                        .withAssetStatusCode(assetStatusCode)
                        .builder();
                AssetBO assetBO = assetService.findAssetByAssetId(assetManagerRequest, context);
                assetBO.setAssetStatus(assetStatusCode);
                return RestResultUtil.buildSuccessResult(assetBO, "获取物资成功");
            }
        });
    }

    /**
     * 获取全部物资信息这边需要鉴权判断是否为物资管理员
     * 如果未接收组织id则返回全部物资的信息
     * 如果接收到组织的id则返回该组织的部分信息
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @CheckLogin
    @GetMapping(value = "/assets")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<List<AssetBO>> getAll(AssetRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "获取特定组织或全部物资信息", request, new RestOperateCallBack<List<AssetBO>>() {

            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
            }

            @Override
            public Result<List<AssetBO>> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetManagerRequestBuilder builder = AssetManagerRequestBuilder.getInstance()
                        //鉴权的时候要用
                        .withUserId(request.getUserId());
                List<AssetBO> assetBOS;
                //接收到组织id 查找该组织的所有物资
                if (null != request.getOrganizationId()) {
                    builder.withOrginazationId(request.getOrganizationId());
                    assetBOS = assetService.queryAssetByOrganizationId(builder.builder(), context);
                } else {
                    assetBOS = assetService.findAllAsset(builder.builder(), context);
                }
                return RestResultUtil.buildSuccessResult(assetBOS, "获取全部/组织/物资列表成功");
            }
        });
    }

    /**
     * 物资管理员删除物资
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping
    @CheckLogin
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<AssetBO> deleteAsset(AssetRestRequest request, HttpServletRequest httpServletRequest) {
        return RestOperateTemplate.operate(LOGGER, "删除物资", request, new RestOperateCallBack<AssetBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetId(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资id不能为空");
            }

            @Override
            public Result<AssetBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                AssetManagerRequest builder = AssetManagerRequestBuilder.getInstance()
                        .withAssetId(request.getAssetId())
                        //鉴权的时候要用
                        .withUserId(request.getUserId())
                        .builder();
                assetService.delete(builder, context);
                return RestResultUtil.buildSuccessResult("删除物资成功");
            }
        });
    }
}
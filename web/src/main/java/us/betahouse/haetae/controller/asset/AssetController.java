/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.asset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import us.betahouse.haetae.activity.dal.service.OrganizationRepoService;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.request.AssetRequest;
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


/**
 * 物资接口
 * @author guofan.cp
 * @version : AssetController.java 2019/01/23 9:06 guofan.cp
 */
@RestController
@RequestMapping(value = "/asset")
public class AssetController {
    /**
     *日志实体
     */
    private final Logger LOGGER= LoggerFactory.getLogger(AssetController.class);

    @Autowired
    private AssetService assetService;

    @Autowired
    private OrganizationRepoService organizationRepoService;
    /**
     * 添加物资
     */
    @CheckLogin
    @PostMapping(value = "asset")
    @Log(loggerName = LoggerName.WEB_DIGEST)
    public Result<AssetBO> asset(AssetRestRequest request, HttpServletRequest httpServletRequest){
        return RestOperateTemplate.operate(LOGGER, "新增物资", request, new RestOperateCallBack<AssetBO>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资名不能为空");
                AssertUtil.assertStringNotBlank(request.getAssetType(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资类型不能为空");
                AssertUtil.assertStringNotBlank(request.getOrganizationName(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资归属组织不能为空");
                AssertUtil.assertNotNull(request.getAssetAmount(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资数量不能为空");
                AssertUtil.assertStringNotBlank(Integer.valueOf(request.getAssetAmount())>0?"1":"", RestResultCode.ILLEGAL_PARAMETERS.getCode(),"物资数量不能小于0");
            }

            @Override
            public Result<AssetBO> execute() {
                OperateContext context = new OperateContext();
                context.setOperateIP(IPUtil.getIpAddr(httpServletRequest));
                /**
                 * 通过组织名字查找组织id
                 */
                String organizationId = organizationRepoService.queryOrganizationByName(request.getOrganizationName()).getOrganizationId();
                AssertUtil.assertStringNotBlank(organizationId,RestResultCode.ILLEGAL_PARAMETERS.getCode(),"物资归属组织不存在");

                AssetManagerRequest assetManagerRequest=AssetManagerRequestBuilder.getInstance()
                        .withAssetName(request.getAssetName())
                        .withAmount(Integer.valueOf(request.getAssetAmount()))
                        //组织id在上面获得
                        .withOrginazationId(organizationId)
                        .withType(request.getAssetType())
                        .withAssetOrganizationName(request.getOrganizationName())
                        //以下是可选参数
                        //额外信息
                        .withExtInfo(request.getExtInfo())
                        .builder();
                AssetBO assetBo=assetService.create(assetManagerRequest,context);
                return RestResultUtil.buildSuccessResult(assetBo,"物资创建成功");
            }
        });
    }
}

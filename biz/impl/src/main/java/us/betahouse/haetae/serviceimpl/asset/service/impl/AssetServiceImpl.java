/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.enums.AssetTypeEnum;
import us.betahouse.haetae.asset.manager.AssetLoanRecordManager;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.serviceimpl.asset.constant.AssetPermType;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.service.AssetService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;

import java.text.MessageFormat;
import java.util.List;

/**
 * 物资业务实现
 *
 * @author guofan.cp
 * @version : AssetServiceImpl.java 2019/01/20 23:58 guofan.cp
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetManager assetManager;

    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Autowired
    private AssetDORepo assetDORepo;

    @Autowired
    private AssetLoanRecordManager assetLoanRecordManager;


    @Override
    @VerifyPerm(permType = AssetPermType.ASSET_CREATE)
    @Transactional(rollbackFor = Exception.class)
    public AssetBO create(AssetManagerRequest request, OperateContext context) {
        AssetTypeEnum assetTypeEnum = AssetTypeEnum.getByCode(request.getAssetType());
        AssertUtil.assertNotNull(assetTypeEnum, "物资类型不存在");
        AssertUtil.assertStringNotBlank(request.getAssetName(), "物资名字不能为空");
        AssertUtil.assertStringNotBlank(request.getAssetOrganizationName(), "物资归属组织不能为空");
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationName(request.getAssetOrganizationName());
        AssertUtil.assertNotNull(organizationBO, MessageFormat.format("组织不存在,{0}", request.getAssetOrganizationName()));
        AssertUtil.assertStringNotBlank(request.getAssetType(), "物资类型不能为空");
        //判断物资类型是否合理并进行转换 接收参数为中文转英文状态
        if (request.getAssetType().equals(AssetTypeEnum.ASSET_CONSUME.getDesc())) {
            request.setAssetType(AssetTypeEnum.ASSET_CONSUME.getCode());
        } else if (request.getAssetType().equals(AssetTypeEnum.ASSET_DURABLE.getDesc())) {
            request.setAssetType(AssetTypeEnum.ASSET_DURABLE.getCode());
        } else {
            AssertUtil.assertNotNull(MessageFormat.format("物资类型不存在,{0}", request.getAssetType()));
        }
        //创建物资
        AssetBO assetBO = assetManager.create(request);
        //设置物资归属组织名字
        assetBO.setAssetOrganizationName(request.getAssetOrganizationName());
        return assetBO;
    }

    @Override
    @VerifyPerm(permType = AssetPermType.ASSET_UPDATE)
    @Transactional(rollbackFor = Exception.class)
    public AssetBO update(AssetManagerRequest request, OperateContext context) {
        //对接收物资剩余数量进行判断
        // 情况1：若物资接收物资剩余个数小于原物资数量则报错
        // 情况2：若物资新加物资数大于原先物资个数，则再物资总数量上加上新增物资的个数
        AssetDO assetDO = assetDORepo.findByAssetId(request.getAssetId());
        AssertUtil.assertNotNull(assetDO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资不存在");
        int oldAssetRemainNumber = assetDO.getRemain();
        AssertUtil.assertTrue(request.getAssetRemain() >= oldAssetRemainNumber, "仅补充数量，非减少数量");
        //更新物资总数量
        request.setAssetAmount(assetDO.getAmount() + request.getAssetRemain() - oldAssetRemainNumber);
        //更新物资状态 状态变为可借
        if (request.getAssetRemain() > 0) {
            request.setAssetStatus(AssetStatusEnum.ASSET_LOAN.getCode());
        } else {
            request.setAssetStatus(AssetStatusEnum.ASSET_NOT_LOAN.getCode());
        }
        AssetBO assetBO = assetManager.update(request);
        addOrganizationName(assetBO);
        return assetBO;
    }

    @Override
    @VerifyPerm(permType = AssetPermType.ASSET_DELETE)
    @Transactional(rollbackFor = Exception.class)
    public void delete(AssetManagerRequest request, OperateContext context) {
        //删除物资信息和物资借用信息
        assetLoanRecordManager.delete(request.getAssetId());
        assetManager.delete(request.getAssetId());
    }

    @Override
    @VerifyPerm(permType = AssetPermType.ASSET_SEEK)
    public List<AssetBO> findAllAsset(AssetManagerRequest request, OperateContext context) {
        List<AssetBO> assetBOList = assetManager.findAll();
        assetBOList.forEach(this::addOrganizationName);
        return assetBOList;
    }

    @Override
    public AssetBO findAssetByAssetId(AssetManagerRequest request, OperateContext context) {
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        AssertUtil.assertNotNull(assetBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资码不存在");
        addOrganizationName(assetBO);
        return assetBO;
    }


    @Override
    @VerifyPerm(permType = AssetPermType.ASSET_SEEK)
    public List<AssetBO> queryAssetByOrganizationId(AssetManagerRequest request, OperateContext context) {
        List<AssetBO> assetBOList = assetManager.queryAssetByOrganizationId(request.getAssetOrganizationId());
        assetBOList.forEach(this::addOrganizationName);
        return assetBOList;
    }

    //物资添加归属组织名字
    void addOrganizationName(AssetBO assetBO) {
        String organizationId = assetBO.getAssetOrganizationId();
        String organizationName = organizationRepoService.queryByOrganizationId(organizationId).getOrganizationName();
        assetBO.setAssetOrganizationName(organizationName);
    }
}

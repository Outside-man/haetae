/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.activity.dal.service.OrganizationRepoService;
import us.betahouse.haetae.activity.model.basic.OrganizationBO;
import us.betahouse.haetae.asset.builder.AssetRecordBOBuilder;
import us.betahouse.haetae.asset.dal.model.AssetBackRecordDO;
import us.betahouse.haetae.asset.dal.model.AssetLoanRecordDO;
import us.betahouse.haetae.asset.dal.repo.AssetBackDORepo;
import us.betahouse.haetae.asset.dal.repo.AssetLoanDORepo;
import us.betahouse.haetae.asset.enums.AssetBackRecordTypeEnum;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.enums.AssetTypeEnum;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.model.basic.AssetRecordBO;
import us.betahouse.haetae.serviceimpl.asset.constant.AssetPermType;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.service.AssetService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 物资业务实现
 *
 * @author guofan.cp
 * @version : AssetServiceImpl.java 2019/01/20 23:58 guofan.cp
 */
@Service
public class AssetServiceImpl implements AssetService  {
    /**
     * 系统结束标志
     */
    private final static String SYSTEM_FINISH_SIGN = "systemFinish";

    @Autowired
    private PermManager permManager;

    @Autowired
    private AssetManager assetManager;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Autowired
    private RoleRepoService roleRepoService;

    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Autowired
    private AssetBackDORepo assetBackDORepo;

    @Autowired
    private AssetLoanDORepo assetLoanDORepo;

    @Override
    @VerifyPerm(permType = AssetPermType.ASSET_CREATE)
    @Transactional(rollbackFor = Exception.class)
    public AssetBO create(AssetManagerRequest request, OperateContext context) {
        AssetTypeEnum assetTypeEnum = AssetTypeEnum.getByCode(request.getAssetType());
        AssertUtil.assertNotNull(assetTypeEnum, "物资类型不存在");
        AssertUtil.assertStringNotBlank(request.getAssetName(), "物资名字不能为空");
        AssertUtil.assertStringNotBlank(request.getAssetOrganizationName(), "物资归属组织不能为空");
        OrganizationBO organizationBO = organizationRepoService.queryOrganizationByName(request.getAssetOrganizationName());
        AssertUtil.assertNotNull(organizationBO, MessageFormat.format("组织不存在,{0}", request.getAssetOrganizationName()));
        AssertUtil.assertStringNotBlank(request.getAssetType(), "物资类型不能为空");
        //创建物资
        AssetBO assetBO = assetManager.create(request);
        return assetBO;
    }

    @Override
    public AssetBO update(AssetManagerRequest request, OperateContext context) {
        if (request.getAssetType() != null) {
            AssetTypeEnum assetTypeEnum = AssetTypeEnum.getByCode(request.getAssetType());
            AssertUtil.assertNotNull(assetTypeEnum, "物资类型不存在");
        }
        if (request.getAssetOrganizationName() != null) {
            OrganizationBO organizationBO = organizationRepoService.queryOrganizationByName(request.getAssetOrganizationName());
            AssertUtil.assertNotNull(organizationBO, MessageFormat.format("组织不存在,{0}", request.getAssetOrganizationName()));
        }
        AssetBO assetBO = assetManager.update(request);
        return assetBO;
    }

    @Override
    public void delete(AssetManagerRequest request, OperateContext context) {
        assetManager.delete(request.getAssetId());
    }

    @Override
    public AssetBO findAssetByAssetId(AssetManagerRequest request, OperateContext context) {
        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        AssertUtil.assertNotNull(assetBO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资码不存在");
        return assetBO;
    }

    @Override
    public List<AssetRecordBO> findRecodByAssetStatus(AssetManagerRequest request, OperateContext context) {
        List<AssetRecordBO> assetRecordBOS = new ArrayList<>();
        AssetStatusEnum assetStatusEnum = AssetStatusEnum.getByCode(request.getAssetStatusCode());
        AssertUtil.assertNotNull(assetStatusEnum, "物资状态不存在");
        switch (assetStatusEnum) {
            //暂无物资 返回报损记录
            case ASSET_TEMPNOTLOAN: {
                System.out.println("cp3" + request.getAssetId() + " " + AssetBackRecordTypeEnum.DESTROY.getCode());
                List<AssetBackRecordDO> assetBackRecordDOS = assetBackDORepo.findAllByAssetIdAndTypeOrderByIdDesc(request.getAssetId(), AssetBackRecordTypeEnum.DESTROY.getCode());
                AssetBackRecordDO assetBackRecordDO = null;
                System.out.println("cp2 size" + assetBackRecordDOS.size());
                for (int i = 0; i < assetBackRecordDOS.size(); i++) {
                    assetBackRecordDO = assetBackRecordDOS.get(i);
                    AssetRecordBOBuilder assetRecordBOBuilder = AssetRecordBOBuilder.getInstance()
                            .withAssetId(assetBackRecordDO.getAssetId())
                            .withAssetInfo(assetBackRecordDO.getExtInfo())
                            .withAssetType(assetBackRecordDO.getAssetType())
                            .withLoanRecordId(assetBackRecordDO.getLoanRecoedId())
                            .withUserId(assetBackRecordDO.getUserId())
                            .withBackAmount(assetBackRecordDO.getAmount())
                            .withBackType(assetBackRecordDO.getType())
                            .withBackRemark(assetBackRecordDO.getRemark());
                    assetRecordBOS.add(assetRecordBOBuilder.builder());
                }
                break;
            }
            //物资借完 返回借用记录
            case ASSET_ALLLOAN: {
                List<AssetLoanRecordDO> assetLoanRecordDOS = assetLoanDORepo.findAllRecordByAssetId(request.getAssetId());
                AssetLoanRecordDO assetLoanRecordDO = null;
                for (int i = 0; i < assetLoanRecordDOS.size(); i++) {
                    assetLoanRecordDO = assetLoanRecordDOS.get(i);
                    AssetRecordBOBuilder assetRecordBOBuilder = AssetRecordBOBuilder.getInstance()
                            .withAssetId(assetLoanRecordDO.getAssetId())
                            .withAssetType(assetLoanRecordDO.getAssetType())
                            .withUserId(assetLoanRecordDO.getUserId())
                            .withLoanRecordId(assetLoanRecordDO.getLoanRecordId())
                            .withLoanTime(assetLoanRecordDO.getLoanTime())
                            .withBackTime(assetLoanRecordDO.getBackTime())
                            .withStatus(assetLoanRecordDO.getStatus())
                            .withLoanamount(assetLoanRecordDO.getAmount())
                            .withDistory(assetLoanRecordDO.getDistory())
                            .withRemark(assetLoanRecordDO.getRemark())
                            .withAssetInfo(assetLoanRecordDO.getAssetInfo());
                    assetRecordBOS.add(assetRecordBOBuilder.builder());
                }
                break;
            }
            default: {
            }
        }
        return assetRecordBOS;
    }

    @Override
    public List<AssetBO> queryAssetByOrganizationId(AssetManagerRequest request, OperateContext context) {
        return assetManager.queryAssetByOrganizationId(request.getAssetOrganizationId());
    }
}

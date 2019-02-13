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
import us.betahouse.haetae.asset.enums.AssetTypeEnum;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.service.AssetService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.util.utils.AssertUtil;

import java.text.MessageFormat;

/**
 * 物资业务实现
 * @author guofan.cp
 * @version : AssetServiceImpl.java 2019/01/20 23:58 guofan.cp
 */
@Service
public class AssetServiceImpl implements AssetService {
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


    @Override
    @Transactional
    public AssetBO create(AssetManagerRequest request, OperateContext context) {

        AssetTypeEnum assetTypeEnum=AssetTypeEnum.getByCode(request.getAssetType());
        AssertUtil.assertNotNull(assetTypeEnum,"物资类型不存在");
        AssertUtil.assertStringNotBlank(request.getAssetName(),"物资名字不能为空");
        AssertUtil.assertStringNotBlank(request.getAssetOrganizationName(),"物资归属组织不能为空");
        OrganizationBO organizationBO=organizationRepoService.queryOrganizationByName(request.getAssetOrganizationName());
        AssertUtil.assertNotNull(organizationBO,MessageFormat.format("组织不存在,{0}",request.getAssetOrganizationName()));
        AssertUtil.assertStringNotBlank(request.getAssetType(),"物资类型不能为空");
        //创建物资
        AssetBO assetBO=assetManager.create(request);
        return assetBO;
    }
}

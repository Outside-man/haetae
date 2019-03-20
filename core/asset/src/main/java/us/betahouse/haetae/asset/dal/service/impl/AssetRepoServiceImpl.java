/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.dal.service.OrganizationRepoService;
import us.betahouse.haetae.activity.model.basic.OrganizationBO;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author guofan.cp
 * @version : AssetRepoServiceImpl.java 2019/01/21 22:32 guofan.cp
 */
@Service
public class AssetRepoServiceImpl implements AssetRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(AssetRepoServiceImpl.class);

    @Autowired
    private BizIdFactory assetBizFactory;
    @Autowired
    private AssetDORepo assetDORepo;
    @Autowired
    private OrganizationRepoService organizationRepoService;

    /**
     * 创建物资
     *
     * @return
     * @Param assetBO
     */
    @Override
    public AssetBO createAsset(AssetBO assetBO) {
        if (StringUtils.isBlank(assetBO.getAssetId())) {
            assetBO.setAssetId(assetBizFactory.getAssetId());
        }
        return convert(assetDORepo.save(convert(assetBO)));
    }

    /**
     * 更新物资
     *
     * @param assetBO
     * @return
     */
    @Override
    public AssetBO updateAsset(AssetBO assetBO) {
        AssetDO assetDO = assetDORepo.findByAssetId(assetBO.getAssetId());
        assetDO.setStatus(assetBO.getAssetStatus());
        assetDO.setAssetName(assetBO.getAssetName());
        assetDO.setRemain(assetBO.getAssetRemain());
        assetDO.setAmount(assetBO.getAssetAmount());
        return convert(assetDORepo.save(assetDO));
    }

    /**
     * @return
     */
    @Override
    public List<AssetBO> findAll() {
        List<AssetDO> assetDOList = assetDORepo.findAll();
        return CollectionUtils.toStream(assetDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    /**
     * @param assetId
     */
    @Override
    public void deleteAsset(String assetId) {
        AssetDO assetDO = assetDORepo.findByAssetId(assetId);
        AssertUtil.assertNotNull(assetDO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资不存在");
        assetDORepo.delete(assetDO);
    }

    /**
     * 通过物资码判断物资状态
     * 返回枚举类中的code
     *
     * @param assetId
     * @return
     */
    @Override
    public String judgeStatusByAssetId(String assetId) {
        AssetDO assetDO = assetDORepo.findByAssetId(assetId);
        String assetStatusCode = null;
        //物资不存在
        if (assetDO == null) {
            assetStatusCode = AssetStatusEnum.ASSET_NOT_EXISTENCE.getCode();
            return assetStatusCode;
        }
        //物资不可借用状态分情况
        if (assetDO.getStatus().equals(AssetStatusEnum.ASSET_NOT_LOAN.getCode())) {
            //暂无物资
            if (assetDO.getAmount() - assetDO.getDestroy() == 0) {
                assetStatusCode = AssetStatusEnum.ASSET_TEMPLATE_NOT_LOAN.getCode();
            }
            //全部借出
            else {
                assetStatusCode = AssetStatusEnum.ASSET_ALL_LOAN.getCode();
            }
        }
        //物资可借用
        else {
            assetStatusCode = AssetStatusEnum.ASSET_LOAN.getCode();
        }
        return assetStatusCode;
    }

    /**
     * 查找物资
     *
     * @param assetId
     * @return
     */
    @Override
    public AssetBO findByAssetId(String assetId) {
        AssetDO assetDO = assetDORepo.findByAssetId(assetId);
        AssertUtil.assertNotNull(assetDO, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资码不存在");
        return convert(assetDO);
    }

    /**
     * @param organizationId
     * @return
     */
    @Override
    public List<AssetBO> queryAssetByOrganizationId(String organizationId) {
        List<AssetDO> assetDOList = assetDORepo.findAssetByOrginazationId(organizationId);
        return CollectionUtils.toStream(assetDOList)
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public AssetBO convert(AssetDO assetDO) {
        //封装的转换器无法实现do2bo spring静态注入问题
        if (assetDO == null) {
            return null;
        }
        OrganizationBO organizationBo = organizationRepoService.queryOrganizationByOrganizationId(assetDO.getOrginazationId());
        String organizationName = organizationBo.getOrganizationName();
        AssetBO assetBO = new AssetBO();
        assetBO.setAssetId(assetDO.getAssetId());
        assetBO.setAssetAmount(assetDO.getAmount());
        assetBO.setAssetRemain(assetDO.getRemain());
        assetBO.setAssetDestroy(assetDO.getDestroy());
        assetBO.setAssetName(assetDO.getAssetName());
        assetBO.setAssetOrganizationId(assetDO.getOrginazationId());
        assetBO.setAssetStatus(assetDO.getStatus());
        assetBO.setAssetType(assetDO.getType());
        assetBO.setCreate(assetDO.getGmtCreate());
        assetBO.setModified(assetDO.getGmtModified());
        assetBO.setAssetOrganizationName(organizationName);
        //assetBO.setExtInfo(JSON.parseObject(assetDO.getExtInfo(), Map.class));
        return assetBO;
    }

    /**
     * 物资BO2DO
     *
     * @param assetBO
     */
    public AssetDO convert(AssetBO assetBO) {
        if (assetBO == null) {
            return null;
        }
        AssetDO assetDO = new AssetDO();
        assetDO.setAmount(assetBO.getAssetAmount());
        assetDO.setAssetId(assetBO.getAssetId());
        assetDO.setAssetName(assetBO.getAssetName());
        assetDO.setOrginazationId(assetBO.getAssetOrganizationId());
        assetDO.setRemain(assetBO.getAssetRemain());
        assetDO.setDestroy(assetBO.getAssetDestroy());
        assetDO.setStatus(assetBO.getAssetStatus());
        assetDO.setType(assetBO.getAssetType());
        assetDO.setGmtCreate(assetBO.getCreate());
        assetDO.setGmtModified(assetBO.getModified());
        assetDO.setExtInfo(JSON.toJSONString(assetBO.getExtInfo()));
        return assetDO;
    }

}
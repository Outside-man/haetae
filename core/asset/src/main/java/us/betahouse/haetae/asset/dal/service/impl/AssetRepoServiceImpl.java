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
import us.betahouse.haetae.asset.dal.convert.EntityConverter;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.dal.model.AssetLoanRecordDO;
import us.betahouse.haetae.asset.dal.repo.AssetBackDORepo;
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static us.betahouse.haetae.asset.dal.convert.EntityConverter.convert;


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
        AssetDO assetDO1 = convert(assetBO);
        if (assetDO1.getAmount() != null && assetDO1.getAmount() != -1) {
            assetDO.setAmount(assetDO1.getAmount());
        }
        if (assetDO1.getAssetName() != null) {
            assetDO.setAssetName(assetDO1.getAssetName());
        }
        if (assetDO1.getDestroy() != null && assetDO1.getDestroy() != -1) {
            assetDO.setDestroy(assetDO1.getDestroy());
        }
        if (assetDO1.getOrginazationId() != null) {
            assetDO.setOrginazationId(assetDO1.getOrginazationId());
        }
        if (assetDO1.getRemain() != null && assetDO1.getRemain() != -1) {
            if (assetDO1.getRemain() > assetDO.getAmount()) {
                String a = null;
                AssertUtil.assertNotNull(a, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "物资剩余数量不能大于物资数量");
            }
            assetDO.setRemain(assetDO1.getRemain());
        }
        if (assetDO1.getStatus() != null) {
            assetDO.setStatus(assetDO1.getStatus());
        }
        if (assetDO1.getType() != null) {
            assetDO.setType(assetDO1.getType());
        }
        if (assetDO.getRemain() == 0) {
            if (assetDO.getDestroy() == assetDO.getAmount()) {
                assetDO.setStatus("allDestroy");
            } else {
                assetDO.setStatus("notLoan");
            }
        }

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
            assetStatusCode = AssetStatusEnum.ASSET_NOTEXISTENCE.getCode();
            return assetStatusCode;
        }
        //物资不可借用状态分情况
        if (assetDO.getStatus().equals("不可借")) {
            //暂无物资
            if (assetDO.getAmount() - assetDO.getDestroy() == 0) {
                assetStatusCode = AssetStatusEnum.ASSET_TEMPNOTLOAN.getCode();
            }
            //全部借出
            else {
                assetStatusCode = AssetStatusEnum.ASSET_ALLLOAN.getCode();
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
        if (assetDO == null) {
            return null;
        }
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
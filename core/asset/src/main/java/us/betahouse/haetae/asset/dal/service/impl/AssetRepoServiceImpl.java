/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.betahouse.haetae.asset.dal.convert.EntityConverter;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetBO;


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
     * @Description: 新增物资
     * @Param: [assetBO]
     */
    @Override
    public AssetBO createAsset(AssetBO assetBO) {
        if (StringUtils.isBlank(assetBO.getAssetId())) {
            assetBO.setAssetId(assetBizFactory.getAssetId());
        }
        return EntityConverter.convert(assetDORepo.save(EntityConverter.convert(assetBO)));
    }

    /**
    * @Description: 查找物资
    * @Param: [assetId]
    */
    @Override
    public AssetBO findByAssetId(String assetId) {
        AssetDO assetDo=assetDORepo.findByAssetId(assetId);
        return EntityConverter.convert(assetDo);
    }

    /**
    * @Description: 判断物资状态
    * @Param: [assetId]
    */
    @Override
    public String judgeByAssetId(String assetId) {
        final String ALLBORROW="全部借出";
        boolean isExistence=assetDORepo.existsByAssetId(assetId);
        String assetStatus;
        if(isExistence){
            return AssetStatusEnum.getByCode("物资不存在").toString();
        }
        else{
            assetStatus=assetDORepo.findByStatus(assetId);
            if("不可借"==assetStatus){
                return ALLBORROW;
            }
            else{

            }
        }
        return null;
    }
}
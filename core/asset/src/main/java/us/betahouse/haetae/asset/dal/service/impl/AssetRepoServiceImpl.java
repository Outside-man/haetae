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
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetBO;

import java.util.List;

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

//    @Override
//    public AssetBO createAsset(AssetBO assetBO) {
//        //StringUtil类判断空方法
//        if (StringUtils.isBlank(assetBO.getAssetId())) {
//            assetBO.setAssetId(assetBizFactory.getAssetId());
//        }
//        //save 返回该对象
//        return entityConverter.convert(assetDORepo.save(entityConverter.convert(assetBO)));
//    }

//    @Override
//    public AssetBO updateAsset(AssetBO assetBO) {
//        return null;
//    }
//
//    @Override
//    public List<AssetBO> queryAllAsset() {
//        return null;
//    }
//
//    @Override
//    public AssetBO queryAssetById(String assetId) {
//        return null;
//    }
//
//    @Override
//    public List<AssetBO> queryAssetByName(String name) {
//        return null;
//    }
}
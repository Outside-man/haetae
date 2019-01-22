/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.asset.dal.convert.EntityConverter;
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetBO;

/**
 * @author guofan.cp
 * @version : AssetRepoServiceImpl.java 2019/01/21 22:32 guofan.cp
 */
public class AssetRepoServiceImpl implements AssetRepoService {
    @Autowired
    private BizIdFactory assetBizFactory;
    @Autowired
    private EntityConverter entityConverter;
    @Autowired
    private AssetDORepo assetDORepo;

    @Override
    public AssetBO createAsset(AssetBO assetBO) {
        //StringUtil类判断空方法
        if (StringUtils.isBlank(assetBO.getAssetId())) {
            assetBO.setAssetId(assetBizFactory.getAssetId());
        }
        //save 返回该对象
        return entityConverter.convert(assetDORepo.save(entityConverter.convert(assetBO)));
    }
}
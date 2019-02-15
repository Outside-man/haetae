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
import us.betahouse.haetae.asset.dal.repo.AssetBackDORepo;
import us.betahouse.haetae.asset.dal.repo.AssetDORepo;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.haetae.asset.idfactory.BizIdFactory;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.utils.AssertUtil;


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
    private AssetBackDORepo assetBackDORepo;
    /**
     * 创建物资
     *
     * @Param assetBO
     * @return
     */
    @Override
    public AssetBO createAsset(AssetBO assetBO) {
        if (StringUtils.isBlank(assetBO.getAssetId())) {
            assetBO.setAssetId(assetBizFactory.getAssetId());
        }
        return EntityConverter.convert(assetDORepo.save(EntityConverter.convert(assetBO)));
    }

    /**
     * 通过物资码判断物资状态
     * 返回枚举类中的code
     * @param assetId
     * @return
     */
    @Override
    public String judgeStatusByAssetId(String assetId) {
        AssetDO assetDO=assetDORepo.findByAssetId(assetId);
        String assetStatusCode;
        //物资不存在
        if(assetDO==null){
            assetStatusCode=AssetStatusEnum.ASSET_NOTEXISTENCE.getCode();
            return assetStatusCode;
        }
        //物资不可借用状态分情况
        if(assetDO.getStatus().equals("不可借")){
            //暂无物资
            if(assetDO.getAmount()-assetDO.getDestory()==0){
                assetStatusCode=AssetStatusEnum.ASSET_TEMPNOTLOAN.getCode();
            }
            //全部借出
            else{
                assetStatusCode=AssetStatusEnum.ASSET_ALLLOAN.getCode();
            }
        }
        //物资可借用
        else{
            assetStatusCode=AssetStatusEnum.ASSET_LOAN.getCode();
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
        AssetDO assetDO=assetDORepo.findByAssetId(assetId);
        AssertUtil.assertNotNull(assetDO, RestResultCode.ILLEGAL_PARAMETERS.getCode(),"物资码不存在");
        return EntityConverter.convert(assetDO);
    }

}
/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.asset.dal.service.AssetBackRecordRepoService;
import us.betahouse.haetae.asset.manager.AssetBackRecordManager;
import us.betahouse.haetae.asset.manager.AssetLoanRecordManager;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.asset.request.AssetBackRecordRequest;
import us.betahouse.haetae.serviceimpl.asset.request.AssetLoanRecordManagerRequest;
import us.betahouse.haetae.serviceimpl.asset.service.AssetBackRecordService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.util.utils.AssertUtil;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordServiceImpl.java 2019/02/12 17:43 yiyuk.hxy
 */
@Service
public class AssetBackRecordServiceImpl implements AssetBackRecordService {
    private final Logger LOGGER = LoggerFactory.getLogger(AssetBackRecordServiceImpl.class);

    @Autowired
    private AssetManager assetManager;

    @Autowired
    private AssetLoanRecordManager assetLoanRecordManager;

    @Autowired
    private AssetBackRecordManager assetBackRecordManager;

    @Autowired
    private AssetBackRecordRepoService assetBackRecordRepoService;

    @Override
    public AssetBackRecordBO create(AssetBackRecordRequest request, OperateContext context) {
        AssertUtil.assertStringNotBlank(request.getUserId(), "用户id不能为空");

        AssetBO assetBO = assetManager.findAssetByAssetID(request.getAssetId());
        if (assetBO == null) {
            AssertUtil.assertStringNotBlank("物资码无效");
            return null;
        }
        AssetLoanRecordBO assetLoanRecordBO = assetLoanRecordManager.findAssetLoanRecordByLoanRecordId(request.getLoanRecoedId());
        if(request.getAmount() > assetLoanRecordBO.getAmount()-assetLoanRecordBO.getRemain()){
            AssertUtil.assertStringNotBlank("归还数量超出剩余未归还数量");
        }
        request.setAssetType(assetBO.getAssetType());
        AssetBackRecordBO assetBackRecordBO = assetBackRecordManager.create(request);
        return assetBackRecordBO;
    }

    @Override
    public List<AssetBackRecordBO> findAllAssetLoanRecordByAssetId(AssetBackRecordRequest request, OperateContext context) {
        return assetBackRecordManager.findAllAssetBackRecordByAssetId(request.getAssetId());
    }

    @Override
    public List<AssetBackRecordBO> findAllAssetLoanRecordByUserId(AssetBackRecordRequest request, OperateContext context) {
        return assetBackRecordManager.findAllAssetBackRecordByUserId(request.getUserId());
    }
}

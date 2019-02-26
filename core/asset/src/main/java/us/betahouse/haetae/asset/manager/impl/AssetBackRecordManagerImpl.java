/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.asset.builder.AssetBackRecordBOBulider;
import us.betahouse.haetae.asset.dal.service.AssetBackRecordRepoService;
import us.betahouse.haetae.asset.manager.AssetBackRecordManager;
import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;
import us.betahouse.haetae.asset.request.AssetBackRecordRequest;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordManagerImpl.java 2019/02/10 22:39 yiyuk.hxy
 */
@Service
public class AssetBackRecordManagerImpl implements AssetBackRecordManager {

    @Autowired
    private AssetBackRecordRepoService assetBackRecordRepoService;

    /**
     * @param request
     * @return
     */
    @Override
    public AssetBackRecordBO create(AssetBackRecordRequest request) {
        AssetBackRecordBOBulider bulider = AssetBackRecordBOBulider.getInstance()
                .withAmount(request.getAmount())
                .withAssetId(request.getAssetId())
                .withAssetType(request.getAssetType())
                .withBackRecoedId(request.getBackRecoedId())
                .withExtInfo(request.getExtInfo())
                .withLoanRecoedId(request.getLoanRecoedId())
                .withRemark(request.getRemark())
                .withType(request.getType())
                .withUserId(request.getUserId());
        return assetBackRecordRepoService.createAssetBackRecord(bulider.build());
    }

    /**
     * @param assetId
     * @return
     */
    @Override
    public List<AssetBackRecordBO> findAllAssetBackRecordByAssetId(String assetId) {
        return assetBackRecordRepoService.queryAssetBackRecordByAssetId(assetId);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<AssetBackRecordBO> findAllAssetBackRecordByUserId(String userId) {
        return assetBackRecordRepoService.queryAssetBackRecordByUserId(userId);
    }

    /**
     * @param loanRecordId
     * @return
     */
    @Override
    public List<AssetBackRecordBO> findAssetBackRecordByLoanRecordId(String loanRecordId) {
        return assetBackRecordRepoService.findAssetBackRecordByLoanRecordId(loanRecordId);
    }
}

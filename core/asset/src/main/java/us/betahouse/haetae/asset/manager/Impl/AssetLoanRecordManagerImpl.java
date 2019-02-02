/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.asset.builder.AssetLoanRecordBOBulider;
import us.betahouse.haetae.asset.dal.service.AssetLoanRecordRepoService;
import us.betahouse.haetae.asset.manager.AssetLoanRecordManager;
import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.asset.request.AssetLoanRecordRequest;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordManagerImpl.java 2019/01/25 10:37 yiyuk.hxy
 */
@Service
public class AssetLoanRecordManagerImpl implements AssetLoanRecordManager {

    @Autowired
    private AssetLoanRecordRepoService assetLoanRecordRepoService;

    @Override
    public AssetLoanRecordBO create(AssetLoanRecordRequest request) {
        AssetLoanRecordBOBulider bulider = AssetLoanRecordBOBulider.getInstance()
                .withLoanRecoedId(request.getLoanRecordId())
                .withAssetId(request.getAssetId())
                .withAssetType(request.getAssetType())
                .withAmount(request.getAmount())
                .withRemain(request.getRemain())
                .withStatus(request.getStatus())
                .withUserId(request.getUserId())
                .withRemark(request.getRemark())
                .withExtInfo(request.getExtInfo())
                .withAssetInfo(request.getAssetInfo());
        return assetLoanRecordRepoService.createAssetLoanRecord(bulider.build());
    }

    @Override
    public AssetLoanRecordBO update(AssetLoanRecordRequest request) {
        AssetLoanRecordBOBulider bulider = AssetLoanRecordBOBulider.getInstance()
                .withLoanRecoedId(request.getLoanRecordId())
                .withAssetId(request.getAssetId())
                .withAssetType(request.getAssetType())
                .withAmount(request.getAmount())
                .withRemain(request.getRemain())
                .withStatus(request.getStatus())
                .withUserId(request.getUserId())
                .withRemark(request.getRemark())
                .withExtInfo(request.getExtInfo())
                .withAssetInfo(request.getAssetInfo());
        return assetLoanRecordRepoService.updateAssetLoanRecord(bulider.build());
    }

    @Override
    public List<AssetLoanRecordBO> findAssetLoanRecordByUserId(String userId) {
        return assetLoanRecordRepoService.queryAssetLoanRecordByUserId(userId);
    }

    @Override
    public List<AssetLoanRecordBO> findAllAssetLoanRecordByAssetId(String assetId) {
        return assetLoanRecordRepoService.queryAllAssetLoanRecordByAssetId(assetId);
    }

    @Override
    public List<AssetLoanRecordBO> findDistoryRecordByAssetId(String assetId) {
        return assetLoanRecordRepoService.queryDistoryRecordByAssetId(assetId);
    }

    @Override
    public List<AssetLoanRecordBO> findAssetLoanRecordByAssetId(String assetId) {
        return assetLoanRecordRepoService.queryAssetLoanRecordByAssetId(assetId);
    }

}

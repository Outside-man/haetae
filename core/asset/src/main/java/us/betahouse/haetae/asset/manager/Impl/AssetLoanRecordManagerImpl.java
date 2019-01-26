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

import java.util.Date;
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
        if (request.getLoanTime() == null){
            request.setLoanTime(new Date());
        }
        AssetLoanRecordBOBulider bulider = AssetLoanRecordBOBulider.getInstance()
                .withLoanRecoedId(request.getLoanRecordId())
                .withAssetId(request.getAssetId())
                .withAssetType(request.getAssetType())
                .withAmount(request.getAmount())
                .withRemain(request.getRemain())
                .withLoanTime(request.getLoanTime())
                .withBackTime(request.getBackTime())
                .withStatus(request.getStatus())
                .withUserId(request.getUserId())
                .withRemark(request.getRemark())
                .withExtInfo(request.getExtInfo());
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
                .withLoanTime(request.getLoanTime())
                .withBackTime(request.getBackTime())
                .withStatus(request.getStatus())
                .withUserId(request.getUserId())
                .withRemark(request.getRemark())
                .withExtInfo(request.getExtInfo());
        return assetLoanRecordRepoService.updateAssetLoanRecord(bulider.build());
    }

    @Override
    public List<AssetLoanRecordBO> findByUserId(String userId) {
        return assetLoanRecordRepoService.queryAssetLoanRecordByUserId(userId);
    }

    @Override
    public List<AssetLoanRecordBO> findByAssetId(String assetId) {
        return assetLoanRecordRepoService.findAssetLoanRecordByAssetId(assetId);
    }
}

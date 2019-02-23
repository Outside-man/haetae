/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.manager;

import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;
import us.betahouse.haetae.asset.request.AssetBackRecordRequest;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordManager.java 2019/02/10 22:39 yiyuk.hxy
 */
public interface AssetBackRecordManager {
    /**
     * 创建记录
     *
     * @param request
     * @return
     */
    AssetBackRecordBO create(AssetBackRecordRequest request);

    /**
     * 查询所有归还记录
     *
     * @param assetId
     * @return
     */
    List<AssetBackRecordBO> findAllAssetBackRecordByAssetId(String assetId);

    /**
     * 查询所有归还记录
     *
     * @param userId
     * @return
     */
    List<AssetBackRecordBO> findAllAssetBackRecordByUserId(String userId);

    /**
     * @param loanRecordId
     * @return
     */
    AssetBackRecordBO findAssetBackRecordByLoanRecordId(String loanRecordId);
}

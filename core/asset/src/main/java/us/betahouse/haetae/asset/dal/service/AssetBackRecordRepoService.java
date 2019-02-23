/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service;

import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordRepoService.java 2019/02/11 12:27 yiyuk.hxy
 */
public interface AssetBackRecordRepoService {
    /**
     * 新增归还记录
     *
     * @param assetBackRecordBO
     * @return
     */
    AssetBackRecordBO createAssetBackRecord(AssetBackRecordBO assetBackRecordBO);

    /**
     * 通过用户id获取借用记录
     *
     * @param userId
     * @return
     */
    List<AssetBackRecordBO> queryAssetBackRecordByUserId(String userId);

    /**
     * 通过物资id获取借用记录
     *
     * @param assetId
     * @return
     */
    List<AssetBackRecordBO> queryAssetBackRecordByAssetId(String assetId);

    /**
     * 通过借用记录id获取
     *
     * @param loanRecordId
     * @return
     */
    AssetBackRecordBO findAssetBackRecordByLoanRecordId(String loanRecordId);
}

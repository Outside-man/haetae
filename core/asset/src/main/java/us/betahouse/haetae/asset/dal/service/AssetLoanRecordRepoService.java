/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service;

import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordRepoService.java 2019/01/23 20:18 yiyuk.hxy
 */
public interface AssetLoanRecordRepoService {
    /**
     * 新增借用记录
     *
     * @param assetLoanRecordBO
     * @return
     */
    AssetLoanRecordBO createAssetLoanRecord(AssetLoanRecordBO assetLoanRecordBO);

    /**
     * 更新借用记录
     *
     * @param assetLoanRecordBO
     * @return
     */
    AssetLoanRecordBO updateAssetLoanRecord(AssetLoanRecordBO assetLoanRecordBO);

    /**
     * 通过用户id获取所有记录
     *
     * @param userId
     * @return
     */
    List<AssetLoanRecordBO> queryAssetLoanRecordByUserId(String userId);

    /**
     * 通过借用记录id获取
     *
     * @param loanId
     * @return
     */
    AssetLoanRecordBO findAssetLoanRecordByLoadId(String loanId);

    /**
     * 通过物资id获取所有记录
     *
     * @param assetId
     * @return
     */
    List<AssetLoanRecordBO> queryAllAssetLoanRecordByAssetId(String assetId);

    /**
     * 通过物资id获取报损记录
     *
     * @param assetId
     * @return
     */
    List<AssetLoanRecordBO> queryDistoryRecordByAssetId(String assetId);

    /**
     * 通过物资id获取借用记录
     *
     * @param assetId
     * @return
     */
    List<AssetLoanRecordBO> queryAssetLoanRecordByAssetId(String assetId);

    /**
     * 通过名称查找
     *
     * @param name
     * @return
     */
    List<AssetLoanRecordBO> queryAssetLoanRecordByName(String name);

    /**
     * 通过物资id删除物资借用记录
     *
     * @param assetId
     * @return
     */
    void delete(String assetId);
}

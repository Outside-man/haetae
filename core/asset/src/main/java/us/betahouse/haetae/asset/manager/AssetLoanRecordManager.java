/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.manager;

import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.asset.request.AssetLoanRecordRequest;

import java.util.List;

/**
 * 物资借用记录管理器
 *
 * @author yiyuk.hxy
 * @version : AssetLoanRecordManager.java 2019/01/23 23:37 yiyuk.hxy
 */
public interface AssetLoanRecordManager {
    /**
     * 创建记录
     *
     * @param request
     * @return
     */
    AssetLoanRecordBO create(AssetLoanRecordRequest request);

    /**
     * 更新记录（归还时间与归还/报损情况）
     *
     * @param request
     * @return
     */
    AssetLoanRecordBO update(AssetLoanRecordRequest request);

    /**
     * 根据借用记录id查询
     *
     * @param loanRecordId
     * @return
     */
    AssetLoanRecordBO findAssetLoanRecordByLoanRecordId(String loanRecordId);

    /**
     * 通过用户id查询记录
     *
     * @param userId
     * @return
     */
    List<AssetLoanRecordBO> findAssetLoanRecordByUserId(String userId);

    /**
     * 通过物资id查询所有借用记录
     *
     * @param assetId
     * @return
     */
    List<AssetLoanRecordBO> findAllAssetLoanRecordByAssetId(String assetId);

    /**
     * 通过物资id查询报损记录
     *
     * @param assetId
     * @return
     */
    List<AssetLoanRecordBO> findDistoryRecordByAssetId(String assetId);
    /**
     * 通过物资id查询还在借出状态的借用记录
     *
     * @param assetId
     * @return
     */
    List<AssetLoanRecordBO> findAssetLoanRecordByAssetId(String assetId);
}

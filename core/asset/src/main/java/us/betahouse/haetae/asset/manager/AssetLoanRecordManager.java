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
     * 通过用户id查询记录
     *
     * @param userId
     * @return
     */
    List<AssetLoanRecordBO> findByUserId(String userId);

    /**
     * 通过物资id查询借用记录
     *
     * @param assetId
     * @return
     */
    List<AssetLoanRecordBO> findByAssetId(String assetId);
}

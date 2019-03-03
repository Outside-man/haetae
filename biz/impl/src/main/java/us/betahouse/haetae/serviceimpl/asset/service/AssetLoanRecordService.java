/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service;

import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;
import us.betahouse.haetae.asset.request.AssetLoanRecordRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordService.java 2019/01/25 15:48 yiyuk.hxy
 */
public interface AssetLoanRecordService {
    /**
     * 创建借用记录
     *
     * @param request
     * @param context
     * @return
     */
    AssetLoanRecordBO create(AssetLoanRecordRequest request, OperateContext context);

    /**
     * 通过物资id查询该物资所有借用记录
     *
     * @param request
     * @param context
     * @return
     */
    List<AssetLoanRecordBO> findAllAssetLoanRecordByAssetId(AssetLoanRecordRequest request, OperateContext context);
    /**
     * 查询该用户所有借用记录
     *
     * @param request
     * @param context
     * @return
     */
    List<AssetLoanRecordBO> findAllAssetLoanRecordByUserId(AssetLoanRecordRequest request, OperateContext context);

    /**
     * 通过借用记录id查询该物资借用记录详细信息
     *
     * @param request
     * @param context
     * @return
     */
    AssetLoanRecordBO findAssetLoanRecordByLoanRecordId(AssetLoanRecordRequest request, OperateContext context);
}

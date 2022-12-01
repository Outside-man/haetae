/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service;

import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;
import us.betahouse.haetae.asset.request.AssetBackRecordRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordService.java 2019/02/12 17:42 yiyuk.hxy
 */
public interface AssetBackRecordService {
    /**
     * 创建归还记录
     *
     * @param request
     * @param context
     * @return
     */
    AssetBackRecordBO create(AssetBackRecordRequest request, OperateContext context);



    /**
     *
     * @param request
     * @param context
     * @return
     */
    List<AssetBackRecordBO> findAllAssetBackRecordByAssetId(AssetBackRecordRequest request, OperateContext context);

    /**
     *
     * @param request
     * @param context
     * @return
     */
    List<AssetBackRecordBO> findAllAssetBackRecordByUserId(AssetBackRecordRequest request, OperateContext context);

    /**
     * @param request
     * @param context
     * @return
     */
    List<AssetBackRecordBO> findAllAssetBackRecordByLoanRecordId(AssetBackRecordRequest request, OperateContext context);
}

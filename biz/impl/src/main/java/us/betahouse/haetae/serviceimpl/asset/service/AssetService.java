/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.service;

import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.model.basic.AssetRecordBO;
import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;
import us.betahouse.haetae.serviceimpl.common.OperateContext;

import java.util.List;

/**
 * 物资业务
 *
 * @author yiyuk.hxy
 * @version : AssetService.java 2019/01/22 14:26 yiyuk.hxy
 */
public interface AssetService {
    /**
     * 创建物资
     *
     * @param request
     * @param context
     * @return
     */
    AssetBO create(AssetManagerRequest request, OperateContext context);

    /**
     * 更新物资
     *
     * @param request
     * @param context
     * @return
     */
    AssetBO update(AssetManagerRequest request, OperateContext context);

    /**
     * @param request
     * @param context
     */
    void delete(AssetManagerRequest request, OperateContext context);

    /**
     * 通过物资Id返回AssetBo
     *
     * @param request
     * @param context
     * @return
     */
    AssetBO findAssetByAssetId(AssetManagerRequest request, OperateContext context);

    /**
     * 判断物资id 返回记录结果集
     *
     * @param request
     * @param context
     * @return
     */
    List<AssetRecordBO> findRecodByAssetStatus(AssetManagerRequest request, OperateContext context);

    /**
     * 查询组织物资
     *
     * @param request
     * @param context
     * @return
     */
    List<AssetBO> queryAssetByOrganizationId(AssetManagerRequest request, OperateContext context);

}

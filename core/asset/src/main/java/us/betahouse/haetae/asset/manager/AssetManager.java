/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.manager;

import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.request.AssetRequest;

import java.util.List;

/**
 * 物资管理器
 *
 * @author guofan.cp
 * @version : AssetManager.java 2019/01/24 9:32 guofan.cp
 */
public interface AssetManager {
    /**
     * 创建物资
     *
     * @param request
     * @return
     */
    AssetBO create(AssetRequest request);

    /**
     * 更新物资
     *
     * @param request
     * @return
     */
    AssetBO update(AssetRequest request);

    /**
     * @param assetId
     * @return
     */
    void delete(String assetId);

    /**
     * 通过Id获取物资
     *
     * @param assetId
     * @return
     */
    AssetBO findAssetByAssetID(String assetId);

    /**
     * @param organizationId
     * @return
     */
    List<AssetBO> queryAssetByOrganizationId(String organizationId);
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.service;

import us.betahouse.haetae.asset.model.basic.AssetBO;

import java.util.List;

/**
 * @author guofan.cp
 * @version : AssetRepoService.java 2019/01/21 22:31 guofan.cp
 */
public interface AssetRepoService {
    /**
     * 新增物资
     *
     * @return
     * @Param assetBO
     */
    AssetBO createAsset(AssetBO assetBO);

    /**
     * 更新物资
     *
     * @param assetBO
     * @return
     */
    AssetBO updateAsset(AssetBO assetBO);

    /**
     *
     * @param assetId
     */
    void deleteAsset(String assetId);

    /**
     * 判断物资状态
     *
     * @param assetId
     * @return
     */
    String judgeStatusByAssetId(String assetId);

    /**
     * 查找物资
     *
     * @param assetId
     * @return
     */
    AssetBO findByAssetId(String assetId);

    /**
     * 根据组织id获取物资
     *
     * @param organizationId
     * @return
     */
    List<AssetBO> queryAssetByOrganizationId(String organizationId);

}

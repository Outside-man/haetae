/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.asset.builder.AssetBOBuilder;
import us.betahouse.haetae.asset.dal.service.AssetRepoService;
import us.betahouse.haetae.asset.manager.AssetManager;
import us.betahouse.haetae.asset.model.basic.AssetBO;
import us.betahouse.haetae.asset.request.AssetRequest;

import java.util.List;

/**
 * @author guofan.cp
 * @version : AssetManagerInpl.java 2019/01/24 9:33 guofan.cp
 */
@Service
public class AssetManagerImpl implements AssetManager {
    @Autowired
    private AssetRepoService assetRepoService;

    /**
     * 创建物资
     *
     * @param request
     * @return
     */
    @Override
    public AssetBO create(AssetRequest request) {
        AssetBOBuilder assetBOBuilder = AssetBOBuilder.getInstance()
                .withAssetName(request.getAssetName())
                .withAssetType(request.getAssetType())
                .withAssetAmount(request.getAssetAmount())
                .withAssetOrginnaztionId(request.getAssetOrganizationId())
                .withAssetRemain(request.getAssetRemain())
                .withAssetDestroy(request.getAssetDestroy())
                .withAssetStatus(request.getAssetStatus())
                .withAssetOrginnaztionName(request.getAssetOrganizationName());
        return assetRepoService.createAsset(assetBOBuilder.builder());
    }

    @Override
    public AssetBO update(AssetRequest request) {
        AssetBOBuilder assetBOBuilder = AssetBOBuilder.getInstance()
                .withAssetId(request.getAssetId())
                .withAssetName(request.getAssetName())
                .withAssetType(request.getAssetType())
                .withAssetOrginnaztionId(request.getAssetOrganizationId())
                .withAssetStatus(request.getAssetStatus())
                .withAssetRemain(request.getAssetRemain())
                .withAssetAmount(request.getAssetAmount())
                .withAssetDestroy(request.getAssetDestroy())
                .withAssetOrginnaztionName(request.getAssetOrganizationName());
        return assetRepoService.updateAsset(assetBOBuilder.builder());
    }

    @Override
    public void delete(String assetId) {
        assetRepoService.deleteAsset(assetId);
    }

    @Override
    public List<AssetBO> findAll() {
        return assetRepoService.findAll();
    }

    /**
     * 查找物资
     *
     * @param assetId
     * @return
     */
    @Override
    public AssetBO findAssetByAssetID(String assetId) {
        return assetRepoService.findByAssetId(assetId);
    }

    /**
     * +     * @param organizationId
     *
     * @return
     */
    @Override
    public List<AssetBO> queryAssetByOrganizationId(String organizationId) {
        return assetRepoService.queryAssetByOrganizationId(organizationId);
    }
}

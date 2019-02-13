/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.builder;

import us.betahouse.haetae.asset.model.basic.AssetBO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author guofan.cp
 * @version : AssetBOBuilder.java 2019/01/24 23:40 guofan.cp
 */
public class AssetBOBuilder {
    private String assetId;
    private String assetName;
    private Date assetCreate;
    private Date assetModified;
    private int assetAmount;
    private String assetOrginnaztionId;
    private String AssetOrginnaztionName;
    private int assetRemain;
    private String assetStatus;
    private String assetType;
    private Map<String, String> extInfo = new HashMap<>();

    private AssetBOBuilder() {
    }

    public static AssetBOBuilder getInstance() {
        return new AssetBOBuilder();
    }

    public AssetBOBuilder withAssetId(String assetId) {
        this.assetId = assetId;
        return this;
    }

    public AssetBOBuilder withAssetName(String assetName) {
        this.assetName = assetName;
        return this;
    }

    public AssetBOBuilder withAssetCreate(Date assetCreate) {
        this.assetCreate = assetCreate;
        return this;
    }

    public AssetBOBuilder withAssetModified(Date assetModified) {
        this.assetModified = assetModified;
        return this;
    }

    public AssetBOBuilder withAssetMount(int assetAmount) {
        this.assetAmount = assetAmount;
        return this;
    }

    public AssetBOBuilder withAssetOrginnaztionId(String assetOrginnaztionId) {
        this.assetOrginnaztionId = assetOrginnaztionId;
        return this;
    }

    public AssetBOBuilder withAssetOrginnaztionName(String getAssetOrginnaztionName) {
        this.AssetOrginnaztionName = getAssetOrginnaztionName;
        return this;
    }

    public AssetBOBuilder withAssetRemain(int assetRemain) {
        this.assetRemain = assetRemain;
        return this;
    }

    public AssetBOBuilder withAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
        return this;
    }

    public AssetBOBuilder withAssetType(String assetType) {
        this.assetType = assetType;
        return this;
    }

    public AssetBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public AssetBO builder() {
        AssetBO assetBO = new AssetBO();
        assetBO.setAssetId(assetId);
        assetBO.setAssetName(assetName);
        assetBO.setAssetType(assetType);
        assetBO.setAssetAmount(assetAmount);
        assetBO.setModified(assetModified);
        assetBO.setCreate(assetCreate);
        assetBO.setExtInfo(extInfo);
        assetBO.setAssetStatus(assetStatus);
        assetBO.setAssetRemain(assetRemain);
        assetBO.setAssetOrganizationId(assetOrginnaztionId);
        assetBO.setAssetOrganizationName(AssetOrginnaztionName);
        return assetBO;
    }
}

/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.asset.request;

import us.betahouse.haetae.common.RestRequest;

/**
 * @author yiyuk.hxy
 * @version : AssetRestRequest.java 2019/01/21 13:04 yiyuk.hxy
 */
public class AssetRestRequest extends RestRequest {

    private static final long serialVersionUID = 7577993495265048875L;

    /**
     * 物资id
     */
    private  String assetId;
    /**
     *物资名字
     */
    private String assetName;
    /**
     * 物资备注信息
     */
    private String assetExtInfo;
    /**
     * 物资总数量
     */
    private Integer assetAmount;
    /**
     * 物资所属组织id
     */
    private String organizationId;
    /**
     * 物资所属组织名字
     */
    private String organizationName;
    /**
     * 物资剩余数量
     */
    private Integer assetRemain;
    /**
     * 物资类别
     */
    private String assetType;

    /**
     * 物资创建者
     */
    private  String userId;

    private Integer assetDestroy;

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetExtInfo() {
        return assetExtInfo;
    }

    public void setAssetExtInfo(String assetExtInfo) {
        this.assetExtInfo = assetExtInfo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(Integer assetAmount) {
        this.assetAmount = assetAmount;
    }

    public Integer getAssetRemain() {
        return assetRemain;
    }

    public void setAssetRemain(Integer assetRemain) {
        this.assetRemain = assetRemain;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Integer getAssetDestroy() {
        return assetDestroy;
    }

    public void setAssetDestroy(Integer assetDestroy) {
        this.assetDestroy = assetDestroy;
    }
}

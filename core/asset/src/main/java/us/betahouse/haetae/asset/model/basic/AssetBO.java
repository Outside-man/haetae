/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.model.basic;

import us.betahouse.haetae.asset.enums.AssetStatusEnum;
import us.betahouse.util.common.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 物资领域对象
 * 暂时还不知道乍用
 *
 * @author guofan.cp
 * @version : AssetBO.java 2019/01/21 22:53 guofan.cp
 */
public class AssetBO extends ToString {
    private static final long serialVersionUID = -3850765153043424655L;
    /**
     * 物资id
     */
    private String assetId;
    /**
     * 物资名字
     */
    private String assetName;
    /**
     * 物资创建时间
     */
    private Date create;
    /**
     * 物资修改时间
     */
    private Date modified;
    /**
     * 物资数量
     */
    private int assetAmount;
    /**
     * 物资归属组织id
     */
    private String assetOrganizationId;
    /**
     * 物资归属组织名字
     */
    private String AssetOrganizationName;
    /**
     * 物资剩余
     */
    private int assetRemain;
    /**
     * 物资状态
     */
    private String assetStatus;
    /**
     * 物资类型
     */
    private String assetType;
    /**
     * 物资额外信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    /**
     * 判断是否可借用
     *
     * @return
     */
    public boolean canLoan() {
        AssetStatusEnum assetStatusEnum = AssetStatusEnum.getByCode(assetType);
        if (assetStatusEnum == null) {
            return false;
        }
        switch (assetStatusEnum) {
            case ASSET_LOAN:
                return true;
            default:
                return false;
        }
    }

    public String fecthExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }

    public void putExtInfo(String key, String value) {
        if (extInfo == null) {
            extInfo = new HashMap<>();
        }
        extInfo.put(key, value);
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

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getAssetOrganizationId() {
        return assetOrganizationId;
    }

    public void setAssetOrganizationId(String assetOrganizationId) {
        this.assetOrganizationId = assetOrganizationId;
    }

    public String getAssetOrganizationName() {
        return AssetOrganizationName;
    }

    public void setAssetOrganizationName(String assetOrganizationName) {
        AssetOrganizationName = assetOrganizationName;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(int assetAmount) {
        this.assetAmount = assetAmount;
    }

    public int getAssetRemain() {
        return assetRemain;
    }

    public void setAssetRemain(int assetRemain) {
        this.assetRemain = assetRemain;
    }
}

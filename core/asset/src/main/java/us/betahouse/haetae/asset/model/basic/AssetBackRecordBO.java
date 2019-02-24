/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.model.basic;

import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordBO.java 2019/01/23 21:05 yiyuk.hxy
 */
public class AssetBackRecordBO extends ToString {
    private static final long serialVersionUID = 3284782905525291362L;
    /**
     * 物资归还记录ID
     */
    private String backRecoedId;
    /**
     * 物资ID
     */
    private String assetId;
    /**
     * 物资种类
     */
    private String assetType;
    /**
     * 物资借取记录ID
     */
    private String loanRecoedId;
    /**
     * 借取人ID
     */
    private String userId;
    /**
     * 物资数量
     */
    private Integer amount;
    /**
     * 归还/报销
     */
    private String type;
    /**
     * 备注，用于报损时
     */
    private String remark;
    private String assetName;
    private String backTime;
    private Map<String, String> extInfo = new HashMap<>();

    public String fetchExtInfo(String key) {
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBackRecoedId() {
        return backRecoedId;
    }

    public void setBackRecoedId(String backRecoedId) {
        this.backRecoedId = backRecoedId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getLoanRecoedId() {
        return loanRecoedId;
    }

    public void setLoanRecoedId(String loanRecoedId) {
        this.loanRecoedId = loanRecoedId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

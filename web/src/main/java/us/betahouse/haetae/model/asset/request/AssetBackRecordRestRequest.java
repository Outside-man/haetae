/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.asset.request;

import us.betahouse.haetae.common.RestRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordRestRequest.java 2019/02/12 20:12 yiyuk.hxy
 */
public class AssetBackRecordRestRequest extends RestRequest {
    private static final long serialVersionUID = 7597108455802717599L;
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
    private String loanRecordId;
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
    private Map<String, String> extInfo = new HashMap<>();

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

    public String getLoanRecordId() {
        return loanRecordId;
    }

    public void setLoanRecordId(String loanRecordId) {
        this.loanRecordId = loanRecordId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
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

    @Override
    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

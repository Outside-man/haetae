/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.asset.request;

import us.betahouse.haetae.common.RestRequest;

import java.util.Date;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordRestRequest.java 2019/01/26 17:40 yiyuk.hxy
 */
public class AssetLoanRecordRestRequest extends RestRequest {
    private static final long serialVersionUID = 7597108455802717599L;
    private String loanRecordId;
    private String assetId;
    private String userId;
    private String assetType;
    private Date loanTime;
    private Date backTime;
    private String status;
    private Integer amount;
    private String remark;
    private Integer remain;
    private Integer distory;
    private String assetInfo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLoanRecordId() {
        return loanRecordId;
    }

    public void setLoanRecordId(String loanRecordId) {
        this.loanRecordId = loanRecordId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDistory() {
        return distory;
    }

    public void setDistory(Integer distory) {
        this.distory = distory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public String getAssetInfo() {
        return assetInfo;
    }

    public void setAssetInfo(String assetInfo) {
        this.assetInfo = assetInfo;
    }
}

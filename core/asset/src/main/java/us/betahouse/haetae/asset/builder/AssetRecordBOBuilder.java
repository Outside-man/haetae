/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.builder;

import us.betahouse.haetae.asset.model.basic.AssetRecordBO;

import java.util.Date;

/**
 * @author guofan.cp
 * @version : AssetRecordBOBuilder.java 2019/02/15 23:55 guofan.cp
 */
public class AssetRecordBOBuilder {
    private String assetId;
    private String assetType;
    private String loanRecordId;
    private String userId;
    private Date loanTime;
    private Date backTime;
    private String status;
    private Integer loanamount;
    private Integer remain;
    private Integer distory;
    private String remark;
    private String assetInfo;
    private String backRecoedId;
    private Integer backAmount;
    private String backType;
    private String backRemark;

    public AssetRecordBOBuilder() {
    }
    public static AssetRecordBOBuilder getInstance() {
        return new AssetRecordBOBuilder();
    }

    public AssetRecordBOBuilder withAssetId(String assetId) {
        this.assetId = assetId;
        return this;
    }

    public AssetRecordBOBuilder withAssetType(String assetType) {
        this.assetType = assetType;
        return this;
    }
    public AssetRecordBOBuilder withLoanRecordId(String loanRecordId) {
        this.loanRecordId = loanRecordId;
        return this;
    }
    public AssetRecordBOBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }
    public AssetRecordBOBuilder withLoanTime(Date loanTime) {
        this.loanTime = loanTime;
        return this;
    }
    public AssetRecordBOBuilder withBackTime(Date backTime) {
        this.backTime = backTime;
        return this;
    }
    public AssetRecordBOBuilder withStatus(String status) {
        this.status = status;
        return this;
    }
    public AssetRecordBOBuilder withLoanamount(Integer loanamount) {
        this.loanamount = loanamount;
        return this;
    }
    public AssetRecordBOBuilder withRemain(Integer remain) {
        this.remain = remain;
        return this;
    }
    public AssetRecordBOBuilder withDistory(Integer distory) {
        this.distory = distory;
        return this;
    }
    public AssetRecordBOBuilder withRemark(String remark) {
        this.remark = remark;
        return this;
    }
    public AssetRecordBOBuilder withAssetInfo(String assetInfo) {
        this.assetInfo = assetInfo;
        return this;
    }
    public AssetRecordBOBuilder withBackRecoedId(String backRecoedId) {
        this.backRecoedId = backRecoedId;
        return this;
    }
    public AssetRecordBOBuilder withBackAmount(Integer backAmount) {
        this.backAmount = backAmount;
        return this;
    }
    public AssetRecordBOBuilder withBackType(String backType) {
        this.backType = backType;
        return this;
    }
    public AssetRecordBOBuilder withBackRemark(String backRemark) {
        this.backRemark = backRemark;
        return this;
    }
    public AssetRecordBO builder() {
        AssetRecordBO assetRecordBO=new AssetRecordBO();
        assetRecordBO.setAssetId(assetId);
        assetRecordBO.setAssetInfo(assetInfo);
        assetRecordBO.setAssetType(assetType);
        assetRecordBO.setBackAmount(backAmount);
        assetRecordBO.setBackRecoedId(backRecoedId);
        assetRecordBO.setBackRemark(backRemark);
        assetRecordBO.setBackTime(backTime);
        assetRecordBO.setBackType(backType);
        assetRecordBO.setDistory(distory);
        assetRecordBO.setLoanamount(loanamount);
        assetRecordBO.setLoanRecordId(loanRecordId);
        assetRecordBO.setLoanTime(loanTime);
        assetRecordBO.setRemain(remain);
        assetRecordBO.setBackRecoedId(backRecoedId);
        assetRecordBO.setStatus(status);
        assetRecordBO.setUserId(userId);
        return assetRecordBO;
    }
}

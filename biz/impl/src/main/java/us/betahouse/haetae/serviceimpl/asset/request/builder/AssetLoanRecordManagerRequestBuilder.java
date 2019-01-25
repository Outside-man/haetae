/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.request.builder;

import us.betahouse.haetae.asset.manager.AssetLoanRecordManager;
import us.betahouse.haetae.serviceimpl.asset.request.AssetLoanRecordManagerRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordManagerRequestBuilder.java 2019/01/26 0:54 yiyuk.hxy
 */
public class AssetLoanRecordManagerRequestBuilder {
    /**
     * 物资借取记录ID
     */
    private String loanRecordId;
    /**
     * 物资ID
     */
    private String assetId;
    /**
     * 物资种类
     */
    private String assetType;
    /**
     * 借取时间
     */
    private Date loanTime;
    /**
     * 最后一次归还时间
     */
    private Date backTime;
    /**
     * 借取人ID
     */
    private String userId;
    /**
     * 是否全部归还/报损
     */
    private String status;
    /**
     * 借用数量
     */
    private Integer amount;
    /**
     * 备注，用于报损时
     */
    private String remark;

    private Map<String, String> extInfo = new HashMap<>();

    private AssetLoanRecordManagerRequestBuilder() {
    }

    public static AssetLoanRecordManagerRequestBuilder getInstance() {
        return new AssetLoanRecordManagerRequestBuilder();
    }

    public AssetLoanRecordManagerRequestBuilder withLoanRecordId(String val) {
        loanRecordId = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withAssetId(String val) {
        assetId = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withAssetType(String val) {
        assetType = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withLoanTime(Date val) {
        loanTime = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withBackTime(Date val) {
        backTime = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withUserId(String val) {
        userId = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withStatus(String val) {
        status = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withAmount(Integer val) {
        amount = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withRemark(String val) {
        remark = val;
        return this;
    }

    public AssetLoanRecordManagerRequestBuilder withExtInfo(Map<String, String> val) {
        extInfo = val;
        return this;
    }

    public AssetLoanRecordManagerRequest build() {
        AssetLoanRecordManagerRequest assetLoanRecordManagerRequest = new AssetLoanRecordManagerRequest();
        assetLoanRecordManagerRequest.setLoanRecordId(loanRecordId);
        assetLoanRecordManagerRequest.setAssetId(assetId);
        assetLoanRecordManagerRequest.setAssetType(assetType);
        assetLoanRecordManagerRequest.setLoanTime(loanTime);
        assetLoanRecordManagerRequest.setBackTime(backTime);
        assetLoanRecordManagerRequest.setUserId(userId);
        assetLoanRecordManagerRequest.setStatus(status);
        assetLoanRecordManagerRequest.setAmount(amount);
        assetLoanRecordManagerRequest.setRemark(remark);
        assetLoanRecordManagerRequest.setExtInfo(extInfo);
        return assetLoanRecordManagerRequest;
    }

}

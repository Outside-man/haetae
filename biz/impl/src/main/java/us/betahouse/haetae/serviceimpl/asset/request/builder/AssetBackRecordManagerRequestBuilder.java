/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.request.builder;

import us.betahouse.haetae.serviceimpl.asset.request.AssetBackRecordManagerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordManagerRequestBuilder.java 2019/02/12 17:37 yiyuk.hxy
 */
public class AssetBackRecordManagerRequestBuilder {
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
    private Map<String, String> extInfo = new HashMap<>();

    private AssetBackRecordManagerRequestBuilder() {
    }

    public static AssetBackRecordManagerRequestBuilder getInstance() {
        return new AssetBackRecordManagerRequestBuilder();
    }

    public AssetBackRecordManagerRequestBuilder withBackRecoedId(String val) {
        backRecoedId = val;
        return this;
    }

    public AssetBackRecordManagerRequestBuilder withAssetId(String val) {
        assetId = val;
        return this;
    }

    public AssetBackRecordManagerRequestBuilder withAssetType(String val) {
        assetType = val;
        return this;
    }

    public AssetBackRecordManagerRequestBuilder withLoanRecoedId(String val) {
        loanRecoedId = val;
        return this;
    }

    public AssetBackRecordManagerRequestBuilder withUserId(String val) {
        userId = val;
        return this;
    }

    public AssetBackRecordManagerRequestBuilder withAmount(Integer val) {
        amount = val;
        return this;
    }

    public AssetBackRecordManagerRequestBuilder withType(String val) {
        type = val;
        return this;
    }

    public AssetBackRecordManagerRequestBuilder withRemark(String val) {
        remark = val;
        return this;
    }

    public AssetBackRecordManagerRequestBuilder withExtInfo(Map<String, String> val) {
        extInfo = val;
        return this;
    }

    public AssetBackRecordManagerRequest build() {
        AssetBackRecordManagerRequest assetBackRecordManagerRequest = new AssetBackRecordManagerRequest();
        assetBackRecordManagerRequest.setAmount(amount);
        assetBackRecordManagerRequest.setAssetId(assetId);
        assetBackRecordManagerRequest.setAssetType(assetType);
        assetBackRecordManagerRequest.setBackRecoedId(backRecoedId);
        assetBackRecordManagerRequest.setExtInfo(extInfo);
        assetBackRecordManagerRequest.setLoanRecoedId(loanRecoedId);
        assetBackRecordManagerRequest.setRemark(remark);
        assetBackRecordManagerRequest.setType(type);
        assetBackRecordManagerRequest.setUserId(userId);

        return assetBackRecordManagerRequest;
    }
}

/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.builder;

import us.betahouse.haetae.asset.model.basic.AssetBackRecordBO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordBOBulider.java 2019/02/10 22:38 yiyuk.hxy
 */
public class AssetBackRecordBOBulider {
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

    private AssetBackRecordBOBulider() {
    }

    public static AssetBackRecordBOBulider getInstance() {
        return new AssetBackRecordBOBulider();
    }

    public AssetBackRecordBOBulider withBackRecoedId(String val) {
        backRecoedId = val;
        return this;
    }

    public AssetBackRecordBOBulider withAssetId(String val) {
        assetId = val;
        return this;
    }

    public AssetBackRecordBOBulider withAssetType(String val) {
        assetType = val;
        return this;
    }

    public AssetBackRecordBOBulider withLoanRecoedId(String val) {
        loanRecoedId = val;
        return this;
    }

    public AssetBackRecordBOBulider withUserId(String val) {
        userId = val;
        return this;
    }

    public AssetBackRecordBOBulider withAmount(Integer val) {
        amount = val;
        return this;
    }

    public AssetBackRecordBOBulider withType(String val) {
        type = val;
        return this;
    }

    public AssetBackRecordBOBulider withRemark(String val) {
        remark = val;
        return this;
    }

    public AssetBackRecordBOBulider withExtInfo(Map<String, String> val) {
        extInfo = val;
        return this;
    }

    public AssetBackRecordBO build() {
        AssetBackRecordBO assetBackRecordBO = new AssetBackRecordBO();
        assetBackRecordBO.setAmount(amount);
        assetBackRecordBO.setAssetId(assetId);
        assetBackRecordBO.setAssetType(assetType);
        assetBackRecordBO.setBackRecoedId(backRecoedId);
        assetBackRecordBO.setExtInfo(extInfo);
        assetBackRecordBO.setLoanRecoedId(loanRecoedId);
        assetBackRecordBO.setRemark(remark);
        assetBackRecordBO.setType(type);
        assetBackRecordBO.setUserId(userId);

        return assetBackRecordBO;
    }
}

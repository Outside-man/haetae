/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.builder;

import us.betahouse.haetae.asset.model.basic.AssetLoanRecordBO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordBOBulider.java 2019/01/25 10:50 yiyuk.hxy
 */
public class AssetLoanRecordBOBulider {
    private String loanRecoedId;
    private String assetId;
    private String assetType;
    private Date loanTime;
    private Date backTime;
    private String userId;
    private int amount;
    private int remain;
    private String status;
    private String remark;
    private Map<String, String> extInfo = new HashMap<>();
    private String assetInfo;

    private AssetLoanRecordBOBulider() {
    }

    public static AssetLoanRecordBOBulider getInstance() {
        return new AssetLoanRecordBOBulider();
    }

    public AssetLoanRecordBOBulider withLoanRecoedId(String val) {
        loanRecoedId = val;
        return this;
    }

    public AssetLoanRecordBOBulider withAssetId(String val) {
        assetId = val;
        return this;
    }

    public AssetLoanRecordBOBulider withAssetType(String val) {
        assetType = val;
        return this;
    }

    public AssetLoanRecordBOBulider withLoanTime(Date val) {
        loanTime = val;
        return this;
    }

    public AssetLoanRecordBOBulider withBackTime(Date val) {
        backTime = val;
        return this;
    }

    public AssetLoanRecordBOBulider withUserId(String val) {
        userId = val;
        return this;
    }

    public AssetLoanRecordBOBulider withAmount(int val) {
        amount = val;
        return this;
    }

    public AssetLoanRecordBOBulider withRemain(int val) {
        remain = val;
        return this;
    }

    public AssetLoanRecordBOBulider withStatus(String val) {
        status = val;
        return this;
    }

    public AssetLoanRecordBOBulider withRemark(String val) {
        remark = val;
        return this;
    }

    public AssetLoanRecordBOBulider withExtInfo(Map<String, String> val) {
        extInfo = val;
        return this;
    }

    public AssetLoanRecordBOBulider withAssetInfo(String val) {
        assetInfo = val;
        return this;
    }

    public AssetLoanRecordBO build() {
        AssetLoanRecordBO assetLoanRecordBO = new AssetLoanRecordBO();
        assetLoanRecordBO.setLoanRecordId(loanRecoedId);
        assetLoanRecordBO.setAssetId(assetId);
        assetLoanRecordBO.setAssetType(assetType);
        assetLoanRecordBO.setUserId(userId);
        assetLoanRecordBO.setAmount(amount);
        assetLoanRecordBO.setRemain(remain);
        assetLoanRecordBO.setRemark(remark);
        assetLoanRecordBO.setStatus(status);
        assetLoanRecordBO.setExtInfo(extInfo);
        assetLoanRecordBO.setAssetInfo(assetInfo);
        return assetLoanRecordBO;
    }

}

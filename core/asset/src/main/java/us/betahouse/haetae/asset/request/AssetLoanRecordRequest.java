/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.request;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordRequest.java 2019/01/23 23:33 yiyuk.hxy
 */
public class AssetLoanRecordRequest extends BaseRequest {
    private static final long serialVersionUID = 7597108455802717599L;
    private String loanRecordId;
    private String assetId;
    private String userId;
    private String assetType;
    private String status;
    private Integer amount;
    private Integer remain;
    private Integer distory;
    private String remark;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
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

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
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

    public String getAssetInfo() {
        return assetInfo;
    }

    public void setAssetInfo(String assetInfo) {
        this.assetInfo = assetInfo;
    }
}

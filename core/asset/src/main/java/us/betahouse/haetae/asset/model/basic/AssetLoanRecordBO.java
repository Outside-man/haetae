/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.model.basic;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 物资借取记录模型
 *
 * @author yiyuk.hxy
 * @version : AssetLoanRecordBO.java 2019/01/23 21:05 yiyuk.hxy
 */
public class AssetLoanRecordBO {
    private static final long serialVersionUID = 1910747655670036477L;
    private String loanRecordId;
    private String assetId;
    private String assetType;
    private String userId;
    private String status;
    private Integer amount;
    /**
     * 已归还数量
     */
    private Integer remain;
    private String remark;
    /**
     * 拓展信息
     */
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

    /**
     * 是否全部归还
     */
    public boolean allBack() {
        if (amount - remain == 0) {
            return true;
        }
        return false;
    }

    /**
     * 创建时间
     */
    private Date createTime;

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

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

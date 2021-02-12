/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.model.basic;

import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 物资借取记录模型
 *
 * @author yiyuk.hxy
 * @version : AssetLoanRecordBO.java 2019/01/23 21:05 yiyuk.hxy
 */
public class AssetLoanRecordBO extends ToString {
    private static final long serialVersionUID = 2503835687145194184L;
    /**
     * 物资借用ID
     */
    private String loanRecordId;
    /**
     * 物资ID
     */
    private String assetId;
    /**
     * 物资名字
     */
    private String assetName;
    /**
     * 物资类型
     */
    private String assetType;
    /**
     * 用户ID
     */
    private String userId;
    private String userRealName;
    /**
     * 用户学号
     */
    private String stuId;
    /**
     * 物资归还状态
     */
    private String status;
    /**
     * 物资借用数量
     */
    private Integer amount;
    /**
     * 已归还数量
     */
    private Integer remain;
    /**
     * 物资报损备注
     */
    private String remark;
    /**
     * 损坏个数
     */
    private Integer distory;
    /**
     * 物资用途
     */
    private String assetInfo;
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
    private String createTime;

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

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
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

    public Integer getDistory() {
        return distory;
    }

    public void setDistory(Integer distory) {
        this.distory = distory;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public String getAssetInfo() {
        return assetInfo;
    }

    public void setAssetInfo(String assetInfo) {
        this.assetInfo = assetInfo;
    }
}

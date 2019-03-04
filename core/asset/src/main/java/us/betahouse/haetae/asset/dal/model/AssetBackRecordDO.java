/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 物资归还记录实体类
 *
 * @author yiyuk, hxy
 * @version : AssetBackRecordDO.java 2019/01/20 16:40 yiyuk,hxy
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "asset_back_record",
        indexes = {
                @Index(name = "uk_asset_back_id", columnList = "back_record_id", unique = true)
        })
public class AssetBackRecordDO extends BaseDO {

    private static final long serialVersionUID = 7714888938135003677L;
    /**
     * 物资归还记录ID
     */
    @Column(name = "back_record_id", length = 32, updatable = false)
    private String backRecoedId;
    /**
     * 物资ID
     */
    @Column(name = "asset_id", length = 32)
    private String assetId;
    /**
     * 物资种类
     */
    @Column(name = "asset_type", length = 32)
    private String assetType;
    /**
     * 物资借取记录ID
     */
    @Column(name = "loan_record_id", length = 32, updatable = false)
    private String loanRecordId;
    /**
     * 借取人ID
     */
    @Column(name = "user_id", length = 32)
    private String userId;
    /**
     * 物资归还数量
     */
    @Column(name = "amount", length = 10)
    private Integer amount;
    /**
     * 归还/报损
     */
    @Column(name = "type", length = 32)
    private String type;
    /**
     * 备注，用于报损时
     */
    @Column(name = "remark", length = 100)
    private String remark;

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

    public String getUserId() {
        return userId;
    }

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
}

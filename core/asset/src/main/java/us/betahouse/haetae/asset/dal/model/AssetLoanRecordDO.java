/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 物资借取记录实体类
 * @author yiyuk,hxy
 * @version : AssetLoanRecordDO.java 2019/01/19 22:03 yiyuk,hxy
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "asset_loan_record",
        indexes = {
                @Index(name = "uk_asset_loan_id", columnList = "loan_record_id", unique = true)
        })
public class AssetLoanRecordDO extends BaseDO {
    private static final long serialVersionUID = -6722889286261263968L;
    /**
     * 物资借取记录ID
     */
    @Column(name = "loan_record_id", length = 32, updatable = false)
    private String loanRecoedId;
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
     * 借取时间
     */
    @Column(name = "loan_time", length = 32)
    private String loanTime;
    /**
     * 最后一次归还时间
     */
    @Column(name = "back_time", length = 32)
    private String backTime;
    /**
     * 借取人ID
     */
    @Column(name = "user_id", length = 32)
    private String userId;
    /**
     * 是否全部归还/报损
     */
    @Column(name = "status", length = 32)
    private String status;
    /**
     * 物资数量
     */
    @Column(name = "amount", length = 10)
    private Integer amount;
    /**
     * 备注，用于报损时
     */
    @Column(name = "remark",length = 10)
    private Integer remark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLoanRecoedId() {
        return loanRecoedId;
    }

    public void setLoanRecoedId(String loanRecoedId) {
        this.loanRecoedId = loanRecoedId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
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

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRemark() {
        return remark;
    }

    public void setRemark(Integer remark) {
        this.remark = remark;
    }
}

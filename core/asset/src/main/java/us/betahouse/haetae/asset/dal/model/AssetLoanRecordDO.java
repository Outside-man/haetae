/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 物资借取实体类
 * @author 10584004
 * @version : AssetLoanRecordDO.java 2019/01/19 22:03 10584004
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
     * 物资借取ID
     */
    @Column(name = "loan_recordid", length = 32, updatable = false)
    private String loanRecoedId;
    /**
     * 物资ID
     */
    @Column(name = "asset_id", length = 32)
    private String assetId;
    /**
     * 物资名称
     */
    @Column(name = "asset_name", length = 32)
    private String assetName;
    /**
     * ?
     */
    @Column(name = "status", length = 32)
    private String status;
    /**
     * 组织id
     */
    @Column(name = "orginazation_id", length = 32)
    private String orginazationId;
    /**
     * 物资状态
     */
    @Column(name = "type", length = 32)
    private String type;
    /**
     * 物资数量
     */
    @Column(name = "amount", length = 10)
    private Integer amount;
    /**
     * 剩余
     */
    @Column(name = "remain",length = 10)
    private Integer remain;

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

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrginazationId() {
        return orginazationId;
    }

    public void setOrginazationId(String orginazationId) {
        this.orginazationId = orginazationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}

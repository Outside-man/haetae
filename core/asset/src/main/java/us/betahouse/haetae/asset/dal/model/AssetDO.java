/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 物资基础类
 *
 * @author yiyu.hxy
 * @version : AssetDO.java 2019/01/19 17:53 yiyu.hxy
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "asset",
        indexes = {
                @Index(name = "uk_asset_id", columnList = "asset_id", unique = true)
        })
public class AssetDO extends BaseDO {
    private static final long serialVersionUID = -6722889286261263968L;
    /**
     * 物资ID
     */
    @Column(name = "asset_id", length = 32, updatable = false)
    private String assetId;
    /**
     * 物资名称
     */
    @Column(name = "asset_name", length = 32)
    private String assetName;
    /**
     * 是否可借用 两种：可借   不可借
     */
    @Column(name = "status", length = 32)
    private String status;
    /**
     * 组织id
     */
    @Column(name = "orginazation_id", length = 32)
    private String orginazationId;
    /**
     * 物资种类
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
    @Column(name = "remain", length = 10)
    private Integer remain;
    /**
     *物资损坏个数
     */
    @Column(name = "destroy" ,length = 10)
    private Integer destroy;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getDestroy() {
        return destroy;
    }

    public void setDestroy(Integer destroy) {
        this.destroy = destroy;
    }
}

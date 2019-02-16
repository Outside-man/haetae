/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.model.basic;

import us.betahouse.util.common.ToString;

import java.util.Date;

/**
 * 物资记录联合实体类
 *
 * @author guofan.cp
 * @version : AssetRecordBO.java 2019/02/13 22:42 guofan.cp
 */
public class AssetRecordBO extends ToString {

    private static final long serialVersionUID = 7493801250977598318L;

    //共同属性
    /**
     * 物资ID
     */
    private String assetId;

    /**
     * 物资种类
     */
    private String assetType;

    /**
     * 物资借用ID
     */
    private String loanRecordId;

    /**
     * 借取人ID
     */
    private String userId;

    //物资借用属性
    /**
     * 借取时间
     */
    private Date loanTime;
    /**
     * 最后一次归还时间
     */
    private Date backTime;
    /**
     * 是否全部归还/报损
     */
    private String status;
    /**
     * 借用数量
     */
    private Integer loanamount;
    /**
     * 可借数量
     */
    private Integer remain;
    /**
     * 报损数量
     */
    private Integer distory;
    /**
     * 备注，用于描述报损情况
     */
    private String remark;
    /**
     * 物资用途
     */
    private String assetInfo;


    //物资归还属性
    /**
     * 物资归还记录ID
     */
    private String backRecoedId;
    /**
     * 物资归还数量
     */
    private Integer backAmount;
    /**
     * 归还/报损
     */
    private String backType;
    /**
     * 备注，用于报损时
     */
    private String backRemark;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLoanamount() {
        return loanamount;
    }

    public void setLoanamount(Integer loanamount) {
        this.loanamount = loanamount;
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

    public String getBackRecoedId() {
        return backRecoedId;
    }

    public void setBackRecoedId(String backRecoedId) {
        this.backRecoedId = backRecoedId;
    }

    public Integer getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(Integer backAmount) {
        this.backAmount = backAmount;
    }

    public String getBackType() {
        return backType;
    }

    public void setBackType(String backType) {
        this.backType = backType;
    }

    public String getBackRemark() {
        return backRemark;
    }

    public void setBackRemark(String backRemark) {
        this.backRemark = backRemark;
    }
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.model.basic;

import us.betahouse.util.common.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : FinanceMessageBO.java 2019/01/31 2:42 MessiahJK
 */
public class FinanceMessageBO extends ToString {

    private static final long serialVersionUID = 192982016691118523L;
    /**
     * 财务信息id
     */
    private String financeMessageId;

    /**
     * 财务名
     */
    private String financeName;

    /**
     * 财务信息
     */
    private String financeInfo;

    /**
     * 类型
     */
    private String type;

    /**
     * 预算
     */
    private String budget;

    /**
     * 真实金额
     */
    private String trueMoney;

    /**
     * 状态
     */
    private String status;

    /**
     * 学期
     */
    private String term;

    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 组织名
     */
    private String organizationName;

    /**
     * 申请人userId
     */
    private String applicantUserId;

    /**
     * 申请人姓名
     */
    private String applicantName;

    /**
     * 审核人userId
     */
    private String auditorUserId;

    /**
     * 审核人姓名
     */
    private String auditorName;

    /**
     * 结束时间
     */
    private Date finishTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 拓展信息
     */
    private Map<String,String> exInfo;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;


    public String fecthExtInfo(String key) {
        if (exInfo == null) {
            return null;
        }
        return  exInfo.get(key);
    }

    public void putExtInfo(String key, String value) {
        if (exInfo == null) {
            exInfo = new HashMap<>(16);
        }
        exInfo.put(key, value);
    }

    public String getFinanceMessageId() {
        return financeMessageId;
    }

    public void setFinanceMessageId(String financeMessageId) {
        this.financeMessageId = financeMessageId;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public String getFinanceInfo() {
        return financeInfo;
    }

    public void setFinanceInfo(String financeInfo) {
        this.financeInfo = financeInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getTrueMoney() {
        return trueMoney;
    }

    public void setTrueMoney(String trueMoney) {
        this.trueMoney = trueMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getApplicantUserId() {
        return applicantUserId;
    }

    public void setApplicantUserId(String applicantUserId) {
        this.applicantUserId = applicantUserId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getAuditorUserId() {
        return auditorUserId;
    }

    public void setAuditorUserId(String auditorUserId) {
        this.auditorUserId = auditorUserId;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, String> getExInfo() {
        return exInfo;
    }

    public void setExInfo(Map<String, String> exInfo) {
        this.exInfo = exInfo;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author MessiahJK
 * @version : FinanceMessageDO.java 2019/01/30 18:56 MessiahJK
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "finance_message",
        indexes = {
                @Index(name = "uk_finance_message_id", columnList = "finance_message_id", unique = true)
        })
public class FinanceMessageDO extends BaseDO {

    /**
     * 财务信息id
     */
    @Column(name = "finance_message_id",updatable = false,length = 32)
    private String financeMessageId;

    /**
     * 财务名
     */
    @Column(name = "finance_name")
    private String financeName;

    /**
     * 财务信息
     */
    @Column(name = "finance_info")
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
    @Column(name = "true_money")
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
    @Column(name = "organization_id")
    private String organizationId;

    /**
     * 组织名
     */
    @Column(name = "organization_name")
    private String organizationName;

    /**
     * 申请人userId
     */
    @Column(name = "applicant_user_id")
    private String applicantUserId;

    /**
     * 申请人姓名
     */
    @Column(name = "applicant_name")
    private String applicantName;

    /**
     * 审核人userId
     */
    @Column(name = "auditor_user_id")
    private String auditorUserId;

    /**
     * 审核人姓名
     */
    @Column(name="auditor_name")
    private String auditorName;

    /**
     * 结束时间
     */
    @Column(name = "finish_time")
    private Date finishTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String exInfo;

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

    public String getExinfo() {
        return exInfo;
    }

    public void setExinfo(String exInfo) {
        this.exInfo = exInfo;
    }
}

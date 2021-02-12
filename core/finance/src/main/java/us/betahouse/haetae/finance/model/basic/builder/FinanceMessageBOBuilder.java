/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.model.basic.builder;

import us.betahouse.haetae.finance.model.basic.FinanceMessageBO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : FinanceMessageBOBuilder.java 2019/02/22 1:40 MessiahJK
 */
public final class FinanceMessageBOBuilder {
    private Long id;
    private String financeMessageId;
    private String financeName;
    private String financeInfo;
    private String type;
    private BigDecimal budget;
    private BigDecimal trueMoney;
    private String status;
    private String term;
    private String organizationId;
    private String organizationName;
    private String applicantUserId;
    private String applicantName;
    private String auditorUserId;
    private String auditorName;
    private Date finishTime;
    private String remark;
    private Map<String,String> exInfo;
    private Date gmtCreate;
    private Date gmtModified;

    private FinanceMessageBOBuilder() {
    }

    public static FinanceMessageBOBuilder aFinanceMessageBO() {
        return new FinanceMessageBOBuilder();
    }

    public FinanceMessageBOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FinanceMessageBOBuilder withFinanceMessageId(String financeMessageId) {
        this.financeMessageId = financeMessageId;
        return this;
    }

    public FinanceMessageBOBuilder withFinanceName(String financeName) {
        this.financeName = financeName;
        return this;
    }

    public FinanceMessageBOBuilder withFinanceInfo(String financeInfo) {
        this.financeInfo = financeInfo;
        return this;
    }

    public FinanceMessageBOBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public FinanceMessageBOBuilder withBudget(BigDecimal budget) {
        this.budget = budget;
        return this;
    }

    public FinanceMessageBOBuilder withTrueMoney(BigDecimal trueMoney) {
        this.trueMoney = trueMoney;
        return this;
    }

    public FinanceMessageBOBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public FinanceMessageBOBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public FinanceMessageBOBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public FinanceMessageBOBuilder withOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public FinanceMessageBOBuilder withApplicantUserId(String applicantUserId) {
        this.applicantUserId = applicantUserId;
        return this;
    }

    public FinanceMessageBOBuilder withApplicantName(String applicantName) {
        this.applicantName = applicantName;
        return this;
    }

    public FinanceMessageBOBuilder withAuditorUserId(String auditorUserId) {
        this.auditorUserId = auditorUserId;
        return this;
    }

    public FinanceMessageBOBuilder withAuditorName(String auditorName) {
        this.auditorName = auditorName;
        return this;
    }

    public FinanceMessageBOBuilder withFinishTime(Date finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public FinanceMessageBOBuilder withRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public FinanceMessageBOBuilder withExInfo(Map<String, String> exInfo) {
        this.exInfo = exInfo;
        return this;
    }

    public FinanceMessageBOBuilder withGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public FinanceMessageBOBuilder withGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    public FinanceMessageBO build() {
        FinanceMessageBO financeMessageBO = new FinanceMessageBO();
        financeMessageBO.setId(id);
        financeMessageBO.setFinanceMessageId(financeMessageId);
        financeMessageBO.setFinanceName(financeName);
        financeMessageBO.setFinanceInfo(financeInfo);
        financeMessageBO.setType(type);
        financeMessageBO.setBudget(budget);
        financeMessageBO.setTrueMoney(trueMoney);
        financeMessageBO.setStatus(status);
        financeMessageBO.setTerm(term);
        financeMessageBO.setOrganizationId(organizationId);
        financeMessageBO.setOrganizationName(organizationName);
        financeMessageBO.setApplicantUserId(applicantUserId);
        financeMessageBO.setApplicantName(applicantName);
        financeMessageBO.setAuditorUserId(auditorUserId);
        financeMessageBO.setAuditorName(auditorName);
        financeMessageBO.setFinishTime(finishTime);
        financeMessageBO.setRemark(remark);
        financeMessageBO.setExInfo(exInfo);
        financeMessageBO.setGmtCreate(gmtCreate);
        financeMessageBO.setGmtModified(gmtModified);
        return financeMessageBO;
    }
}

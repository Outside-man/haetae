/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.finance.request.builder;

import us.betahouse.haetae.serviceimpl.finance.request.FinanceManagerRequest;

import java.math.BigDecimal;

/**
 * @author MessiahJK
 * @version : FinanceManagerRequestBuilder.java 2019/02/22 0:56 MessiahJK
 */
public final class FinanceManagerRequestBuilder {
    private String financeMessageId;
    private String financeName;
    private String term;
    private String financeInfo;
    private String type;
    private BigDecimal budget;
    private BigDecimal trueMoney;
    private String status;
    private String organizationId;
    private String remark;
    private Integer limit;
    private Integer page;
    private Boolean audite;
    private String userId;

    private FinanceManagerRequestBuilder() {
    }

    public static FinanceManagerRequestBuilder aFinanceManagerRequest() {
        return new FinanceManagerRequestBuilder();
    }

    public FinanceManagerRequestBuilder withFinanceMessageId(String financeMessageId) {
        this.financeMessageId = financeMessageId;
        return this;
    }

    public FinanceManagerRequestBuilder withFinanceName(String financeName) {
        this.financeName = financeName;
        return this;
    }

    public FinanceManagerRequestBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public FinanceManagerRequestBuilder withFinanceInfo(String financeInfo) {
        this.financeInfo = financeInfo;
        return this;
    }

    public FinanceManagerRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public FinanceManagerRequestBuilder withBudget(BigDecimal budget) {
        this.budget = budget;
        return this;
    }

    public FinanceManagerRequestBuilder withTrueMoney(BigDecimal trueMoney) {
        this.trueMoney = trueMoney;
        return this;
    }

    public FinanceManagerRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public FinanceManagerRequestBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public FinanceManagerRequestBuilder withRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public FinanceManagerRequestBuilder withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public FinanceManagerRequestBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }

    public FinanceManagerRequestBuilder withAudite(Boolean audite) {
        this.audite = audite;
        return this;
    }

    public FinanceManagerRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public FinanceManagerRequest build() {
        FinanceManagerRequest financeManagerRequest = new FinanceManagerRequest();
        financeManagerRequest.setFinanceMessageId(financeMessageId);
        financeManagerRequest.setFinanceName(financeName);
        financeManagerRequest.setTerm(term);
        financeManagerRequest.setFinanceInfo(financeInfo);
        financeManagerRequest.setType(type);
        financeManagerRequest.setBudget(budget);
        financeManagerRequest.setTrueMoney(trueMoney);
        financeManagerRequest.setStatus(status);
        financeManagerRequest.setOrganizationId(organizationId);
        financeManagerRequest.setRemark(remark);
        financeManagerRequest.setLimit(limit);
        financeManagerRequest.setPage(page);
        financeManagerRequest.setAudite(audite);
        financeManagerRequest.setUserId(userId);
        return financeManagerRequest;
    }
}

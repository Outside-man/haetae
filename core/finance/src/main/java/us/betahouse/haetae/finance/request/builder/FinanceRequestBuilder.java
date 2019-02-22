/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.request.builder;

import us.betahouse.haetae.finance.request.FinanceRequest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : FinanceRequestBuilder.java 2019/02/22 0:47 MessiahJK
 */
public final class FinanceRequestBuilder {
    private String financeName;
    private String financeInfo;
    private String type;
    private String requestId;
    private BigDecimal budget;
    private Map<String, String> extInfo = new HashMap<>();
    private BigDecimal trueMoney;
    private String status;
    private String term;
    private String organizationId;
    private Integer page;
    private Integer limit;

    private FinanceRequestBuilder() {
    }

    public static FinanceRequestBuilder aFinanceRequest() {
        return new FinanceRequestBuilder();
    }

    public FinanceRequestBuilder withFinanceName(String financeName) {
        this.financeName = financeName;
        return this;
    }

    public FinanceRequestBuilder withFinanceInfo(String financeInfo) {
        this.financeInfo = financeInfo;
        return this;
    }

    public FinanceRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public FinanceRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public FinanceRequestBuilder withBudget(BigDecimal budget) {
        this.budget = budget;
        return this;
    }

    public FinanceRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public FinanceRequestBuilder withTrueMoney(BigDecimal trueMoney) {
        this.trueMoney = trueMoney;
        return this;
    }

    public FinanceRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public FinanceRequestBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public FinanceRequestBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public FinanceRequestBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }

    public FinanceRequestBuilder withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public FinanceRequest build() {
        FinanceRequest financeRequest = new FinanceRequest();
        financeRequest.setFinanceName(financeName);
        financeRequest.setFinanceInfo(financeInfo);
        financeRequest.setType(type);
        financeRequest.setRequestId(requestId);
        financeRequest.setBudget(budget);
        financeRequest.setExtInfo(extInfo);
        financeRequest.setTrueMoney(trueMoney);
        financeRequest.setStatus(status);
        financeRequest.setTerm(term);
        financeRequest.setOrganizationId(organizationId);
        financeRequest.setPage(page);
        financeRequest.setLimit(limit);
        return financeRequest;
    }
}

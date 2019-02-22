/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.finance.request;

import us.betahouse.haetae.common.RestRequest;

import java.math.BigDecimal;

/**
 * @author MessiahJK
 * @version : FinanceRestRequest.java 2019/02/21 1:31 MessiahJK
 */
public class FinanceRestRequest  extends RestRequest {

    private static final long serialVersionUID = -4850531327378549656L;
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
    private BigDecimal budget;

    /**
     * 真实金额
     */
    private BigDecimal trueMoney;

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
     * 备注
     */
    private String remark;

    /**
     * 每页条数
     */
    private Integer limit;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 是否通过
     */
    private Boolean audite;

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

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getTrueMoney() {
        return trueMoney;
    }

    public void setTrueMoney(BigDecimal trueMoney) {
        this.trueMoney = trueMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Boolean getAudite() {
        return audite;
    }

    public void setAudite(Boolean audite) {
        this.audite = audite;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}

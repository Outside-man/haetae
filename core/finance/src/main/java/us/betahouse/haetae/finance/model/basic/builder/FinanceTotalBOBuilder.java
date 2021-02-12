/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.model.basic.builder;

import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author MessiahJK
 * @version : FinanceTotalBOBuilder.java 2019/02/22 1:41 MessiahJK
 */
public final class FinanceTotalBOBuilder {
    private Long id;
    private String financeTotalId;
    private String organizationId;
    private String organizationName;
    private BigDecimal totalMoney;
    private BigDecimal totalMoneyIncludeBudget;
    private String remark;
    private Date gmtCreate;
    private Date gmtModified;

    private FinanceTotalBOBuilder() {
    }

    public static FinanceTotalBOBuilder aFinanceTotalBO() {
        return new FinanceTotalBOBuilder();
    }

    public FinanceTotalBOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public FinanceTotalBOBuilder withFinanceTotalId(String financeTotalId) {
        this.financeTotalId = financeTotalId;
        return this;
    }

    public FinanceTotalBOBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public FinanceTotalBOBuilder withOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public FinanceTotalBOBuilder withTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
        return this;
    }

    public FinanceTotalBOBuilder withTotalMoneyIncludeBudget(BigDecimal totalMoneyIncludeBudget) {
        this.totalMoneyIncludeBudget = totalMoneyIncludeBudget;
        return this;
    }

    public FinanceTotalBOBuilder withRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public FinanceTotalBOBuilder withGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public FinanceTotalBOBuilder withGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    public FinanceTotalBO build() {
        FinanceTotalBO financeTotalBO = new FinanceTotalBO();
        financeTotalBO.setId(id);
        financeTotalBO.setFinanceTotalId(financeTotalId);
        financeTotalBO.setOrganizationId(organizationId);
        financeTotalBO.setOrganizationName(organizationName);
        financeTotalBO.setTotalMoney(totalMoney);
        financeTotalBO.setTotalMoneyIncludeBudget(totalMoneyIncludeBudget);
        financeTotalBO.setRemark(remark);
        financeTotalBO.setGmtCreate(gmtCreate);
        financeTotalBO.setGmtModified(gmtModified);
        return financeTotalBO;
    }
}

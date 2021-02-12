/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.finance.vo.builder;

import us.betahouse.haetae.model.finance.vo.FinanceTotalVO;

import java.math.BigDecimal;

/**
 * @author MessiahJK
 * @version : FinanceTotalVOBuilder.java 2019/02/22 2:08 MessiahJK
 */
public final class FinanceTotalVOBuilder {
    private BigDecimal totalMoney;
    private BigDecimal totalMoneyIncludeBudget;

    private FinanceTotalVOBuilder() {
    }

    public static FinanceTotalVOBuilder aFinanceTotalVO() {
        return new FinanceTotalVOBuilder();
    }

    public FinanceTotalVOBuilder withTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
        return this;
    }

    public FinanceTotalVOBuilder withTotalMoneyIncludeBudget(BigDecimal totalMoneyIncludeBudget) {
        this.totalMoneyIncludeBudget = totalMoneyIncludeBudget;
        return this;
    }

    public FinanceTotalVO build() {
        FinanceTotalVO financeTotalVO = new FinanceTotalVO();
        financeTotalVO.setTotalMoney(totalMoney);
        financeTotalVO.setTotalMoneyIncludeBudget(totalMoneyIncludeBudget);
        return financeTotalVO;
    }
}

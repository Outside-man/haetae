/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.finance.vo;

import us.betahouse.util.common.ToString;

import java.math.BigDecimal;

/**
 * @author MessiahJK
 * @version : FinanceTotalVO.java 2019/02/21 19:35 MessiahJK
 */
public class FinanceTotalVO extends ToString {

    private static final long serialVersionUID = -3278562040026925952L;
    /**
     * 金额总计
     */
    private BigDecimal totalMoney;

    /**
     * 金额总计（包含预算）
     */
    private BigDecimal totalMoneyIncludeBudget;

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalMoneyIncludeBudget() {
        return totalMoneyIncludeBudget;
    }

    public void setTotalMoneyIncludeBudget(BigDecimal totalMoneyIncludeBudget) {
        this.totalMoneyIncludeBudget = totalMoneyIncludeBudget;
    }
}

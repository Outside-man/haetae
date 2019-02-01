/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author MessiahJK
 * @version : FinanceTotalDO.java 2019/01/29 15:46 MessiahJK
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "finance_total",
        indexes = {
                @Index(name = "uk_finance_total_id", columnList = "finance_total_id", unique = true)
        })
public class FinanceTotalDO extends BaseDO{

    /**
     * 财务统计id
     */
    @Column(name = "finance_total_id",length = 32,updatable = false)
    private String financeTotalId;

    /**
     * 组织id
     */
    @Column(name="organization_id",length = 32)
    private String organizationId;

    /**
     * 组织名
     */
    @Column(name = "organization_name")
    private String organizationName;

    /**
     * 金额总计
     */
    private BigDecimal totalMoney;

    /**
     * 金额总计（包含预算）
     */
    private BigDecimal totalMoneyIncludeBudget;

    /**
     * 备注
     */
    private String remark;

    public String getFinanceTotalId() {
        return financeTotalId;
    }

    public void setFinanceTotalId(String financeTotalId) {
        this.financeTotalId = financeTotalId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

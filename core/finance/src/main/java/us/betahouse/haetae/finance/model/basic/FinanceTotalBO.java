/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.model.basic;

import us.betahouse.util.common.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author MessiahJK
 * @version : FinanceTotalBO.java 2019/01/31 2:31 MessiahJK
 */
public class FinanceTotalBO extends ToString {

    private static final long serialVersionUID = 549634770587637634L;
    /**
     * id
     */
    private Long id;

    /**
     * 财务统计id
     */
    private String financeTotalId;

    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 组织名
     */
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

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

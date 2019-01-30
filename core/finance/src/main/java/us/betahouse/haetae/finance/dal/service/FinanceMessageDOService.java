/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.service;

import us.betahouse.haetae.finance.model.basic.FinanceMessageBO;
import us.betahouse.haetae.finance.model.common.PageList;

/**
 * @author MessiahJK
 * @version : FinanceMessageDOService.java 2019/01/31 2:51 MessiahJK
 */
public interface FinanceMessageDOService {

    /**
     * 创建
     *
     * @param financeMessageBO
     * @return
     */
    FinanceMessageBO create(FinanceMessageBO financeMessageBO);

    /**
     * 更新
     *
     * @param financeMessageBO
     * @return
     */
    FinanceMessageBO update(FinanceMessageBO financeMessageBO);

    /**
     * 通过组织id、学期、状态查询
     *
     * @param organizationId 组织id
     * @param term 学期
     * @param  status 状态
     * @return
     */
    PageList<FinanceMessageBO> findByOrganizationIdAndtermAndStatus(String organizationId,String term,String status);


}

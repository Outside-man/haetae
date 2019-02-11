/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.service;

import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;

import java.util.List;

/**
 * @author MessiahJK
 * @version : FinanceTotalDOService.java 2019/01/31 2:34 MessiahJK
 */
public interface FinanceTotalDOService {

    /**
     * 创建财务统计实体
     *
     * @param financeTotalBO
     * @return
     */
    FinanceTotalBO create(FinanceTotalBO financeTotalBO);

    /**
     * 通过财务id查找财务统计实体
     *
     * @param financeTotalId
     * @return
     */
    FinanceTotalBO findByFinanceTotalId(String financeTotalId);

    /**
     * 更新财务统计实体
     *
     * @param financeTotalBO
     * @return
     */
    FinanceTotalBO update(FinanceTotalBO financeTotalBO);

    /**
     * 查找所有财务统计实体
     *
     * @return
     */
    List<FinanceTotalBO> findAll();

}
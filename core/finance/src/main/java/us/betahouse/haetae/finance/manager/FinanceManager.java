/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.manager;

import us.betahouse.haetae.finance.model.basic.FinanceMessageBO;
import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;
import us.betahouse.haetae.finance.model.common.PageList;
import us.betahouse.haetae.finance.request.FinanceRequest;

import java.util.List;

/**
 * @author MessiahJK
 * @version : FinanceManager.java 2019/02/22 0:39 MessiahJK
 */

public interface FinanceManager {

    /**
     * 查询信息 分页
     *
     * @param request
     * @return
     */
    PageList<FinanceMessageBO> findMessage(FinanceRequest request);

    /**
     * 查询我的提交信息
     *
     * @param request
     * @return
     */
    List<FinanceMessageBO> findMyMessage(FinanceRequest request);

    /**
     * 通过财务信息id查询财务信息实体
     *
     * @param financeMessageId
     * @return
     */
    FinanceMessageBO findMessageByFinanceMessageId(String financeMessageId);

    /**
     * 改变状态
     *
     * @param financeMessageId
     * @param status
     * @return
     */
    FinanceMessageBO changeStatus(String financeMessageId,String status);

    /**
     * 核算
     *
     * @param financeMessageBO
     * @return
     */
    FinanceMessageBO check(FinanceMessageBO financeMessageBO);
    /**
     * 获取总金额
     *
     * @param request
     * @return
     */
    FinanceTotalBO getTotalMoney(FinanceRequest request);


    FinanceMessageBO changeFinanceMessage(FinanceMessageBO financeMessageBO);

    /**
     * 创建财务信息 预算
     *
     * @param financeMessageBO
     * @return
     */
    FinanceMessageBO createFinanceMessageByBudget(FinanceMessageBO financeMessageBO);


    /**
     * 创建财务信息 记账
     *
     * @param financeMessageBO
     * @return
     */
    FinanceMessageBO createFinanceMessageByTally(FinanceMessageBO financeMessageBO);


    /**
     * 初始化财务统计
     *
     * @param financeTotalBO
     * @return
     */
    void initTotalMoney(FinanceTotalBO financeTotalBO);
}

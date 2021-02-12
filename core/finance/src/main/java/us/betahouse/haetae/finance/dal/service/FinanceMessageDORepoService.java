/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.service;

import us.betahouse.haetae.finance.model.basic.FinanceMessageBO;
import us.betahouse.haetae.finance.model.common.PageList;

import java.util.List;

/**
 * @author MessiahJK
 * @version : FinanceMessageDORepoService.java 2019/01/31 2:51 MessiahJK
 */

public interface FinanceMessageDORepoService {

    /**
     * 创建
     *
     * @param financeMessageBO
     * @return
     */
    FinanceMessageBO createFinanceMessage(FinanceMessageBO financeMessageBO);

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
     * @param status 状态
     * @param page 页数
     * @param limit 每页条数
     * @return
     */
    PageList<FinanceMessageBO> findByOrganizationIdAndTermAndStatus(String organizationId,String term,String status,Integer page, Integer limit);

    /**
     * 查询自己提交的财务记录
     *
     * @param organizationId
     * @param term
     * @param applicantUserId
     * @return
     */
    List<FinanceMessageBO> findAllByOrganizationIdAndTermAndApplicantUserId(String organizationId,String term,String applicantUserId);
    /**
     * 通过财务信息id查询
     *
     * @param financeMessageId 财务信息id
     * @return
     */
    FinanceMessageBO findByFinanceMessageId(String financeMessageId);
}

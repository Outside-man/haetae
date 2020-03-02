/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.finance.dal.model.FinanceMessageDO;

import java.util.List;

/**
 * 财务信息查询
 *
 * @author MessiahJK
 * @version : FinanceMessageDORepo.java 2019/01/31 2:23 MessiahJK
 */
public interface FinanceMessageDORepo extends JpaRepository<FinanceMessageDO,Long> {

    /**
     * 通过组织id、学期、状态进行分页 模糊查询
     *
     * @param pageable 分页工具
     * @param organizationId 组织id
     * @param term 学期
     * @param status 状态
     * @return
     */
    Page<FinanceMessageDO> findAllByOrganizationIdContainsAndTermContainsAndStatusContains(Pageable pageable,String organizationId,String term,String status);

    /**
     * 通过组织id、学期、状态进行分页
     *
     * @param pageable 分页工具
     * @param organizationId 组织id
     * @param term 学期
     * @param status 状态
     * @return
     */
    Page<FinanceMessageDO> findAllByOrganizationIdAndTermContainsAndStatus(Pageable pageable,String organizationId,String term,String status);

    /**
     * 通过财务信息id获取财务信息实体
     *
     * @param financeMessageId 财务信息id
     * @return
     */
    FinanceMessageDO findByFinanceMessageId(String financeMessageId);

    /**
     * 查询自己上报的财务信息
     *
     * @param organizationId
     * @param term
     * @param applicantUserId
     * @return
     */
    List<FinanceMessageDO> findAllByOrganizationIdAndTermAndApplicantUserId(String organizationId,String term,String applicantUserId);
}

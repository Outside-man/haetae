/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.finance.dal.model.FinanceTotalDO;

/**
 * @author MessiahJK
 * @version : FinanceTotalDORepo.java 2019/01/30 0:48 MessiahJK
 */
public interface FinanceTotalDORepo  extends JpaRepository<FinanceTotalDO,Long> {

    /**
     * 通过统计id查找财务统计实体
     *
     * @param financeTotalId
     * @return
     */
    FinanceTotalDO findByFinanceTotalId(String financeTotalId);
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.MajorDO;

/**
 * 专业仓储
 *
 * @author dango.yxm
 * @version : MajorDORepo.java 2018/11/16 下午7:14 dango.yxm
 */
@Repository
public interface MajorDORepo extends JpaRepository<MajorDO, Long> {
}

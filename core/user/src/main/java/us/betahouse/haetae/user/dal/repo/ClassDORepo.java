/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.ClassDO;

/**
 * 班级仓储
 *
 * @author dango.yxm
 * @version : ClassDORepo.java 2018/11/16 下午7:06 dango.yxm
 */
@Repository
public interface ClassDORepo extends JpaRepository<ClassDO, Long> {
}

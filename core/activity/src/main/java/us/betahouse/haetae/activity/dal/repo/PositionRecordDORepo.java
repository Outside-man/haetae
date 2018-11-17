/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.PositionRecordDO;

/**
 * 履历实体存储
 *
 * @author MessiahJK
 * @version : PositionRecordDORepo.java 2018/11/17 15:45 MessiahJK
 */
@Repository
public interface PositionRecordDORepo extends JpaRepository<PositionRecordDO,Long> {

}

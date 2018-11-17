/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.ActivityRecordDO;

/**
 *
 * @author MessiahJK
 * @version : ActivityRecordDORepo.java 2018/11/17 15:44 MessiahJK
 */
@Repository
public interface  ActivityRecordDORepo extends JpaRepository<ActivityRecordDO,Long> {
}

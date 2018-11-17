/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.ActivityDO;

import java.util.List;

/**
 * 活动实体储存
 *
 * @author MessiahJK
 * @version : ActivityDORepo.java 2018/11/17 15:39 MessiahJK
 */
@Repository
public interface ActivityDORepo extends JpaRepository<ActivityDO,Long> {
    List<ActivityDO> findByType(String type);
}

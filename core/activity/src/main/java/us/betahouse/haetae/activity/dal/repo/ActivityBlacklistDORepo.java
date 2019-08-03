package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.ActivityBlacklistDO;

import java.util.List;

/**
 * 活动黑名单仓储
 *
 * @author zjb
 * @version : ActivityBlacklistDORepo.java 2019/8/3 14:29 zjb
 */
@Repository
public interface ActivityBlacklistDORepo extends JpaRepository<ActivityBlacklistDO, Long> {

    /**
     * 通过用户id查找黑名单记录数量
     * @param userId
     * @return
     */
    Long countByUserId(String userId);

    /**
     * 通过用户id和学期查找黑名单记录数量
     * @param userId
     * @return
     */
    Long countByUserIdAndTerm(String userId,String term);

    /**
     * 通过用户id查找所有黑名单记录
     *
     * @param userId
     * @return
     */
    List<ActivityBlacklistDO> findAllByUserId(String userId);

    /**
     * 通过用户id和学期查找所有黑名单记录
     *
     * @param userId
     * @return
     */
    List<ActivityBlacklistDO> findAllByUserIdAndTerm(String userId,String term);

}

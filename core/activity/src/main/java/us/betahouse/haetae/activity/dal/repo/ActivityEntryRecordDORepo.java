package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.activity.dal.model.ActivityEntryRecordDO;

import java.util.List;

/**
 * 活动报名记录仓储
 *
 * @author zjb
 * @version : ActivityEntryRecordDORepo.java 2019/7/7 15:19 zjb
 */
@Repository
public interface ActivityEntryRecordDORepo extends JpaRepository<ActivityEntryRecordDO, Long> {

    /**
     * 通过用户id查找报名记录
     *
     * @param userId
     * @return
     */
    List<ActivityEntryRecordDO> findAllByUserId(String userId);

    /**
     * 通过报名信息id查找报名记录
     *
     * @param activityEntryId
     * @return
     */
    List<ActivityEntryRecordDO> findAllByActivityEntryId(String activityEntryId);

    /**
     * 通过报名信息id和用户id获取报名记录
     * @param activityEntryId
     * @param userId
     * @return
     */
    ActivityEntryRecordDO findByActivityEntryIdAndUserId(String activityEntryId, String userId);

    /**
     * 通过对应报名信息id和用户id检测报名记录是否存在
     * @param activityEntryId
     * @param userId
     */
    boolean existsActivityEntryRecordDOByActivityEntryIdAndUserId(String activityEntryId, String userId);

    /**
     * 通过对应报名信息id和报名记录状态查找报名记录数量
     * @param activityEntryId
     * @return
     */
    Long countByActivityEntryIdAndState(String activityEntryId, String state);
}

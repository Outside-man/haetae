package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.ActivityEntryDO;

import java.util.List;

/**
 * 活动报名信息仓储
 *
 * @author zjb
 * @version : ActivityEntryDORepo.java 2019/7/7 15:10 zjb
 */
@Repository
public interface ActivityEntryDORepo extends JpaRepository<ActivityEntryDO, Long> {


    /**
     * 通过活动id获取报名信息
     *
     * @param activityId
     * @return
     */
    ActivityEntryDO findByActivityId(String activityId);

    /**
     * 通过活动信息id获取报名信息
     *
     * @param activityEntryId
     * @return
     */
    ActivityEntryDO findByActivityEntryId(String activityEntryId);

    /**
     * 通过活动id查找报名信息
     *
     * @param activityId
     * @return
     */
    List<ActivityEntryDO> findAllByActivityId(String activityId);

    /**
     * 通过报名状态查找报名信息
     *
     * @param state
     * @return
     */
    List<ActivityEntryDO> findAllByState(String state);

    /**
     * 通过报名状态查找报名信息并按活动id逆序
     *
     * @param state
     * @return
     */
    List<ActivityEntryDO> findAllByStateOrderByActivityIdDesc(String state);

    /**
     * 通过学期、状态、类型分页查询
     *
     * @param pageable 分页工具
     * @param term 学期
     * @param status 状态
     * @param type 类型
     * @return Page<ActivityDO>
     */
    Page<ActivityEntryDO> findAllByTermContainsAndStateContainsAndTypeContainsOrderByStart(Pageable pageable, String term, String status, String type);

}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
public interface ActivityDORepo extends JpaRepository<ActivityDO, Long> {
    /**
     * 通过活动类型查找活动
     *
     * @param type
     * @return
     */
    List<ActivityDO> findAllByType(String type);

    /**
     * 通过活动id获取
     *
     * @param activityId
     * @return
     */
    ActivityDO findByActivityId(String activityId);

    /**
     * 通过活动id 检测活动是否存在
     *
     * @param activityId
     * @return
     */
    boolean existsActivityDOByActivityId(String activityId);

    /**
     * 通过活动状态查找活动
     *
     * @param state
     * @return
     */
    List<ActivityDO> findAllByState(String state);

    /**
     * 通过活动状态查找活动 按活动id逆序
     *
     * @param status
     * @return
     */
    List<ActivityDO> findAllByStateOrderByActivityIdDesc(String status);

    /**
     * 通过活动ids查找活动
     *
     * @param activityIds
     * @return
     */
    List<ActivityDO> findAllByActivityIdIn(List<String> activityIds);

    /**
     * 通过活动名查找活动
     *
     * @param activityName
     * @return
     */
    ActivityDO findByActivityName(String activityName);

    /**
     * 通过学期、状态、类型分页查询 倒序
     *
     * @param pageable 分页工具
     * @param term     学期
     * @param status   状态
     * @param type     类型
     * @return Page<ActivityDO>
     */
    Page<ActivityDO> findAllByTermContainsAndStateContainsAndTypeContainsOrderByActivityIdDesc(Pageable pageable, String term, String status, String type);

    /**
     * 通过学期、状态、类型分页查询
     *
     * @param pageable 分页工具
     * @param term     学期
     * @param status   状态
     * @param type     类型
     * @return Page<ActivityDO>
     */
    Page<ActivityDO> findAllByTermContainsAndStateContainsAndTypeContains(Pageable pageable, String term, String status, String type);

    /**
     * 获取最新的十个活动
     * @return
     */
    @Query(value = "SELECT * from activity ORDER BY id desc LIMIT 10 ",nativeQuery = true)
    List<ActivityDO> findFirst10OrOrderByStart();
}

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
import us.betahouse.haetae.activity.dal.model.ActualStamperDO;

import java.util.Date;
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

    /**
     * 通过用户Id分页查询
     *
     * @param pageable 分页工具
     * @param userId

     * @return Page<ActivityDO>
     */
    Page<ActivityDO> findByUserId(Pageable pageable, String userId);

    /**
     * 已审批通过的活动分页查询
     *
     * @param pageable 分页工具
     * @param state     状态
     * @param stuId 学号
     * @param activityName 活动名
     * @param organizationMessage 组织单位

     * @return Page<ActivityDO>
     */
    @Query(value = "SELECT * from activity where state = ?1 and activity_name like ?3 " +
            "and organization_message like ?4 and user_id in(select user_id from common_user_info where stu_id like ?2 ) "
            ,nativeQuery = true)
    Page<ActivityDO>  findApproved(Pageable pageable, String state,String stuId, String activityName ,String organizationMessage);

    /**
     * 已审批通过的活动（添加时间）分页查询
     *
     * @param pageable 分页工具
     * @param state     状态
     * @param stuId 学号
     * @param activityName 活动名
     * @param organizationMessage 组织单位
     * @param start 活动开始时间
     * @param end 活动结束时间

     * @return Page<ActivityDO>
     */
    //nativeQuery = true:直接查表名，不查对象名
    @Query(value = "SELECT * from activity where state = ?1 and activity_name like ?3 and organization_message like ?4 " +
            "and start >= ?5 and end <= ?6 and user_id in( select user_id from common_user_info where stu_id like ?2 ) ",nativeQuery = true)
    Page<ActivityDO> findApprovedAddTime(Pageable pageable, String state, String stuId, String activityName, String organizationMessage , Date start, Date end);


    /**
     * 本周创建的活动分页查询 倒序
     * @param date
     * @param pageable
     * @return
     */
    Page<ActivityDO> findByGmtCreateGreaterThanEqualAndActivityNameContainsOrderByActivityIdDesc(Date date, Pageable pageable,String activityName);

    /**
     * 本周创建的活动分页查询
     * @param date
     * @param pageable
     * @return
     */
    Page<ActivityDO> findByGmtCreateGreaterThanEqualAndActivityNameContains(Date date, Pageable pageable,String activityName);
    /**
     * 本周审批通过的活动分页查询 倒序
     * @param date
     * @param pageable
     * @return
     */
    Page<ActivityDO> findByGmtModifiedGreaterThanEqualAndActivityNameContainsAndStateContainsOrderByActivityIdDesc(Date date, Pageable pageable,String activityName,String state);

    /**
     * 本周审批通过的活动分页查询
     * @param date
     * @param pageable
     * @return
     */
    Page<ActivityDO> findByGmtModifiedGreaterThanEqualAndActivityNameContainsAndStateContains(Date date, Pageable pageable,String activityName,String state);

    //查询本周完整的活动信息（创建时间为本周 且 状态为finish）


    //查询活动id及实际扫章数（先查询本周创建的活动的id，根据id分组查询活动章）
    @Query(value = "select new us.betahouse.haetae.activity.dal.model.ActualStamperDO(a.activity_id as activityId,count(a.user_id) as stamperNumber) from activity_record as a \n" +
            "where a.activity_id in (select activity_id from activity where date(gmt_create) between '2021-7-1' and current_date)\n" +
            "group by a.activity_id",nativeQuery = true)
    Page<ActualStamperDO> findActualStamper(Pageable pageable);


}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.ActivityDO;

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
    @Query(value = "SELECT * from activity where state != ?1 and state != 'CANCELED' and activity_name like ?3 " +
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
     * @param activityStampedStart 活动扫章开始时间
     * @param activityStampedEnd 活动扫章结束时间
     * @return Page<ActivityDO>
     */
    @Query(value = "SELECT * from activity where state != ?1 and state != 'CANCELED'and activity_name like ?3 and organization_message like ?4 " +
            "and activity_stamped_start >= ?5 and activity_stamped_end <= ?6 and user_id in( select user_id from common_user_info where stu_id like ?2 ) ",nativeQuery = true)
    Page<ActivityDO> findApprovedAddTime(Pageable pageable, String state, String stuId, String activityName, String organizationMessage , Date activityStampedStart, Date activityStampedEnd);

    @Modifying
    @Query(value = "update activity set activity_stamped_start=?,activity_stamped_end=? where activity_id=?",nativeQuery = true)
    int updateActivityStampedTimeByActivityId(Date activityStampedStart,Date activityStampedEnd,String activityId);

    @Query(value = "select * from activity where activity_name like concat('%',?,'%') " +
            "and organization_message like concat('%',?,'%') " +
            "and user_id=(select user_id from common_user_info where stu_id=?) " +
            "and activity_stamped_start>=? and activity_stamped_end<=? " +
            "and activity_id in (select activity_id from activity where state='FINISHED' or state='PUBLISHED' or state='RESTARTED') " +
            "limit ?,?",nativeQuery = true)
    List<ActivityDO> findApprovedActivity(String activityName,String OrganizationName,
                                          String stuId,Date start,Date end,int formIndex,int size);

    @Query(value = "select count(id) from activity where activity_name like concat('%',?,'%') " +
            "and organization_message like concat('%',?,'%') " +
            "and user_id=(select user_id from common_user_info where stu_id=?) " +
            "and activity_stamped_start>=? and activity_stamped_end<=? " +
            "and activity_id in (select activity_id from activity where state='FINISHED' or state='PUBLISHED' or state='RESTARTED')",nativeQuery = true)
    Long findApprovedActivityNum(String activityName,String OrganizationName,String stuId,Date start,Date end);

    @Query(value = "select * from activity where activity_name like concat('%',?,'%') " +
            "and organization_message like concat('%',?,'%') " +
            "and activity_stamped_start>=? and activity_stamped_end<=? " +
            "and activity_id in (select activity_id from activity where state='FINISHED' or state='PUBLISHED' or state='RESTARTED') " +
            "limit ?,?",nativeQuery = true)
    List<ActivityDO> findApprovedActivity(String activityName,String OrganizationName,
                                                      Date start,Date end,int formIndex,int size);

    @Query(value = "select count(id) from activity where activity_name like concat('%',?,'%') " +
            "and organization_message like concat('%',?,'%') " +
            "and activity_stamped_start>=? and activity_stamped_end<=? " +
            "and activity_id in (select activity_id from activity where state='FINISHED' or state='PUBLISHED' or state='RESTARTED')",nativeQuery = true)
    Long findApprovedActivityNum(String activityName,String OrganizationName,Date start,Date end);

    @Query(value = "select * from activity where activity_name like concat('%',?,'%') " +
            "and organization_message like concat('%',?,'%') " +
            "and user_id=(select user_id from common_user_info where stu_id=?) " +
            "and activity_id in (select activity_id from activity where state='FINISHED' or state='PUBLISHED' or state='RESTARTED') " +
            "limit ?,?",nativeQuery = true)
    List<ActivityDO> findApprovedActivity(String activityName,String OrganizationName,
                                          String stuId,int formIndex,int size);

    @Query(value = "select count(id) from activity where activity_name like concat('%',?,'%') " +
            "and organization_message like concat('%',?,'%') " +
            "and user_id=(select user_id from common_user_info where stu_id=?) " +
            "and activity_id in (select activity_id from activity where state='FINISHED' or state='PUBLISHED' or state='RESTARTED')",nativeQuery = true)
    Long findApprovedActivityNum(String activityName,String OrganizationName,String stuId);

    @Query(value = "select * from activity where activity_name like concat('%',?,'%') " +
            "and organization_message like concat('%',?,'%') " +
            "and activity_id in (select activity_id from activity where state='FINISHED' or state='PUBLISHED' or state='RESTARTED') " +
            "limit ?,?",nativeQuery = true)
    List<ActivityDO> findApprovedActivity(String activityName,String OrganizationName,
                                          int formIndex,int size);

    @Query(value = "select count(id) from activity where activity_name like concat('%',?,'%') " +
            "and organization_message like concat('%',?,'%') " +
            "and activity_id in (select activity_id from activity where state='FINISHED' or state='PUBLISHED' or state='RESTARTED')",nativeQuery = true)
    Long findApprovedActivityNum(String activityName,String OrganizationName);
}

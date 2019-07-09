package us.betahouse.haetae.activity.dal.service;

import us.betahouse.haetae.activity.model.basic.ActivityEntryBO;
import us.betahouse.haetae.activity.model.common.PageList;

import java.util.List;

/**
 * 活动报名信息仓储服务
 *
 * @author zjb
 * @version : ActivityEntryRepoService.java 2019/7/7 15:54 zjb
 */
public interface ActivityEntryRepoService {

    /**
     * 通过活动id获取报名信息
     *
     * @param activityId
     * @return
     */
    ActivityEntryBO findByActivityId(String activityId);

    /**
     * 通过活动信息id获取报名信息
     *
     * @param activityEntryId
     * @return
     */
    ActivityEntryBO findByActivityEntryId(String activityEntryId);

    /**
     * 通过报名状态查找报名信息
     *
     * @param state
     * @return
     */
    List<ActivityEntryBO> findAllByState(String state);

    /**
     * 通过报名状态查找报名信息并按活动id逆序
     *
     * @param state
     * @return
     */
    List<ActivityEntryBO> findAllByStateOrderByActivityIdDesc(String state);

    /**
     * 通过学期、状态、类型分页查询 分页
     *
     * @param term 学期
     * @param status 状态
     * @param type 类型
     * @param page 页面
     * @param limit 每页行数
     * @return PageList<ActivityEntryBO>
     */
    PageList<ActivityEntryBO> queryActivityEntryByTermAndStateAndTypePagerASC(String term, String status, String type, Integer page, Integer limit);

}

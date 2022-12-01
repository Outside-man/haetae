package us.betahouse.haetae.activity.dal.service;

import us.betahouse.haetae.activity.model.basic.ActivityBlacklistBO;

import java.util.List;

/**
 * 活动黑名单仓储服务
 *
 * @author zjb
 * @version : ActivityBlacklistRepoService.java 2019/8/3 14:58 zjb
 */
public interface ActivityBlacklistRepoService {
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
    List<ActivityBlacklistBO> findAllByUserId(String userId);

    /**
     * 通过用户id和学期查找所有黑名单记录
     *
     * @param userId
     * @return
     */
    List<ActivityBlacklistBO> findAllByUserIdAndTerm(String userId, String term);

    /**
     * 新增黑名单记录
     *
     * @param activityBlacklistBO
     * @return
     */
    ActivityBlacklistBO createActivityBlacklist(ActivityBlacklistBO activityBlacklistBO);

    /**
     * 按活动id扫描缺席人员，加入黑名单
     *
     * @param activityId
     * @return
     */
    List<ActivityBlacklistBO> addBlacklistByActivityId(String activityId);

}

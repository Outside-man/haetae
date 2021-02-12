package us.betahouse.haetae.serviceimpl.activity.service;

import us.betahouse.haetae.activity.model.basic.ActivityBlacklistBO;

import java.util.List;

/**
 * 活动黑名单服务
 *
 * @author zjb
 * @version : ActivityBlacklistService.java 2019/8/3 15:22 zjb
 */
public interface ActivityBlacklistService {


    /**
     * 通过用户id查找黑名单记录数量
     * @param userId
     * @return
     */
    Long countByUserId(String userId);

    /**
     * 通过用户id和学期获取信誉分
     * @param userId
     * @return
     */
    Long getCreditScoreByUserIdAndTerm(String userId,String term);

    /**
     * 通过用户id查找所有黑名单记录
     *
     * @param userId
     * @return
     */
    List<ActivityBlacklistBO> queryAllByUserId(String userId);

    /**
     * 通过用户id和学期查找所有黑名单记录
     *
     * @param userId
     * @return
     */
    List<ActivityBlacklistBO> queryAllByUserIdAndTerm(String userId, String term);
}

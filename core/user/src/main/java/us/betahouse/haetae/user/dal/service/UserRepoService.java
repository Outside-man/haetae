/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.model.basic.perm.UserBO;

/**
 * 用户仓储服务
 *
 * @author dango.yxm
 * @version : UserRepoService.java 2018/11/16 下午7:39 dango.yxm
 */
public interface UserRepoService {

    /**
     * 检查用户是否存在
     *
     * @param userId
     * @return
     */
    boolean checkUserExistByUserId(String userId);

    /**
     * 通过用户名获取用户
     *
     * @param userName
     * @return
     */
    UserBO queryByUserName(String userName);

    /**
     * 通过用户id获取用户
     *
     * @param userId
     * @return
     */
    UserBO queryByUserId(String userId);

    /**
     * 创建新用户
     *
     * @param userBO
     * @return
     */
    UserBO createUser(UserBO userBO);

    /**
     * 更新用户信息
     *
     * @param userBO
     * @return
     */
    UserBO updateUserByUserId(UserBO userBO);

    /**
     * 通过唯一openId 查询用户信息
     *
     * @param openId
     * @return
     */
    UserBO queryByOpenId(String openId);

    /**
     * 清除 登陆信息
     *
     * @param userId
     * @return
     */
    UserBO clearOpenIdAndSessionId(String userId);

    /**
     * 清除 登陆信息
     *
     * @param userId
     * @return
     */
    UserBO clearSessionId(String userId);

    /**
     * 通过sessionId 获取用户信息
     *
     * @param sessionId
     * @return
     */
    UserBO queryBySessionId(String sessionId);
}

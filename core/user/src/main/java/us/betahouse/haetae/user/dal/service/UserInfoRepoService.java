/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.model.basic.UserInfoBO;

import java.util.List;

/**
 * 用户信息仓储服务
 *
 * @author dango.yxm
 * @version : UserInfoRepoService.java 2018/11/17 下午8:36 dango.yxm
 */
public interface UserInfoRepoService {


    /**
     * 绑定用户信息
     *
     * @param userId 用户id
     * @param userInfoBO
     */
    void bindUserInfo(String userId, UserInfoBO userInfoBO);

    /**
     * 通过用户id查询用户信息
     *
     * @param userId
     * @return
     */
    UserInfoBO queryUserInfoByUserId(String userId);

    /**
     * 通过学号查询用户信息
     *
     * @param stuId
     * @return
     */
    UserInfoBO queryUserInfoByStuId(String stuId);

    /**
     * 修改用户信息
     *
     * @param userId
     * @param userInfoBO
     * @return
     */
    UserInfoBO modifyUserInfoByUserId(String userId, UserInfoBO userInfoBO);

    /**
     * 批量获取用户信息
     *
     * @param userIds
     * @return
     */
    List<UserInfoBO> batchQueryByUserIds(List<String> userIds);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<UserInfoBO> queryAllUser();


    /**
     * 获取所有年级和专业
     *
     * @return
     */
    List<UserInfoBO> queryAllMajorAndGrade();

}

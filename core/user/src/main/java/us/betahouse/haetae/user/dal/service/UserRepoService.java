/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.model.perm.UserBO;

/**
 * 用户仓储服务
 * @author dango.yxm
 * @version : UserRepoService.java 2018/11/16 下午7:39 dango.yxm
 */
public interface UserRepoService {

    /**
     * 通过用户名获取用户
     * @param userName
     * @return
     */
    UserBO queryByUserName(String userName);

    /**
     * 创建新用户
     * @param userBO
     * @return
     */
    UserBO createUser(UserBO userBO);

    /**
     * 更新用户信息
     * @param userBO
     * @return
     */
    UserBO updateUserByUserId(UserBO userBO);
}

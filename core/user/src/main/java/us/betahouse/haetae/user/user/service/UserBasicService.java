/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.service;

import us.betahouse.haetae.user.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.user.model.CommonUser;

/**
 * 用户基础服务
 * <br/> 提供用户维度的服务
 *
 * @author dango.yxm
 * @version : UserBasicService.java 2018/11/18 下午2:38 dango.yxm
 */
public interface UserBasicService {

    /**
     * 登陆 建议使用带ip的
     * 构建完整的基础用户信息
     *
     * @param username
     * @param password
     * @return
     */
    CommonUser login(String username, String password);

    /**
     * 登陆 带记录ip
     * 构建完整的基础用户信息
     *
     * @param username
     * @param password
     * @param loginIP
     * @return
     */
    CommonUser login(String username, String password, String loginIP);

    /**
     * 登出
     * TODO @dango.yxm 2018年11月18日15:41:17 暂时无用 之后引入token时使用
     *
     * @param userId
     * @return
     */
    void loginOut(String userId);

    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    void modifyPassword(String userId, String password);

    /**
     * 修改用户信息
     *
     * @param userId
     * @param userInfoBO
     * @return
     */
    CommonUser modifyUserInfo(String userId, UserInfoBO userInfoBO);
}

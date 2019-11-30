/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model;

import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.common.ToString;

/**
 * 基础用户模型
 *
 * @author dango.yxm
 * @version : BasicUser.java 2018/11/21 2:22 PM dango.yxm
 */
public class BasicUser extends ToString {

    private static final long serialVersionUID = 8726239593982522254L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 登陆凭证
     */
    private String token;

//    private List<TripartiteLoginBO>

    /**
     * 用户信息
     */
    private UserInfoBO userInfo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserInfoBO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBO userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

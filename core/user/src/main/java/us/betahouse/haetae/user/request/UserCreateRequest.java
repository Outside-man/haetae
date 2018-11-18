/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.request;

import us.betahouse.haetae.user.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.user.model.basic.perm.UserBO;

import javax.validation.constraints.NotNull;

/**
 * 用户创建请求
 *
 * @author dango.yxm
 * @version : UserCreateRequest.java 2018/11/18 下午10:41 dango.yxm
 */
public class UserCreateRequest extends BaseUserRequest {

    private static final long serialVersionUID = -1457199050759374073L;

    /**
     * 账户信息
     */
    @NotNull
    private UserBO userBO;

    /**
     * 用户信息
     */
    private UserInfoBO userInfoBO;

    public UserBO getUserBO() {
        return userBO;
    }

    public void setUserBO(UserBO userBO) {
        this.userBO = userBO;
    }

    public UserInfoBO getUserInfoBO() {
        return userInfoBO;
    }

    public void setUserInfoBO(UserInfoBO userInfoBO) {
        this.userInfoBO = userInfoBO;
    }
}

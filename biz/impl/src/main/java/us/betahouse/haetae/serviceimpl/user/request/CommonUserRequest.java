/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.request;


import us.betahouse.haetae.user.request.UserManageRequest;

/**
 * 用户请求
 *
 * @author dango.yxm
 * @version : CommonUserRequest.java 2018/11/20 11:37 PM dango.yxm
 */
public class CommonUserRequest extends UserManageRequest {

    private static final long serialVersionUID = 652627597728059422L;

    /**
     * 微信小程序code/易班code
     */
    private String code;

    /**
     * 登陆凭证
     */
    private String token;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

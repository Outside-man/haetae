/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.request;


import us.betahouse.haetae.serviceimpl.common.BaseRequest;

/**
 * 用户请求
 *
 * @author dango.yxm
 * @version : CommonUserRequest.java 2018/11/20 11:37 PM dango.yxm
 */
public class CommonUserRequest extends BaseRequest {

    private static final long serialVersionUID = 652627597728059422L;

    /**
     * 微信小程序code
     */
    private String code;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

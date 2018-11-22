/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
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
     * 微信小程序code
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

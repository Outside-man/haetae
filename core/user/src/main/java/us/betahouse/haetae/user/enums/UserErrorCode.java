/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.enums;

import us.betahouse.util.common.ResultCode;

/**
 * 用户异常码
 *
 * @author dango.yxm
 * @version : UserErrorCode.java 2018/11/18 下午3:04 dango.yxm
 */
public enum UserErrorCode implements ResultCode {

    USERNAME_PASSWORD_NOT_RIGHT("用户名或密码错误"),

    USER_NOT_EXIST("用户不存在"),

    ;

    private String message;

    UserErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

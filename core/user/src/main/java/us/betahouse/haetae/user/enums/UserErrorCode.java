/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.enums;

import us.betahouse.util.common.ResultCode;
import us.betahouse.util.enums.CommonResultCode;

/**
 * 用户异常码
 *
 * @author dango.yxm
 * @version : UserErrorCode.java 2018/11/18 下午3:04 dango.yxm
 */
public enum UserErrorCode implements ResultCode {

    /**
     * 用户名或密码错误
     */
    USERNAME_PASSWORD_NOT_RIGHT(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "用户名或密码错误"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不存在"),

    /**
     * 用户不存在
     */
    USER_NOT_LOGIN(CommonResultCode.UNAUTHORIZED.getCode(), "用户不存在"),;

    private String code;

    private String message;

    UserErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

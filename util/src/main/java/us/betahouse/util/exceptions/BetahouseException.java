/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.exceptions;

import us.betahouse.util.common.ResultCode;
import us.betahouse.util.enums.CommonResultCode;

/**
 * betahouse 通用业务异常
 *
 * @author dango.yxm
 * @version : BetahouseException.java 2018/10/05 下午11:32 dango.yxm
 */
public class BetahouseException extends RuntimeException {

    private static final long serialVersionUID = -2372085495406352289L;

    /**
     * 异常码
     */
    private String errorCode;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 构造器
     *
     * @param cause
     */
    public BetahouseException(final Throwable cause) {
        super(cause);
        this.errorCode = CommonResultCode.SYSTEM_ERROR.getCode();
        this.message = CommonResultCode.SYSTEM_ERROR.getMessage();
    }

    public BetahouseException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BetahouseException(ResultCode resultCode) {
        this.errorCode = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BetahouseException(ResultCode resultCode, String message) {
        this.errorCode = resultCode.getCode();
        this.message = message;
    }

    public BetahouseException(Throwable cause, String errorCode, String message) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public BetahouseException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 异常获取信息
     *
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }
}

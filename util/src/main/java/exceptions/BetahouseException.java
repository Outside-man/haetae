/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package exceptions;

import common.ResultCode;
import enums.CommonResultCode;

/**
 * betahouse 通用业务异常
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
    private String errorMsg;

    /**
     * 构造器
     * @param cause
     */
    public BetahouseException(final Throwable cause){
        super(cause);
        this.errorCode = CommonResultCode.SYSTEM_ERROR.getCode();
        this.errorMsg = CommonResultCode.SYSTEM_ERROR.getMessage();
    }

    public BetahouseException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BetahouseException(ResultCode resultCode) {
        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getMessage();
    }

    public BetahouseException(Throwable cause, String errorCode, String errorMsg) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

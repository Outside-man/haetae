/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package enums;

import org.apache.commons.lang.StringUtils;

/**
 * 通用结果码
 *
 * @author dango.yxm
 * @version : CommonResultCode.java 2018/10/05 下午11:36 dango.yxm
 */
public enum CommonResultCode {

    /**
     * 系统异常
     */
    SYSTEM_ERROR("系统异常"),

    /**
     * 参数异常
     */
    ILLEGAL_PARAMETERS("参数异常"),

    /**
     * 调用成功
     */
    SUCCESS("调用成功")
    ;

    private String errorMsg;

    public CommonResultCode getByCode(String errorCode) {
        for (CommonResultCode commonResultCode : values()) {
            if (StringUtils.equals(commonResultCode.getErrorCode(), errorCode)) {
                return commonResultCode;
            }
        }
        return null;
    }

    CommonResultCode(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return name();
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

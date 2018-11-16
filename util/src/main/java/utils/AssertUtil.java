/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package utils;

import enums.CommonResultCode;
import exceptions.BetahouseException;
import org.apache.commons.lang.StringUtils;

/**
 * 断言工具
 *
 * @author dango.yxm
 * @version : AssertUtil.java 2018/10/05 下午11:30 dango.yxm
 */
public class AssertUtil {

    /**
     * 断言true
     *
     * @param expression
     * @param errorMsg
     */
    public static void assertTrue(Boolean expression, String errorMsg) {
        if (expression == null || !expression) {
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getErrorCode(), errorMsg);
        }
    }

    public static void assertTrue(Boolean expression) {
        if (expression == null || !expression) {
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS);
        }
    }

    /**
     * 断言对象等于null
     *
     * @param obj
     * @param errorMsg
     */
    public static void assertNull(Object obj, String errorMsg) {
        if (obj != null) {
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getErrorCode(), errorMsg);
        }
    }

    /**
     * 断言对象不等于null
     *
     * @param obj
     */
    public static void assertNotNull(Object obj) {
        if (obj == null) {
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS);
        }
    }

    /**
     * 断言对象不等于null
     *
     * @param obj
     * @param errorMsg
     */
    public static void assertNotNull(Object obj, String errorMsg) {
        if (obj == null) {
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getErrorCode(), errorMsg);
        }
    }

    /**
     * 断言字符串不为空串
     *
     * @param str
     * @param errorMsg
     */
    public static void assertStringNotBlank(String str, String errorMsg) {
        if (StringUtils.isBlank(str)) {
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getErrorCode(), errorMsg);
        }
    }

    /**
     * 断言字符串不为空串
     *
     * @param str
     */
    public static void assertStringNotBlank(String str) {
        if (StringUtils.isBlank(str)) {
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS);
        }
    }

    /**
     * 断言字符串为空串
     *
     * @param str
     * @param errorMsg
     */
    public static void assertStringBlank(String str, String errorMsg) {
        if (StringUtils.isNotBlank(str)) {
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getErrorCode(), errorMsg);
        }
    }

    public static void assertEquals(){}

}

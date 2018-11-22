/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.utils;

import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;

/**
 * restful 结果处理工具类
 *
 * @author dango.yxm
 * @version : RestResultUtil.java 2018/11/22 2:23 PM dango.yxm
 */
public class RestResultUtil {

    /**
     * 构建失败结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildFailResult(T data) {
        Result<T> result = buildFailResult();
        result.setData(data);
        return result;
    }

    /**
     * 构建失败结果
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildFailResult() {
        Result<T> result = buildResult(RestResultCode.SYSTEM_ERROR);
        result.setSuccess(false);
        result.setRetry(false);
        return result;
    }

    /**
     * 构建成功结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildSuccessResult(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    /**
     * 构建成功结果
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildSuccessResult() {
        Result<T> result = buildResult(RestResultCode.SUCCESS);
        result.setSuccess(true);
        result.setRetry(false);
        return result;
    }

    /**
     * 构建结果
     *
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildResult(RestResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setErrorCode(resultCode.getCode());
        result.setErrorCode(resultCode.getMessage());
        return result;
    }
}

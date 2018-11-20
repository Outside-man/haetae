/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.template;

import us.betahouse.util.common.Result;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import org.slf4j.Logger;
import us.betahouse.util.utils.LoggerUtil;

import java.text.MessageFormat;

/**
 * Rest操作模板类
 *
 * @author dango.yxm
 * @version : RestOperateTemplate.java 2018/10/06 下午2:12 dango.yxm
 */
public class RestOperateTemplate {

    /**
     * 操作模板
     *
     * @param logger     日志
     * @param methodName 方法名称 标识
     * @param request    请求
     * @param callBack   操作方法
     * @param <T>        结果实体
     * @return
     */
    public static <T> Result<T> operate(Logger logger, String methodName, Object request, RestOperateCallBack<T> callBack) {
        long start = System.currentTimeMillis();
        Result<T> result = null;
        try {
            //操作前置
            callBack.before();

            //执行操作
            result = callBack.execute();

            //操作后置
            callBack.after();
            return result;
        } catch (BetahouseException be) {
            LoggerUtil.warn(be, logger, "RestOperateTemplate.operate fail, methodName={0}, request={1}, errorCode={2}, errorMsg={3}", methodName, request, be.getErrorCode(), be.getErrorMsg());
            result = new Result<>(false, be.getErrorCode(), be.getErrorMsg());
            return result;
        } catch (Exception e) {
            LoggerUtil.error(e, logger, "RestOperateTemplate.operate error, methodName={0}, request={1}", methodName, request);
            result = new Result<>(false, CommonResultCode.SYSTEM_ERROR.getCode(), CommonResultCode.SYSTEM_ERROR.getMessage());
            return result;
        } catch (Throwable t) {
            LoggerUtil.error(t, logger, "RestOperateTemplate.operate throwable, methodName={0}, request={1}", methodName, request);
            result = new Result<>(false, CommonResultCode.SYSTEM_ERROR.getCode(), CommonResultCode.SYSTEM_ERROR.getMessage());
            return result;
        } finally {
            long end = System.currentTimeMillis();
            System.out.println(MessageFormat.format("RestOperateTemplate.operate final, methodName={0}, consume={1}ms, request={2}, result={3}", methodName, end - start, request, result));
            LoggerUtil.info(logger, "RestOperateTemplate.operate final, methodName={0}, consume={1}ms, request={2}, result={3}", methodName, end - start, request, result);
        }
    }
}

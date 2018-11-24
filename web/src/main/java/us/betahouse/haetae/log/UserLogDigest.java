/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.log;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import us.betahouse.haetae.request.UserRequest;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.log.Log;
import org.springframework.stereotype.Component;
import us.betahouse.util.log.LogLevel;
import us.betahouse.util.log.LogMark;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;


/**
 * 用户日志摘要
 *
 * @author dango.yxm
 * @version : UserLogDigest.java 2018/11/19 下午5:02 dango.yxm
 */
@Order(-1)
@Aspect
@Component
public class UserLogDigest {

    /**
     * 结果摘要日志模板
     */
    private final static String DIGEST_TEMPLATE = "[{0}] ip=[{1}], action=[{2}], result=[{3}], time=[{4}], errorMessage=[{5}], params={6}";

    @Pointcut("execution(* us.betahouse.haetae.controller..*(..)) && @annotation(us.betahouse.util.log.Log)")
    public void targetLog() {
    }


    @Around("targetLog() && @annotation(log)")
    public Object loggerParse(ProceedingJoinPoint proceedingJoinPoint, Log log) throws Throwable {
        // 获取logger
        final Logger logger = LoggerFactory.getLogger(log.loggerName());

        // 获取入参
        UserRequest request = parseRequest(proceedingJoinPoint.getArgs());
        AssertUtil.assertNotNull(request, CommonResultCode.SYSTEM_ERROR.getCode(), "日志切面不适用该方法");
        String params = JSON.toJSONString(request);

        // 获取ip
        String ip = parseIP(proceedingJoinPoint.getArgs());

        // 获取方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();

        String resultMessage = null;
        long start = System.currentTimeMillis();
        Result result = (Result) proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        resultMessage = MessageFormat.format(DIGEST_TEMPLATE, log.identity(), ip, methodName, parseResult(result), String.valueOf(end - start) + "ms", result.getErrorMsg(), params);
        log(logger, log.logLevel(), resultMessage);
        return result;
    }

    /**
     * 打印日志
     *
     * @param logger
     * @param logLevel
     * @param message
     */
    private void log(Logger logger, LogLevel logLevel, String message) {
        if (logLevel == null) {
            throw new IllegalArgumentException("日志级别不能为空");
        }
        if (logger == null) {
            throw new IllegalArgumentException("logger 不能为空");
        }
        switch (logLevel) {
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
            case DEBUG:
                logger.debug(message);
                break;
            case TRACE:
                logger.trace(message);
                break;
            default:
                throw new IllegalArgumentException("不存在日志级别");
        }
    }

    /**
     * 解析结果
     *
     * @param result
     * @return
     */
    private String parseResult(Result result) {
        return result.isSuccess() ? LogMark.SUCCESS : LogMark.FAIL;
    }

    /**
     * 解析请求
     *
     * @param args
     * @return
     */
    private UserRequest parseRequest(Object[] args) {
        UserRequest request = null;
        for (Object arg : args) {
            if (arg instanceof UserRequest) {
                request = (UserRequest) arg;
                break;
            }
        }
        return request;
    }

    /**
     * 解析ip
     *
     * @param args
     * @return
     */
    private String parseIP(Object[] args) {
        HttpServletRequest request = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                break;
            }
        }
        String ip = IPUtil.getIpAddr(request);
        if (StringUtils.isBlank(ip)) {
            return LogMark.DEFAULT;
        }
        return ip;
    }
}

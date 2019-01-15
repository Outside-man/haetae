/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.common.log;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.common.RestAop;
import us.betahouse.haetae.common.RestRequest;
import us.betahouse.haetae.utils.IPUtil;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.log.Log;
import us.betahouse.util.log.LogLevel;
import us.betahouse.util.log.LogMark;
import us.betahouse.util.utils.AssertUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户日志摘要
 *
 * @author dango.yxm
 * @version : UserLogDigest.java 2018/11/19 下午5:02 dango.yxm
 */
@Order(-1)
@Aspect
@Component
public class LogDigest extends RestAop {

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

        RestRequest request = parseRestRequest(proceedingJoinPoint);
        HttpServletRequest httpServletRequest = parseHttpServletRequest(proceedingJoinPoint);

        AssertUtil.assertNotNull(request, CommonResultCode.SYSTEM_ERROR.getCode(), "请求解析失败");
        AssertUtil.assertNotNull(httpServletRequest, CommonResultCode.SYSTEM_ERROR.getCode(), "请求解析失败");

        // 获取ip
        String ip = parseIP(httpServletRequest);

        // 获取方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();

        String resultMessage = null;
        long start = System.currentTimeMillis();
        Result result = (Result) proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        resultMessage = MessageFormat.format(DIGEST_TEMPLATE, log.identity(), ip, methodName, parseResult(result), String.valueOf(end - start) + "ms", result.getErrorMsg(), parseParams(request));
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
     * 解析ip
     *
     * @param request
     * @return
     */
    private String parseIP(HttpServletRequest request) {
        String ip = IPUtil.getIpAddr(request);
        if (StringUtils.isBlank(ip)) {
            return LogMark.DEFAULT;
        }
        return ip;
    }

    /**
     * 解析参数
     *
     * @param restRequest
     * @return
     */
    private String parseParams(RestRequest restRequest) {
        Map<String, String> params = new HashMap<>();
        String userId = restRequest.getUserId();
        if (StringUtils.isNotBlank(userId)) {
            params.put("userId", userId);
        }
        return JSON.toJSONString(params);
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.log.Log;
import org.springframework.stereotype.Component;
import us.betahouse.util.log.LogLevel;
import us.betahouse.util.log.LogMark;
import us.betahouse.util.utils.AssertUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户日志摘要
 *
 * @author dango.yxm
 * @version : UserLogDigest.java 2018/11/19 下午5:02 dango.yxm
 */
@Aspect
@Component
public class UserLogDigest {

    /**
     * 结果摘要日志模板
     */
    private final static String DIGEST_TEMPLATE = "[{0}] action=[{1}], result=[{2}], time=[{3}], errorMessage=[{4}], params={5}";

    @Pointcut("execution(* us.betahouse.haetae.user.manager..*(..)) && @annotation(us.betahouse.util.log.Log)")
    public void targetLog() {
    }


    @Around("targetLog() && @annotation(log)")
    public void loggerParse(ProceedingJoinPoint proceedingJoinPoint, Log log) throws Throwable {
        // 获取logger
        final Logger logger = LoggerFactory.getLogger(log.LoggerName());

        // 获取入参
        UserManageRequest request = parseRequest(proceedingJoinPoint.getArgs());
        AssertUtil.assertNotNull(request, CommonResultCode.SYSTEM_ERROR.getCode(), "日志切面不适用该方法");
        String params = parseParam(request);

        // 获取方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();

        String resultMessage = null;
        long start = System.currentTimeMillis();
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            long end = System.currentTimeMillis();
            resultMessage = MessageFormat.format(DIGEST_TEMPLATE, log.identity(), methodName, LogMark.FAIL, String.valueOf(end - start), t.getMessage(), params);
            log(logger, log.logLevel(), resultMessage);
            throw t;
        }

        long end = System.currentTimeMillis();
        resultMessage = MessageFormat.format(DIGEST_TEMPLATE, log.identity(), methodName, LogMark.SUCCESS, String.valueOf(end - start), LogMark.DEFAULT, params);
        log(logger, log.logLevel(), resultMessage);

    }

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

    private UserManageRequest parseRequest(Object[] args) {
        UserManageRequest request = null;
        for (Object arg : args) {
            if (arg instanceof UserManageRequest) {
                request = (UserManageRequest) arg;
                break;
            }
        }
        return request;
    }

    private String parseParam(UserManageRequest request) {
        Map<String, String> params = new HashMap<>();
        params.put("userId", request.getUserId());
        return JSON.toJSONString(params);
    }
}

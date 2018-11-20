/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import us.betahouse.util.log.Log;
import us.betahouse.util.log.LogHandle;
import us.betahouse.util.log.LogLevel;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 用户管理
 * @author dango.yxm
 * @version : UserLogDegist.java 2018/11/19 下午5:02 dango.yxm
 */
@Aspect
@Component
public class UserLogDigest implements LogHandle {

    @Pointcut("execution(* us.betahouse.haetae.user.manager.*.*(..))")
    public void targetLog() {

    }

    @Before("targetLog() && @annotation(log)")
    public void logArgs(JoinPoint joinPoint, Log log) {
        System.out.println(log.LoggerName());
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println(Arrays.toString(args));

    }

    @Around("targetLog() && @annotation(log)")
    public void loggerParse(ProceedingJoinPoint proceedingJoinPoint, Log log) {
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println(args);
    }


    @Override
    public LogLevel getLevel() {
        return null;
    }

    @Override
    public String getLogMessage() {
        return null;
    }

    @Override
    public Logger getLogger() {
        return null;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.log;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户日志
 *
 * @author dango.yxm
 * @version : Log.java 2018/11/19 下午4:11 dango.yxm
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 日志名称
     *
     * @return
     */
    String LoggerName();

    /**
     * 日志标识
     *
     * @return
     */
    String identification() default "";

    /**
     * 日志级别
     *
     * @return
     */
    LogLevel logLevel() default LogLevel.INFO;

    /**
     * 日志处理方法
     *
     * @return
     */
    Class<? extends LogHandle> logHandle();
}

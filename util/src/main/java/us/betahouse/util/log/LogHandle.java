/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志处理器
 *
 * @author dango.yxm
 * @version : LogHandle.java 2018/11/19 下午4:24 dango.yxm
 */
public interface LogHandle {

    LogLevel getLevel();

    String getLogMessage();

    default Logger getLogger(){
        return LoggerFactory.getLogger(this.getClass());
    }

    default void log() {
        LogLevel level = getLevel();
        Logger logger = getLogger();
        if (level == null) {
            throw new IllegalArgumentException("日志级别不能为空");
        }
        if (logger == null) {
            throw new IllegalArgumentException("logger 不能为空");
        }
        switch (level) {
            case INFO:
                logger.info(getLogMessage());
                break;
            case WARN:
                logger.warn(getLogMessage());
                break;
            case ERROR:
                logger.error(getLogMessage());
                break;
            case DEBUG:
                logger.debug(getLogMessage());
                break;
            case TRACE:
                logger.trace(getLogMessage());
                break;
            default:
                throw new IllegalArgumentException("不存在日志级别");
        }
    }
}

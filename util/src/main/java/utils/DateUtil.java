/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具
 *
 * @author dango.yxm
 * @version : DateUtil.java 2018/10/06 下午1:28 dango.yxm
 */
public class DateUtil {

    private static final String SHORT_TIME = "yyyyMMddHHmmss";

    private static final String YEAR_TIME = "yyyy";

    private static final String MONTH_DAY = "MMdd";


    /**
     * 获取短时间字符串
     *
     * @param date
     * @return
     */
    public static String getShortDatesStr(Date date) {
        return format(date, SHORT_TIME);
    }

    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static String getYear(Date date) {
        return format(date, YEAR_TIME);
    }

    /**
     * 获取月日
     *
     * @param date
     * @return
     */
    public static String getMonthDay(Date date) {
        return format(date, MONTH_DAY);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }
}

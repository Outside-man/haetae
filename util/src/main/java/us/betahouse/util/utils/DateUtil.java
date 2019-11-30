/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.utils;

import java.text.ParseException;
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

    private static final String YEAR_MONTH_DAY="yyyy-MM-dd";

    private static final String DAY = "dd";


    /**
     * 获取短时间字符串
     *
     * @param date
     * @return
     */
    public static String getShortDatesStr(Date date) {
        return format(date, SHORT_TIME);
    }

    public static Date getDateByShortDatesSt(String str) {
        return parse(str, SHORT_TIME);
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

    public static String getYearMonthDay(Date date){
        return format(date, YEAR_MONTH_DAY);
    }
    /**
     * 获取日
     *
     * @param date
     * @return
     */
    public static String getDay(Date date) {
        return format(date, DAY);
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

    /**
     * 字符串转换成日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parse(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期转换失败");
        }
    }

    /**
     * 判断当前是否在时间段内
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean nowIsBetween(Date start, Date end) {
        return isBetween(new Date(), start, end);
    }


    /**
     * 判断日期是不是在时间段内
     *
     * @param date
     * @param start
     * @param end
     * @return
     */
    public static boolean isBetween(Date date, Date start, Date end) {
        if (date.before(start) || date.after(end)) {
            return false;
        }
        return true;
    }
}

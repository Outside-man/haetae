/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.utils;

import java.text.DateFormat;
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

    private static final String MEDIUM_TIME = "MM月dd日 ";

    private static final String SHORT_TIME = "yyyyMMddHHmmss";

    private static final String YEAR_TIME = "yyyy";

    private static final String MONTH_DAY = "MMdd";

    private static final String YEAR_MONTH_DAY="yyyy-MM-dd";

    private static final String DAY = "dd";

    private static final String TIME_DESCRIPTION = "yyyy年MM月dd日 HH:mm";

    private static final String TIME_DATABASE = "yyyy-MM-dd HH:mm:ss";
    /**
     * 获取短时间字符串
     * @param date
     */
    public static String getShortDatesStr(Date date) {
        return format(date, SHORT_TIME);
    }

    public static Date getDateByShortDatesSt(String str) {
        return parse(str, SHORT_TIME);
    }


   //该方法用于数据库中的日期转换为MEDIUM_TIME格式
    public static String getMediumDatesStr(String str) { return getMediumDatesStr(parse(str,TIME_DATABASE)); }

    private static String getMediumDatesStr(Date date) { return format(date, MEDIUM_TIME); }

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


    /**
     * 转换成 yyyy年MM月dd日 形式
     */
   public static String parse_CH(String dateStr,String format){
       DateFormat dateFormat = new SimpleDateFormat(format);
       try {
           Date date = dateFormat.parse(dateStr);
           DateFormat CHFormat=new SimpleDateFormat(TIME_DESCRIPTION);
        return  CHFormat.format(date);
       } catch (ParseException e) {
           throw new IllegalArgumentException("日期转换失败");
       }

   }
    /**
     * 判断日期是不是今天内
     *
     * @param inputDate
     * @return
     */
    public static boolean isToday(Date inputDate){
        boolean flag = false;
        //获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date nowDate = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(nowDate);
        String subDate = format.substring(0, 10);
        //定义每天的24h时间范围
        String beginTime = subDate + " 00:00:00";
        String endTime = subDate + " 23:59:59";
        Date BeginTime = null;
        Date EndTime = null;
        try {
            BeginTime = dateFormat.parse(beginTime);
            EndTime = dateFormat.parse(endTime);

        } catch (ParseException e) {
            throw new IllegalArgumentException("日期转换失败");
        }
        if(inputDate.after(BeginTime) && inputDate.before(EndTime)) {
            flag = true;
        }
        return flag;
    }
}

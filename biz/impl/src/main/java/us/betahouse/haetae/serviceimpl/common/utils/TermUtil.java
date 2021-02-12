/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.common.utils;


import java.util.Calendar;
import java.util.Date;

/**
 * 学期工具
 *
 * @author dango.yxm
 * @version : TermUtil.java 2018/11/24 11:17 PM dango.yxm
 */
public class TermUtil {

    /**
     * 第一学期
     */
    private final static String FIRST_TERM = "A";

    /**
     * 第二学期
     */
    private final static String SECOND_TERM = "B";

    /**
     * 第一学期开始月份 9月
     */
    private final static int FIRST_TERM_START = Calendar.SEPTEMBER;

    /**
     * 第一学期结束月份 次年 3月
     */
    private final static int FIRST_TERM_END = Calendar.MARCH;


    /**
     * 获取当前学期
     *
     * @return
     */
    public static String getNowTerm() {
        return getTerm(new Date());
    }


    /**
     * 获取学期
     *
     * @param date
     * @return
     */
    public static String getTerm(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 需要月份补充
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // 月份小于第二学年的第一学期开始 就认为还在上学年 需要减1
        if (month < FIRST_TERM_START) {
            year = year - 1;
        }

        // 第一学期定义 9月01 - 次年 2月28
        if (month >= FIRST_TERM_START || month < FIRST_TERM_END) {
            return year + FIRST_TERM;
        }

        return year + SECOND_TERM;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.common.utils;


import us.betahouse.util.utils.DateUtil;

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
    private final static String FIRST_TERM = "01";

    /**
     * 第二学期
     */
    private final static String SECOND_TERM = "02";

    /**
     * 第一学期开始月份 3月
     */
    private final static int FIRST_TERM_START = Calendar.MARCH;

    /**
     * 第二学期开始月份 7月
     */
    private final static int FIRST_TERM_END = Calendar.JULY;


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

        String term = FIRST_TERM;
        if (month > FIRST_TERM_START && month < FIRST_TERM_END) {
            return DateUtil.getYear(date) + FIRST_TERM;
        }

        return DateUtil.getYear(date) + SECOND_TERM;
    }
}

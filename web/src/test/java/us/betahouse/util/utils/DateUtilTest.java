package us.betahouse.util.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * 日期工具的测试类
 *
 * @author hanwy
 */
public class DateUtilTest {

    @Test
    public void getShortDatesStr() {
        Calendar calendar = Calendar.getInstance();
        AssertUtil.assertEquals(String.valueOf(calendar.get(Calendar.DATE)), DateUtil.getDay(new Date()), "xxxx");
    }

    @Test
    public void getYear() {
    }

    @Test
    public void getMonthDay() {
    }

    @Test
    public void getDay() {
    }

    @Test
    public void format() {
    }
}
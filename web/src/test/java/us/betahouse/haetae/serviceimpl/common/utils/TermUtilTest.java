package us.betahouse.haetae.serviceimpl.common.utils;

import org.junit.Test;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.DateUtil;


public class TermUtilTest {

    @Test
    public void getNowTerm() {
        System.out.println(TermUtil.getNowTerm());
    }

    @Test
    public void getTerm() {
        // 2018-10 2018学年上学期 201801
        AssertUtil.assertEquals(TermUtil.getTerm(DateUtil.getDateByShortDatesSt("20181011000000")), "2018A");
        // 2019-01 2018学年上学期 201801
        AssertUtil.assertEquals(TermUtil.getTerm(DateUtil.getDateByShortDatesSt("20190111000000")), "2018A");
        // 2019-03 2018学年下学期 201802
        AssertUtil.assertEquals(TermUtil.getTerm(DateUtil.getDateByShortDatesSt("20190311000000")), "2018B");
        // 2019-07 2018学年下学期 201802
        AssertUtil.assertEquals(TermUtil.getTerm(DateUtil.getDateByShortDatesSt("20190711000000")), "2018B");
        // 2019-08 2018学年上学期 201802
        AssertUtil.assertEquals(TermUtil.getTerm(DateUtil.getDateByShortDatesSt("20190811000000")), "2018B");
        // 2019-09 2019学年上学期 201901
        AssertUtil.assertEquals(TermUtil.getTerm(DateUtil.getDateByShortDatesSt("20190911000000")), "2019A");
    }
}
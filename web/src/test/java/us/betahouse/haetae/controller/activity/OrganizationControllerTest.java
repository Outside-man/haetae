package us.betahouse.haetae.controller.activity;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.organization.model.OrganizationBO;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationControllerTest {

    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Test
    public void test() {
        List<OrganizationBO> organizationBOList = organizationRepoService.queryAllOrganization();
        for (OrganizationBO organizationBO : organizationBOList) {
            try {
//                String gbk= URLEncoder.encode(organizationBO.getOrganizationName(),"GBK");
//                organizationBO.setFirstAlpha(PinyinUtils.getSortLetters(getCnASCII(organizationBO.getOrganizationName())));
//                System.out.println(CharsetUtil.getEncoding(gbk));
//                break;
//                String cnStr=organizationBO.getOrganizationName();
//                System.out.println(getPingYin(cnStr));
//                System.out.println(getPinYinHeadChar(cnStr));
//                System.out.println(getCnASCII(cnStr));
                break;
//                System.out.println(getCnASCII(organizationBO.getOrganizationName()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        convert = convert.toUpperCase();
        return convert;
    }

}

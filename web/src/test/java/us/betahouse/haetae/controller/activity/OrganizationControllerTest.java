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
import us.betahouse.haetae.activity.model.basic.OrganizationBO;
import us.betahouse.haetae.serviceimpl.activity.service.OrganizationService;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.util.dictionary.PinyinUtils;

import java.net.URLEncoder;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationControllerTest {

    @Autowired
    OrganizationService organizationService;

    @Test
    public void test(){
        List<OrganizationBO> organizationBOList = organizationService.findAll(new OperateContext());
        for(OrganizationBO organizationBO:organizationBOList){
            try {
//                String gbk=URLEncoder.encode(organizationBO.getOrganizationName(),"GBK");
//                organizationBO.setFirstAlpha(PinyinUtils.getSortLetters(getCnASCII(organizationBO.getOrganizationName())));
//                System.out.println(CharsetUtil.getEncoding(gbk));
//                break;
                String cnStr=organizationBO.getOrganizationName();
                System.out.println(getPingYin(cnStr));
                System.out.println(getPinYinHeadChar(cnStr));
                System.out.println(getCnASCII(cnStr));
                break;
//                System.out.println(getCnASCII(organizationBO.getOrganizationName()));
            }catch (Exception e){
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
        convert=convert.toUpperCase();
        return convert;
    }

}

class CharsetUtil {
    /** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
    public static final String US_ASCII = "US-ASCII";

    /** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
    public static final String ISO_8859_1 = "ISO-8859-1";

    /** 8 位 UCS 转换格式 */
    public static final String UTF_8 = "UTF-8";

    /** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
    public static final String UTF_16BE = "UTF-16BE";

    /** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
    public static final String UTF_16LE = "UTF-16LE";

    /** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
    public static final String UTF_16 = "UTF-16";

    /** 中文超大字符集 */
    public static final String GBK = "GBK";

    public static final String[] CHARSET = { "US-ASCII", "ISO-8859-1", "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16", "GBK",
            "GB2312" };

    public static String getEncoding(String str) {
        for (String encode : CHARSET) {
            try {
                if (str.equals(new String(str.getBytes(encode), encode))) {
                    String s = encode;
                    return s;
                }
            } catch (Exception exception) {
            }
        }
        return "";
    }
    public static void main(String[] args) {
        System.out.println(getEncoding("你好"));
    }
}

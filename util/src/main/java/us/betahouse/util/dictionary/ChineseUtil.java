/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.util.dictionary;

/**
 * @author MessiahJK
 * @version : ChineseUtil.java 2019/01/30 21:11 MessiahJK
 */
public class ChineseUtil {


    /**
     * 中文编码长度
     */
    private static final Integer CHINESE_CHAR_LENGTH =2;

    /**
     * 大写
     */
    public static final String UPPER="UPPER";

    /**
     * 小写
     */
    public static final String LOWER="LOWER";

    /**
     * 字母数
     */
    private static final Integer CHAR_NUMBER=26;
    /**
     * 中文-字母对应
     */
    private static final char[] CHINESE_CHAR =
            {
                    '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈',
                    '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然',
                    '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座'
            };
    /**
     * 大写字母
     */
    private static final char[] UPPER_CHAR =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                    'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                    'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            };
    /**
     * 小写字母
     */
    private static final char[] LOWER_CHAR =
            {
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                    's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            };
    private static int[] table = new int[27];
    //初始化

    {
        for (int i = 0; i < 27; ++i) {
            table[i] = getGBKValue(CHINESE_CHAR[i]);
        }
    }

    /**
     * 将字符转换为字母
     * 中文字符返回拼音首字母
     * 英文返回首字母
     * 非简体汉字及英文字母将返回'#'
     *
     * @param ch 字符
     * @param type 大小写
     * @return 字母
     */
    private static char char2Alpha(char ch, String type) {
        //英文小写字符
        if (ch >= LOWER_CHAR[0] && ch <= LOWER_CHAR[CHAR_NUMBER-1]) {
            if(UPPER.equals(type)){
                return (char) (ch - 'a' + 'A');
            }else{
                return ch;
            }
        }
        //英文大写字符
        if (ch >= UPPER_CHAR[0] && ch <= UPPER_CHAR[CHAR_NUMBER-1]) {
            if(UPPER.equals(type)){
                return (char) (ch - 'a' + 'A');
            }else{
                return (char)(ch-'A'+'a');
            }
        }
        //获取字符GBK值
        int gb = getGBKValue(ch);
        //如果小于字典第一个值 返回#
        if (gb < table[0]) {
            return '#';
        }

        int i;
        for (i = 0; i < CHAR_NUMBER; ++i) {
            if (match(i, gb)) {
                break;
            }
        }

        if (i >= CHAR_NUMBER){
            return '#';
        } else{
            if(UPPER.equals(type)){
                return UPPER_CHAR[i];
            }else{
                return LOWER_CHAR[i];
            }
        }
    }
    private char char2Alpha(char ch){
        return char2Alpha(ch, UPPER);
    }
    /**
     * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
     *
     * @param sourceStr 输入字符串
     * @param type 返回大小写
     * @return
     */
    public String string2Alpha(String sourceStr,String type) {
        StringBuilder result = new StringBuilder();
        int strLength = sourceStr.length();
        int i;
        try {
            for (i = 0; i < strLength; i++) {
                result.append(char2Alpha(sourceStr.charAt(i), type));
            }
        } catch (Exception e) {
            result = new StringBuilder();
        }
        return result.toString();
    }

    /**
     * 根据一个包含汉字的字符串返回第一个汉字拼音首字母的字符串
     *
     * @param sourceStr 输入字符串
     * @param type 类型
     * @return
     */
    public static String string2AlphaFirst(String sourceStr,String type) {
        String result = "";
        try {
            result += char2Alpha(sourceStr.charAt(0),type);
        } catch (Exception ignored) {
        }
        return result;
    }


    /**
     * 取出汉字的编码
     *
     * @param ch
     * @return
     */
    private static int getGBKValue(char ch) {
        String str = "";
        str += ch;
        try {
            byte[] bytes = str.getBytes("GBK");

            if (bytes.length < CHINESE_CHAR_LENGTH){
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] &
                    0xff);
        } catch (Exception e) {
            return 0;
        }
    }

    private static boolean match(int i, int gb) {
        if (gb < table[i]) {
            return false;
        }
        int j = i + 1;

        //字母Z使用了两个标签
        while (j < CHAR_NUMBER && (table[j] == table[i])) {
            ++j;
        }
        if (j == CHAR_NUMBER) {
            return gb <= table[j];
        } else {
            return gb < table[j];
        }
    }

}

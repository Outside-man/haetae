/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.utils;

import org.apache.commons.lang.StringUtils;

import java.nio.charset.Charset;

/**
 * 字符编码工具类
 *
 * @author dango.yxm
 * @version : CharsetEncodingUtil.java 2018/10/21 下午3:41 dango.yxm
 */
public class CharsetEncodingUtil {

    private static final Charset GBK = Charset.forName("GBK");

    /**
     * 是否可以转换成GBK编码
     * @param str
     * @return
     */
    public static boolean canEncodeGBK(String str) {
        return StringUtils.isNotBlank(str) && GBK.newEncoder().canEncode(str);
    }
}

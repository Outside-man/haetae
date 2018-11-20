/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.utils;

import us.betahouse.util.utils.MD5Util;

/**
 * 加密工具类
 *
 * @author dango.yxm
 * @version : EncryptUtil.java 2018/11/18 下午2:59 dango.yxm
 */
public class EncryptUtil {

    /**
     * 密码加密 md5(md5(pwd)+salt)
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        return MD5Util.MD5(MD5Util.MD5(password) + salt);
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.builder;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.user.model.perm.UserBO;
import utils.MD5Util;

import java.util.UUID;

/**
 * 用户构建者
 *
 * @author dango.yxm
 * @version : UserBOBuilder.java 2018/11/17 下午11:08 dango.yxm
 */
final public class UserBOBuilder {

    /**
     * 用户名
     */
    private String username;

    /**
     * 明文密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * openId
     */
    private String openId;

    /**
     * 传入基本参数可以构建用户
     *
     * @param username
     * @param password
     * @return
     */
    public static UserBOBuilder getInstance(String username, String password) {
        return new UserBOBuilder(username, password);
    }

    public UserBO build() {
        UserBO userBO = new UserBO();
        // 设置盐
        if (StringUtils.isNotBlank(salt)) {
            userBO.setSalt(salt);
        } else {
            // 没有指定盐 就用uuid
            userBO.setSalt(UUID.randomUUID().toString());
        }

        userBO.setUserName(username);
        userBO.setPassword(encrypt(password, userBO.getSalt()));
        userBO.setOpenId(openId);
        return userBO;
    }

    /**
     * 加密 md5(md5(pwd)+salt)
     *
     * @param password
     * @param salt
     * @return
     */
    private String encrypt(String password, String salt) {
        return MD5Util.MD5(MD5Util.MD5(password) + salt);
    }


    /**
     * 构造函数
     *
     * @param username
     * @param password
     */
    private UserBOBuilder(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserBOBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBOBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBOBuilder withSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public UserBOBuilder withOpenId(String openId) {
        this.openId = openId;
        return this;
    }
}

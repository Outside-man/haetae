/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model.basic.perm;

import org.hibernate.validator.constraints.NotBlank;
import us.betahouse.util.common.ToString;

import java.util.Date;

/**
 * 用户模型
 *
 * @author dango.yxm
 * @version : UserBO.java 2018/11/16 下午7:22 dango.yxm
 */
public class UserBO extends ToString {

    private static final long serialVersionUID = -7947186134001585631L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    @NotBlank
    private String userName;

    /**
     * 密文密码
     */
    @NotBlank
    private String password;
    
    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 盐
     */
    @NotBlank
    private String salt;

    /**
     * 小程序用户id
     */
    private String openId;

    /**
     * 上次登陆ip
     */
    private String lastLoginIP;

    /**
     * 上次登陆时间
     */
    private Date lastLoginDate;

    /**
     * 会话id
     */
    private String sessionId;
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

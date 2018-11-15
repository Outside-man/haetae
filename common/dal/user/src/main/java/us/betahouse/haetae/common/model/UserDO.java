/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.common.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户账户
 *
 * @author dango.yxm
 * @version : UserDO.java 2018/10/05 上午10:46 dango.yxm
 */
@Entity
@Table(name = "common_user")
@EntityListeners(AuditingEntityListener.class)
public class UserDO implements Serializable {

    private static final long serialVersionUID = -3563331475230666631L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * userId 生成 不可修改
     */
    @Column(name = "user_id", updatable = false, unique = true)
    private String userId;

    /**
     * 用户名
     */
    @Column(unique = true,nullable = false)
    private String username;

    /**
     * 密码 长度不超过32位
     */
    @Column(length = 32,nullable = false)
    private String password;

    /**
     * 盐
     */
    @Column(length = 8,nullable = false)
    private String salt;

    /**
     * 微信小程序openId
     */
    @Column(length = 32)
    private String openId;

    /**
     * 上次登录时间
     */
    @Column
    private Date lastLoginDate;

    /**
     * 上次登录ip
     */
    @Column(name = "last_login_ip", length = 32)
    private String lastLoginIP;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "gmt_create",nullable = false)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified",nullable = false)
    private Date gmtModified;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}


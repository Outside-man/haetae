/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.user.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author MessiahJK
 * @version : TripartiteLoginDO.java 2019/06/30 21:14 MessiahJK
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_tripartite_login",
        indexes = {
                @Index(name = "uk_tripartite_login_id", columnList = "tripartite_login_id", unique = true),
        })
public class TripartiteLoginDO extends BaseDO{

    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;


    /**
     * 三方登陆id
     */
    @Column(name="tripartite_login_id",length =32,nullable = false)
    private String tripartiteLoginId;

    /**
     * 系统token
     */
    private String token;

    /**
     * 类型
     * 参考 us.betahouse.haetae.user.enums.TripartiteType.java
     */
    private String type;

    /**
     * 三方openId
     */
    private String openId;

    /**
     * 三方用户id
     */
    private String tripartiteUserId;

    /**
     * 信息
     */
    private String message;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTripartiteUserId() {
        return tripartiteUserId;
    }

    public void setTripartiteUserId(String tripartiteUserId) {
        this.tripartiteUserId = tripartiteUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTripartiteLoginId() {
        return tripartiteLoginId;
    }

    public void setTripartiteLoginId(String tripartiteLoginId) {
        this.tripartiteLoginId = tripartiteLoginId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

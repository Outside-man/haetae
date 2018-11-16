/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model;

import common.ToString;

import javax.persistence.*;

/**
 * 用户权限关联映射
 *
 * @author dango.yxm
 * @version : UserPermRelationBO.java 2018/11/16 下午7:08 dango.yxm
 */
public class UserPermRelationBO extends ToString {

    private static final long serialVersionUID = -2813955160454262449L;

    /**
     * 用户权限映射id
     */
    @Column(name = "user_perm_id", length = 32, nullable = false, updatable = false)
    private String userPermId;

    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;

    /**
     * 权限id
     */
    @Column(name = "perm_id", length = 32, nullable = false)
    private String permId;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;

    public String getUserPermId() {
        return userPermId;
    }

    public void setUserPermId(String userPermId) {
        this.userPermId = userPermId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}

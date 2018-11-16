/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model;

import common.ToString;

import javax.persistence.*;

/**
 * 用户角色关联映射
 *
 * @author dango.yxm
 * @version : UserRoleRelationBO.java 2018/11/16 下午6:47 dango.yxm
 */
public class UserRoleRelationBO extends ToString {

    private static final long serialVersionUID = -3499656559703117150L;

    /**
     * 用户角色映射id
     */
    @Column(name = "user_role_id", length = 32, nullable = false, updatable = false)
    private String userRoleId;

    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;

    /**
     * 角色id
     */
    @Column(name = "role_id", length = 32, nullable = false)
    private String roleId;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}

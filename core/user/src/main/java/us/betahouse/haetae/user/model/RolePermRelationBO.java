/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model;

import common.ToString;

import javax.persistence.*;

/**
 * 角色权限映射关系
 *
 * @author dango.yxm
 * @version : RolePermRelationBO.java 2018/11/16 下午6:55 dango.yxm
 */
public class RolePermRelationBO extends ToString {

    private static final long serialVersionUID = -1575539821866033837L;

    /**
     * 角色权限映射id
     */
    @Column(name = "role_perm_id", length = 32, nullable = false, updatable = false)
    private String rolePermId;

    /**
     * 角色id
     */
    @Column(name = "role_id", length = 32, nullable = false)
    private String roleId;

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

    public String getRolePermId() {
        return rolePermId;
    }

    public void setRolePermId(String rolePermId) {
        this.rolePermId = rolePermId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

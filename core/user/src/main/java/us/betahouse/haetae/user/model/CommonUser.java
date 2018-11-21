/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model;

import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础用户类型(带权限信息)
 *
 * @author dango.yxm
 * @version : CommonUser.java 2018/11/17 下午8:09 dango.yxm
 */
public class CommonUser extends BasicUser {

    private static final long serialVersionUID = -5647855589382683224L;

    /**
     * 角色信息
     */
    private Map<String, RoleBO> roleInfo = new HashMap<>();

    /**
     * 权限信息
     * key permBO.getPermId()
     */
    private Map<String, PermBO> permission = new HashMap<>();

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    /**
     * 放入角色
     *
     * @param roleBO
     */
    public void putRole(RoleBO roleBO) {
        if (roleInfo == null) {
            roleInfo = new HashMap<>();
        }
        roleInfo.put(roleBO.getRoleId(), roleBO);
    }

    /**
     * 获取角色
     *
     * @param roleId
     * @return
     */
    public RoleBO fetchRole(String roleId) {
        if (roleInfo == null) {
            return null;
        }
        return roleInfo.get(roleId);
    }

    /**
     * 放入权限
     *
     * @param perm
     */
    public void putPerm(PermBO perm) {
        if (permission == null) {
            permission = new HashMap<>();
        }
        permission.put(perm.getPermId(), perm);
    }

    /**
     * 获取权限
     *
     * @param permId
     * @return
     */
    public PermBO fetchPerm(String permId) {
        if (permission == null) {
            return null;
        }
        return permission.get(permId);
    }

    /**
     * 放入拓展信息
     *
     * @param key
     * @param value
     */
    public void putExtInfo(String key, String value) {
        if (extInfo == null) {
            extInfo = new HashMap<>();
        }
        extInfo.put(key, value);
    }

    /**
     * 取出拓展信息
     *
     * @param key
     * @return
     */
    public String fetchExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }

    public Map<String, PermBO> getPermission() {
        return permission;
    }

    public void setPermission(Map<String, PermBO> permission) {
        this.permission = permission;
    }

    public Map<String, RoleBO> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(Map<String, RoleBO> roleInfo) {
        this.roleInfo = roleInfo;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

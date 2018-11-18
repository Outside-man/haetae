/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.model;

import common.ToString;
import us.betahouse.haetae.user.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.user.model.basic.perm.RoleBO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础用户类型
 *
 * @author dango.yxm
 * @version : CommonUser.java 2018/11/17 下午8:09 dango.yxm
 */
public class CommonUser extends ToString {

    private static final long serialVersionUID = -5647855589382683224L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户信息
     */
    private UserInfoBO userInfo;

    /**
     * 角色信息
     */
    private List<RoleBO> roleInfo;

    /**
     * 权限信息
     */
    private Map<String, PermBO> permission = new HashMap<>();

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    /**
     * 放入权限
     *
     * @param perm
     */
    public void putPerm(PermBO perm) {
        if (permission == null) {
            permission = new HashMap<>();
        }
        permission.put(perm.getPermType(), perm);
    }

    /**
     * 获取权限
     *
     * @param permType
     * @return
     */
    public PermBO fetchPerm(String permType) {
        if (permission == null) {
            return null;
        }
        return permission.get(permType);
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserInfoBO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBO userInfo) {
        this.userInfo = userInfo;
    }

    public Map<String, PermBO> getPermission() {
        return permission;
    }

    public void setPermission(Map<String, PermBO> permission) {
        this.permission = permission;
    }

    public List<RoleBO> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(List<RoleBO> roleInfo) {
        this.roleInfo = roleInfo;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

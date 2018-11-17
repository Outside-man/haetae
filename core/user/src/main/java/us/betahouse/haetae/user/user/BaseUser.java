/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user;

import common.ToString;
import us.betahouse.haetae.user.model.UserInfoBO;
import us.betahouse.haetae.user.model.perm.PermBO;
import us.betahouse.haetae.user.model.perm.RoleBO;
import us.betahouse.haetae.user.model.perm.UserBO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础用户类型
 *
 * @author dango.yxm
 * @version : BaseUser.java 2018/11/17 下午8:09 dango.yxm
 */
public class BaseUser extends ToString {

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
    private Map<String, PermBO> permission;

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
}

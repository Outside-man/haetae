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
     * 账户信息
     */
    private UserBO user;

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
     * 获取用户id
     *
     * @return
     */
    public String getUserId() {
        if (user != null) {
            return user.getUserId();
        }
        return null;
    }

    public UserBO getUser() {
        return user;
    }

    public void setUser(UserBO user) {
        this.user = user;
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

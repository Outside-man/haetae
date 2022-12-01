/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model;

import us.betahouse.haetae.user.model.basic.perm.RoleBO;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础用户类型(带角色信息)
 *
 * @author dango.yxm
 * @version : CommonUser.java 2018/11/17 下午8:09 dango.yxm
 */
public class CommonUser extends BasicUser {

    private static final long serialVersionUID = -5647855589382683224L;
    
    /**
     * 头像路径
     */
    private String avatarUrl;
    
    /**
     * 角色信息
     */
    private Map<String, RoleBO> roleInfo = new HashMap<>();

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
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    
    public Map<String, RoleBO> getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(Map<String, RoleBO> roleInfo) {
        this.roleInfo = roleInfo;
    }
}

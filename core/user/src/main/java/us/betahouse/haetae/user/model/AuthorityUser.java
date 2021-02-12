/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model;

import us.betahouse.haetae.user.model.basic.perm.PermBO;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限用户模型(带权限)
 *
 * @author dango.yxm
 * @version : AuthorityUser.java 2018/11/22 10:59 AM dango.yxm
 */
public class AuthorityUser extends CommonUser {

    private static final long serialVersionUID = -3558529314705554024L;

    /**
     * 权限信息
     * key permBO.getPermId()
     */
    private Map<String, PermBO> permission = new HashMap<>();


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


    public Map<String, PermBO> getPermission() {
        return permission;
    }

    public void setPermission(Map<String, PermBO> permission) {
        this.permission = permission;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model.perm;

import common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色模型
 *
 * @author dango.yxm
 * @version : RoleBO.java 2018/11/16 下午7:29 dango.yxm
 */
public class RoleBO extends ToString {

    private static final long serialVersionUID = 6271997850765499455L;

    /**
     * 权限id
     */
    private String roleId;

    /**
     * 权限名称
     */
    private String roleName;

    /**
     * 权限描述
     */
    private String roleDesc;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

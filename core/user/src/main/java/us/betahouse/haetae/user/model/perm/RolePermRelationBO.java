/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model.perm;

import common.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashMap;
import java.util.Map;

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
    private String rolePermId;

    /**
     * 角色id
     */
    @NotBlank
    private String roleId;

    /**
     * 权限id
     */
    @NotBlank
    private String permId;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

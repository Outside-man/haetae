/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.user.request;

import us.betahouse.haetae.user.request.RoleManageRequest;

import java.util.List;
import java.util.Map;

/**
 * @author guofan.cp
 * @version : RoleRequest.java 2019/08/16 10:00 guofan.cp
 */
public class RoleUserPermRequest extends RoleManageRequest {


    /**
     * 角色id
     */
    private String roleId;

    /**
     * 学号ids
     */
    private List<String> stuIds;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescribe;

    /**
     * 权限描述
     */
    private String permDescribe;

    /**
     * 权限id
     */
    private String permId;

    /**
     * 权限ids
     */
    private List<String> permIds;

    /**
     * 权限名字
     */
    private String permName;

    /**
     * 权限类型
     */
    private String permType;

    /**
     * 额外信息
     */
    private Map<String, String> extInfo;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    @Override
    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    @Override
    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public String getPermDescribe() {
        return permDescribe;
    }

    public void setPermDescribe(String permDescribe) {
        this.permDescribe = permDescribe;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermType() {
        return permType;
    }

    public void setPermType(String permType) {
        this.permType = permType;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getStuIds() {
        return stuIds;
    }

    public void setStuIds(List<String> stuIds) {
        this.stuIds = stuIds;
    }
}

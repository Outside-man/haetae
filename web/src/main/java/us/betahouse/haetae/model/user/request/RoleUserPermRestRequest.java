/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.user.request;

import us.betahouse.haetae.common.RestRequest;

import java.util.List;

/**
 * 角色请求
 *
 * @author guofan.cp
 * @version : RoleRequest.java 2019/08/16 9:06 guofan.cp
 */
public class RoleUserPermRestRequest extends RestRequest {

    private static final long serialVersionUID = 2558811064381300140L;

    /**
     * 操作用户id
     */
    private List<String> userIds;

    /**
     * 操作用户学号
     */
    private List<String> stuIds;

    /**
     * 角色名字
     */
    private String roleName;


    /**
     * 操作角色ids
     */
    private List<String> roldIds;

    /**
     * 操作角色id
     */
    private String roleId;

    /**
     * 操作权限ids
     */
    private List<String> permIds;

    /**
     * 操作权限id
     */
    private String permId;

    /**
     * 描述信息
     */
    private String describe;

    /**
     * 权限名字
     */
    private String permName;


    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getRoldIds() {
        return roldIds;
    }

    public void setRoldIds(List<String> roldIds) {
        this.roldIds = roldIds;
    }

    public List<String> getPermIds() {
        return permIds;
    }

    public void setPermIds(List<String> permIds) {
        this.permIds = permIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roldName) {
        this.roleName = roldName;
    }

    public List<String> getStuIds() {
        return stuIds;
    }

    public void setStuIds(List<String> stuIds) {
        this.stuIds = stuIds;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

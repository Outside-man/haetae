/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.builder;

import us.betahouse.haetae.user.model.perm.RoleBO;

/**
 * 角色构建者
 *
 * @author dango.yxm
 * @version : RoleBOBuilder.java 2018/11/17 下午11:48 dango.yxm
 */
final public class RoleBOBuilder {

    /**
     * 角色码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;

    public RoleBOBuilder getInstance(String roleCode, String roleName) {
        return new RoleBOBuilder(roleCode, roleName);
    }

    public RoleBO build() {
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleCode(roleCode);
        roleBO.setRoleName(roleName);
        roleBO.setRoleDesc(roleDesc);
        return roleBO;
    }

    private RoleBOBuilder(String roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public RoleBOBuilder withRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public RoleBOBuilder withRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
        return this;
    }
}

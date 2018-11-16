/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.model.perm;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import us.betahouse.haetae.user.dal.model.BaseDO;

import javax.persistence.*;

/**
 * 角色权限映射关系
 *
 * @author dango.yxm
 * @version : RoleRermRelationDO.java 2018/11/16 下午6:55 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_user_role_relation",
        indexes = {
                @Index(name = "uk_role_perm_id", columnList = "role_perm_id", unique = true),
                @Index(name = "uk_role_id_perm_id", columnList = "role_id, perm_id", unique = true)
        })
public class RoleRermRelationDO extends BaseDO {

    private static final long serialVersionUID = -4171561993007901760L;

    /**
     * 角色权限映射id
     */
    @Column(name = "role_perm_id", length = 32, nullable = false, updatable = false)
    private String userRoleId;

    /**
     * 角色id
     */
    @Column(name = "role_id", length = 32, nullable = false)
    private String roleId;

    /**
     * 权限id
     */
    @Column(name = "perm_id", length = 32, nullable = false)
    private String permId;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
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

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}

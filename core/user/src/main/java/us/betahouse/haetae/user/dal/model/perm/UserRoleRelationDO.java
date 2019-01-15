/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.model.perm;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import us.betahouse.haetae.user.dal.model.BaseDO;

import javax.persistence.*;

/**
 * 用户角色关联映射
 *
 * @author dango.yxm
 * @version : UserRoleRelationDO.java 2018/11/16 下午6:47 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_user_role_relation",
        indexes = {
                @Index(name = "uk_user_role_id", columnList = "user_role_id", unique = true),
                @Index(name = "uk_user_id_role_id", columnList = "user_id, role_id", unique = true)
        })
public class UserRoleRelationDO extends BaseDO {

    private static final long serialVersionUID = -7653226792865270631L;

    /**
     * 用户角色映射id
     */
    @Column(name = "user_role_id", length = 32, nullable = false, updatable = false)
    private String userRoleId;

    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;

    /**
     * 角色id
     */
    @Column(name = "role_id", length = 32, nullable = false)
    private String roleId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}

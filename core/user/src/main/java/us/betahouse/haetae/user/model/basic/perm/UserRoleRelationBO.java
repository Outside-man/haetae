/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model.basic.perm;

import org.hibernate.validator.constraints.NotBlank;
import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户角色关联映射
 *
 * @author dango.yxm
 * @version : UserRoleRelationBO.java 2018/11/16 下午6:47 dango.yxm
 */
public class UserRoleRelationBO extends ToString {

    private static final long serialVersionUID = -3499656559703117150L;

    /**
     * 用户角色映射id
     */
    private String userRoleId;

    /**
     * 用户id
     */
    @NotBlank
    private String userId;

    /**
     * 角色id
     */
    @NotBlank
    private String roleId;

    /**
     * 用户名字
     */
    private String userNama;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public String getUserNama() {
        return userNama;
    }

    public void setUserNama(String userNama) {
        this.userNama = userNama;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

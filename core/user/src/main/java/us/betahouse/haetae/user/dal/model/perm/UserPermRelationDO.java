/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.model.perm;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import us.betahouse.haetae.user.dal.model.BaseDO;

import javax.persistence.*;

/**
 * 用户权限关联映射
 *
 * @author dango.yxm
 * @version : UserPermRelationDO.java 2018/11/16 下午7:08 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_user_perm_relation",
        indexes = {
                @Index(name = "uk_user_perm_id", columnList = "user_perm_id", unique = true),
                @Index(name = "uk_user_id_perm_id", columnList = "user_id, perm_id", unique = true)
        })
public class UserPermRelationDO extends BaseDO {

    private static final long serialVersionUID = -1596686242825911794L;

    /**
     * 用户权限映射id
     */
    @Column(name = "user_perm_id", length = 32, nullable = false, updatable = false)
    private String userPermId;

    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;

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

    public String getUserPermId() {
        return userPermId;
    }

    public void setUserPermId(String userPermId) {
        this.userPermId = userPermId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

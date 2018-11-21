/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.model.basic.perm;

import us.betahouse.util.common.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户权限关联映射
 *
 * @author dango.yxm
 * @version : UserPermRelationBO.java 2018/11/16 下午7:08 dango.yxm
 */
public class UserPermRelationBO extends ToString {

    private static final long serialVersionUID = -2813955160454262449L;

    /**
     * 用户权限映射id
     */
    private String userPermId;

    /**
     * 用户id
     */
    @NotBlank
    private String userId;

    /**
     * 权限id
     */
    @NotBlank
    private String permId;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

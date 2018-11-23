/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.request;

import us.betahouse.haetae.user.model.basic.perm.PermBO;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限创建请求
 *
 * @author dango.yxm
 * @version : PermManageRequest.java 2018/11/19 下午4:03 dango.yxm
 */
public class PermManageRequest extends BaseRequest {

    private static final long serialVersionUID = 7477024341940645346L;

    /**
     * 权限实体
     */
    @NotNull
    private PermBO permBO;

    /**
     * 绑定的用户
     */
    private List<String> userId = new ArrayList<>();


    /**
     * 绑定的权限ids
     */
    private List<String> roleIds = new ArrayList<>();

    public PermBO getPermBO() {
        return permBO;
    }

    public void setPermBO(PermBO permBO) {
        this.permBO = permBO;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}

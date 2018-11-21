/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.request;

import us.betahouse.haetae.user.model.basic.perm.RoleBO;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色创建请求
 *
 * @author dango.yxm
 * @version : RoleCreateRequest.java 2018/11/19 下午3:47 dango.yxm
 */
public class RoleCreateRequest extends BaseRequest {

    private static final long serialVersionUID = 2672268693238403447L;

    /**
     * 权限实体
     */
    @NotNull
    private RoleBO role;


    /**
     * 绑定的全新啊权限ids
     */
    private List<String> permIds = new ArrayList<>();

    public RoleBO getRole() {
        return role;
    }

    public void setRole(RoleBO role) {
        this.role = role;
    }

    public List<String> getPermIds() {
        return permIds;
    }

    public void setPermIds(List<String> permIds) {
        this.permIds = permIds;
    }
}

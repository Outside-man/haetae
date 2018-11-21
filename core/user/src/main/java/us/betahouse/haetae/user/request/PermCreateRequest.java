/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.request;

import us.betahouse.haetae.user.model.basic.perm.PermBO;

import javax.validation.constraints.NotNull;

/**
 * 权限创建请求
 *
 * @author dango.yxm
 * @version : PermCreateRequest.java 2018/11/19 下午4:03 dango.yxm
 */
public class PermCreateRequest extends BaseRequest {

    private static final long serialVersionUID = 7477024341940645346L;

    /**
     * 权限实体
     */
    @NotNull
    private PermBO permBO;

    public PermBO getPermBO() {
        return permBO;
    }

    public void setPermBO(PermBO permBO) {
        this.permBO = permBO;
    }
}

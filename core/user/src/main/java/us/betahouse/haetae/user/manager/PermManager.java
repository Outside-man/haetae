/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager;

import us.betahouse.haetae.user.request.PermCreateRequest;
import us.betahouse.haetae.user.user.model.basic.perm.PermBO;

/**
 * 权限管理器
 *
 * @author dango.yxm
 * @version : PermManager.java 2018/11/19 下午3:46 dango.yxm
 */
public interface PermManager {


    /**
     * 创建权限
     *
     * @param request
     * @return
     */
    PermBO createPerm(PermCreateRequest request);
}

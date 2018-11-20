/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager;

import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.haetae.user.user.model.basic.perm.UserBO;


/**
 * 用户管理器
 *
 * @author dango.yxm
 * @version : UserManager.java 2018/11/18 下午10:37 dango.yxm
 */
public interface UserManager {

    /**
     * 创建用户
     *
     * @param request
     * @return
     */
    UserBO create(UserManageRequest request);

    /**
     * 给用户批量添加角色
     *
     * @param request
     */
    void batchAddRole(UserManageRequest request);

    /**
     * 给用户批量添加权限
     *
     * @param request
     */
    void batchAddPerm(UserManageRequest request);
}

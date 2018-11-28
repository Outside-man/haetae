/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager;

import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.request.UserManageRequest;


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
    CommonUser create(UserManageRequest request);

    /**
     * 给用户批量添加角色
     *
     * @param request
     */
    void batchBindRole(UserManageRequest request);

    /**
     * 给用户批量添加角色
     *
     * @param request
     */
    void batchBindRolByCode(UserManageRequest request);

    /**
     * 给用户批量删除角色
     *
     * @param request
     */
    void batchUnbindRole(UserManageRequest request);

    /**
     * 给用户批量添加权限
     *
     * @param request
     */
    void batchBindPerm(UserManageRequest request);

    /**
     * 给用户批量删除权限
     *
     * @param request
     */
    void batchUnbindPerm(UserManageRequest request);
}

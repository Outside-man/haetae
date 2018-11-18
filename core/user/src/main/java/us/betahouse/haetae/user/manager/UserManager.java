/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager;

import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.user.request.UserCreateRequest;
import us.betahouse.haetae.user.user.model.CommonUser;

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
    @Transactional
    CommonUser create(UserCreateRequest request);

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleId
     */
    void addRole(String userId, String roleId);

    /**
     * 给用户添加权限
     *
     * @param userId
     * @param permId
     */
    void addPerm(String userId, String permId);
}

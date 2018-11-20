/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager;

import us.betahouse.util.log.Log;
import us.betahouse.haetae.user.log.UserLogDigest;
import us.betahouse.haetae.user.request.UserCreateRequest;
import us.betahouse.haetae.user.user.model.CommonUser;

import java.util.List;

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
    @Log(LoggerName = "us.betahouse.haetae.user.manager.UserManager", logHandle = UserLogDigest.class)
    CommonUser create(UserCreateRequest request);

    /**
     * 给用户批量添加角色
     *
     * @param userId
     * @param roleIds
     */
    void batchAddRole(String userId, List<String> roleIds);

    /**
     * 给用户批量添加权限
     *
     * @param userId
     * @param permIds
     */
    void batchAddPerm(String userId, List<String> permIds);
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;

import java.util.List;

/**
 * 权限仓储服务
 *
 * @author dango.yxm
 * @version : PermRepoService.java 2018/11/16 下午11:22 dango.yxm
 */
public interface PermRepoService {

    /**
     * 创建权限
     *
     * @param permBO
     * @return
     */
    PermBO createPerm(PermBO permBO);

    /**
     * 通过权限ids 获取权限
     *
     * @param permIds
     * @return
     */
    List<PermBO> queryPermsByPermIds(List<String> permIds);

    /**
     * 查询角色下的权限
     *
     * @param roleId
     * @return
     */
    List<PermBO> queryPermByRoleId(String roleId);

    /**
     * 批量查询角色下的权限
     *
     * @param roleIds
     * @return
     */
    List<PermBO> batchQueryPermByRoleId(List<String> roleIds);

    /**
     * 查询用户下的权限
     *
     * @param userId
     * @return
     */
    List<PermBO> queryPermByUserId(String userId);

    /**
     * 给角色绑定权限
     *
     * @param roleId
     * @param permIds
     */
    List<PermBO> roleBindPerms(String roleId, List<String> permIds);

    /**
     * 给角色绑定权限
     *
     * @param roleId
     * @param permIds
     */
    void roleUnbindPerms(String roleId, List<String> permIds);

    /**
     * 给用户绑定权限
     *
     * @param userId
     * @param permIds
     */
    List<PermBO> userBindPerms(String userId, List<String> permIds);

    /**
     * 给用户绑定权限
     *
     * @param userId
     * @param permIds
     */
    void userUnbindPerms(String userId, List<String> permIds);

    /**
     * 批量给用户绑定权限
     *
     * @param userIds
     * @param permId
     * @return
     */
    List<UserBO> usersBindPerm(List<String> userIds, String permId);

    /**
     * 批量给用户解除绑定权限
     *
     * @param userIds
     * @param permId
     * @return
     */
    void usersUnbindPerm(List<String> userIds, String permId);

    /**
     * 和所有角色解绑
     *
     * @param permId
     */
    void detachAllRoles(String permId);

    /**
     * 和所有用户解绑
     *
     * @param permId
     */
    void detachAllUsers(String permId);


    /**
     * 获取用户对应权限
     *
     * @param userId
     * @param permIds
     * @return
     */
    boolean verifyUserPermRelationByPermId(String userId, List<String> permIds);

    /**
     * 获取用户对应权限
     *
     * @param userId
     * @param permTypes
     * @return
     */
    boolean verifyUserPermRelationByPermType(String userId, List<String> permTypes);

    /**
     * 获取角色对应权限
     *
     * @param roleId
     * @param permIds
     * @return
     */
    boolean verifyRolePermRelationByPermId(String roleId, List<String> permIds);

    /**
     * 获取角色对应权限
     *
     * @param roleId
     * @param permTypes
     * @return
     */
    boolean verifyRolePermRelationByPermType(String roleId, List<String> permTypes);
}

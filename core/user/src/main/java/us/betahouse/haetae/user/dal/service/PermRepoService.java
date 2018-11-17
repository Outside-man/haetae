/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service;

import us.betahouse.haetae.user.model.perm.PermBO;

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
    void roleBindPerms(String roleId, List<String> permIds);

    /**
     * 给用户绑定权限
     *
     * @param userId
     * @param permIds
     */
    void userBindPerms(String userId, List<String> permIds);
}

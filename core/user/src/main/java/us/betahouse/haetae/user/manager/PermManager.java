/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.manager;

import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.request.PermManageRequest;

import java.util.Date;
import java.util.List;

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
    PermBO createPerm(PermManageRequest request);


    /**
     * 批量用户绑定权限
     *
     * @param request
     */
    void batchUsersBindPerms(PermManageRequest request);

    /**
     * 批量用户解绑权限
     *
     * @param request
     */
    void batchUsersUnbindPerms(PermManageRequest request);

    /**
     * 和所有用户解绑
     *
     * @param permId
     */
    void detachAllUsers(String permId);

    /**
     * 获取权限的用户id 并按照创建时间顺序排序
     *
     * @param permId
     * @return
     */
    List<String> getPermUsers(String permId);
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service;

import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.request.CommonUserRequest;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;

import java.util.Map;

/**
 * 用户服务
 *
 * @author dango.yxm
 * @version : UserService.java 2018/11/21 2:19 PM dango.yxm
 */
public interface UserService {


    /**
     * 注册用户信息
     *
     * @param request
     * @param context
     * @return
     */
    CommonUser register(CommonUserRequest request, OperateContext context);


    /**
     * 登录
     *
     * @param request
     * @param context
     * @return
     */
    CommonUser login(CommonUserRequest request, OperateContext context);

    /**
     * 易班登录
     *
     * @param request
     * @param context
     * @return
     */
    CommonUser yiLogin(CommonUserRequest request, OperateContext context);

    /**
     * 登陆 by token
     *
     * @param request
     * @param context
     * @return
     */
    CommonUser fetchUser(CommonUserRequest request, OperateContext context);

    /**
     * 登出
     *
     * @param request
     * @param context
     * @return
     */
    void logout(CommonUserRequest request, OperateContext context);

    /**
     * 登出
     *
     * @param request
     * @return
     */
    void wxLogout(CommonUserRequest request, OperateContext context);

    /**
     * 获取权限
     *
     * @param request
     * @param context
     * @return
     */
    Map<String, PermBO> fetchUserPerms(CommonUserRequest request, OperateContext context);

    /**
     * 获取角色
     *
     * @param request
     * @param context
     * @return
     */
    Map<String, RoleBO> fetchUserRoles(CommonUserRequest request, OperateContext context);

    /**
     * 修改账户信息
     *
     * @param request
     * @param context
     */
    void modifyUser(CommonUserRequest request, OperateContext context);

    /**
     * 根据学号修改密码
     * @param request
     * @param context
     */
    void modifyPwdByStuId(CommonUserRequest request, OperateContext context);

    /**
     * 修改用户的专业 班级 年级
     *
     * @param request
     * @param context
     */
    void modifyUserMajorAndClassAndGrade(CommonUserRequest request, OperateContext context);
}


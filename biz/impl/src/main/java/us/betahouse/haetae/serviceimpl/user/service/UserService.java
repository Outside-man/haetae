/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service;

import org.springframework.web.multipart.MultipartFile;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.request.CommonUserRequest;
import us.betahouse.haetae.serviceimpl.user.request.UploadUserExcelRequest;
import us.betahouse.haetae.serviceimpl.user.routingtable.UserRoutingTable;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;

import java.util.List;
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

    /**
     * 通过唯一的userid查询用户信息
     *
     * @param userId
     * @param context
     * @return
     */
    UserBO queryByUserId(String userId, OperateContext context);

    /**
     * 通过userId来查询用户基础模型
     *
     * @param userId
     * @param context
     * @return
     */
    CommonUser queryCommonByUserId(String userId, OperateContext context);


    /**
     * excel保存用户信息,返回excel表标题
     *
     * @param request
     * @param file
     * @param context
     * @return
     */
    List<String> saveUserByExcel(UploadUserExcelRequest request, MultipartFile file, OperateContext context);

    /**
     * 返回用户权限所及的路由表
     * @param userId
     * @return
     */
    List<UserRoutingTable> getRoutingTable(String userId);

    /**
     * 登录，管理员和活动负责人才可以登录
     * @param request
     * @param context
     * @return
     */
    CommonUser loginProxy(CommonUserRequest request, OperateContext context);

    /**
     * 给予用户导章权限
     * @param request
     * @param context
     */
    void giveStamperPerm(CommonUserRequest request,OperateContext context);

    /**
     * 通过学号查找学生信息
     * @param stuid
     * @return
     */
    CommonUser findByStuid(String stuid);

    /**
     * 解绑一位用户的导章权限
     * @param request
     * @param context
     */
    void unBindStamperPerm(CommonUserRequest request,OperateContext context);
}


/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.service;

import us.betahouse.haetae.user.model.BasicUser;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;

import java.util.List;
import java.util.Map;

/**
 * 用户基础服务
 * <br/> 提供用户维度的服务
 *
 * @author dango.yxm
 * @version : UserBasicService.java 2018/11/18 下午2:38 dango.yxm
 */
public interface UserBasicService {

    /**
     * 登陆 带记录ip
     * 构建完整的基础用户信息
     *
     * @param username
     * @param password
     * @param openId
     * @param loginIP
     * @return
     */
    CommonUser login(String username, String password, String openId, String loginIP);


    /**
     * 获取用户通用模型
     *
     * @param userId
     * @return
     */
    CommonUser getByUserId(String userId);

    /**
     * 放置token
     *
     * @param commonUser
     * @return
     */
    CommonUser setToken(CommonUser commonUser);
    /**
     * 通过学号获取用户通用模型
     *
     * @param stuId
     * @return
     */
    CommonUser getByStuId(String stuId);
    /**
     * 检测登陆 带ip
     *
     * @param token
     * @param loginIP
     * @return
     */
    UserBO checkLogin(String token, String loginIP);

    /**
     * 登出
     *
     * @param userId
     * @return
     */
    void loginOut(String userId);

    /**
     * 登出
     *
     * @param userId
     * @return
     */
    void wxLoginOut(String userId);

    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    void modifyPassword(String userId, String password);

    /**
     * 修改用户专业班级年级
     *
     * @param userId
     * @param major
     * @param classId
     * @param grade
     */
    void modifyMajorAndClassAndGrade(String userId,String major,String classId,String grade);
    /**
     * 修改用户信息
     *
     * @param basicUser
     */
    void modifyUserInfo(BasicUser basicUser);

    /**
     * 获取用户的权限
     *
     * @param userId
     * @return
     */
    Map<String, PermBO> fetchUserPerms(String userId);

    /**
     * 获取用户的角色
     *
     * @param userId
     * @return
     */
    Map<String, RoleBO> fetchUserRoles(String userId);


    /**
     * 用户鉴权
     *
     * @param userId
     * @param permIds
     * @return
     */
    boolean verifyPermissionByPermId(String userId, List<String> permIds);


    /**
     * 用户鉴权
     *
     * @param userId
     * @param permTypes
     * @return
     */
    boolean verifyPermissionByPermType(String userId, List<String> permTypes);
}

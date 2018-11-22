/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service;

import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.request.CommonUserRequest;
import us.betahouse.haetae.user.model.CommonUser;

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
     * @return
     */
    CommonUser register(CommonUserRequest request, OperateContext context);


    /**
     * 登陆
     *
     * @param request
     * @return
     */
    CommonUser login(CommonUserRequest request, OperateContext context);
}

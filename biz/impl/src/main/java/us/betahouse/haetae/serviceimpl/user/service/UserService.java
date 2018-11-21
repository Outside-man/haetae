/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service;

import us.betahouse.haetae.serviceimpl.common.BaseRequest;
import us.betahouse.haetae.user.model.BasicUser;
import us.betahouse.util.common.Result;

/**
 * 用户服务
 *
 * @author dango.yxm
 * @version : UserService.java 2018/11/21 2:19 PM dango.yxm
 */
public interface UserService {


    Result<BasicUser> register(BaseRequest request);


    /**
     * 登陆
     * @param request
     * @return
     */
    Result<BasicUser> login(BaseRequest request);
}

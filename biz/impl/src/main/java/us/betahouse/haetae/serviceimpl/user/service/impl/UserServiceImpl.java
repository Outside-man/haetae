/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.request.CommonUserRequest;
import us.betahouse.haetae.serviceimpl.user.service.UserService;
import us.betahouse.haetae.user.manager.UserManager;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.util.wechat.WeChatLoginUtil;

/**
 * 用户服务实现
 *
 * @author dango.yxm
 * @version : UserServiceImpl.java 2018/11/21 6:39 PM dango.yxm
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("")
    private String APP_ID;

    @Value("")
    private String SECRET;


    @Autowired
    private UserManager userManager;

    @Autowired
    private UserBasicService userBasicService;

    @Override
    public CommonUser register(CommonUserRequest request, OperateContext context) {
        return userManager.create(request);
    }


    @Override
    public CommonUser login(CommonUserRequest request, OperateContext context) {
        // 获取openId
        String openId = WeChatLoginUtil.fetchOpenId(request.getCode(), APP_ID, SECRET);
        return userBasicService.login(request.getUsername(), request.getPassword(), openId, context.getOperateIP());
    }
}

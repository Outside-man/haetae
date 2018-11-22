/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.request.UserRequest;
import us.betahouse.haetae.user.model.BasicUser;
import us.betahouse.util.common.Result;
import us.betahouse.util.enums.RestResultCode;
import us.betahouse.util.template.RestOperateCallBack;
import us.betahouse.util.template.RestOperateTemplate;
import us.betahouse.util.utils.AssertUtil;

/**
 * 用户接口
 *
 * @author dango.yxm
 * @version : UserController.java 2018/11/21 6:19 PM dango.yxm
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);



    @PostMapping(value = "/login")
    public Result<BasicUser> login(UserRequest request) {
        return RestOperateTemplate.operate(LOGGER, "用户登录", request, new RestOperateCallBack<BasicUser>() {
            @Override
            public void before() {
                AssertUtil.assertNotNull(request, RestResultCode.ILLEGAL_PARAMETERS.getCode(), "请求体不能为空");
                AssertUtil.assertStringNotBlank(request.getUsername(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "用户名不能为空");
                AssertUtil.assertStringNotBlank(request.getPassword(), RestResultCode.ILLEGAL_PARAMETERS.getCode(), "密码不能为空");
            }

            @Override
            public Result<BasicUser> execute() {

                return null;
            }

            @Override
            public void after() {

            }
        });
    }
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.validate;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CharsetEncodingUtil;
import us.betahouse.util.validator.Validator;

import java.text.MessageFormat;

/**
 * 用户名基础校验器
 *
 * @author dango.yxm
 * @version : UserNameBasicValidator.java 2018/10/05 下午11:14 dango.yxm
 */
public class UserNameBasicValidator implements Validator<UserManageRequest> {

    @Autowired
    private UserRepoService userRepoService;

    @Override
    public boolean support(UserManageRequest user) {
        return true;
    }

    @Override
    public void validate(UserManageRequest request) {
        AssertUtil.assertNotNull(request, "请求不能为空");
        AssertUtil.assertStringNotBlank(request.getUsername(), "用户名不能为空");
        AssertUtil.assertTrue(CharsetEncodingUtil.canEncodeGBK(request.getUsername())||CharsetEncodingUtil.canEncodeUTF8(request.getUsername()), "用户名不能包含特殊字符");
        UserBO userBO = userRepoService.queryByUserName(request.getUsername());
        AssertUtil.assertNull(userBO, MessageFormat.format("用户名已经注册, {0}", request.getUsername()));
    }
}

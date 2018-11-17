/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.validate;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.model.perm.UserBO;
import utils.AssertUtil;
import utils.CharsetEncodingUtil;
import validator.Validator;

/**
 * 用户名基础校验器
 *
 * @author dango.yxm
 * @version : UserNameBasicValidator.java 2018/10/05 下午11:14 dango.yxm
 */
public class UserNameBasicValidator implements Validator<UserBO> {

    @Autowired
    private UserRepoService userRepoService;

    @Override
    public boolean support(UserBO user) {
        return true;
    }

    @Override
    public void validate(UserBO user) {
        AssertUtil.assertNotNull(user, "请求不能为空");
        AssertUtil.assertStringNotBlank(user.getUserName(), "用户名不能为空");
        AssertUtil.assertTrue(CharsetEncodingUtil.canEncodeGBK(user.getUserName()), "用户名不能包含特殊字符");
        UserBO userBO = userRepoService.queryByUserName(user.getUserName());
        AssertUtil.assertNull(userBO, "用户名已经注册");
    }
}

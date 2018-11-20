/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.validate;

import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.validator.Validator;

/**
 * 密码基础校验器
 *
 * @author dango.yxm
 * @version : UserNameBasicValidator.java 2018/10/05 下午11:14 dango.yxm
 */
public class PasswordBasicValidator implements Validator<UserManageRequest> {

    /**
     * 密码最长长度
     */
    private final static int PASSWORD_MAX_LENGTH = 32;

    /**
     * 密码最短长度
     */
    private final static int PASSWORD_MIN_LENGTH = 6;

    @Override
    public boolean support(UserManageRequest request) {
        return true;
    }

    @Override
    public void validate(UserManageRequest request) {
        AssertUtil.assertNotNull(request, "请求不能为空");
        AssertUtil.assertStringNotBlank(request.getPassword(), "密码不能为空");
        AssertUtil.assertTrue(request.getPassword().length() <= PASSWORD_MAX_LENGTH, "密码长度不能超过32");
        AssertUtil.assertTrue(request.getPassword().length() >= PASSWORD_MIN_LENGTH, "密码长度不能少于6");
    }
}

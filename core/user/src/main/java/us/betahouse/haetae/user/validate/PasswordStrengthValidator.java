/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.validate;

import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.validator.Validator;

/**
 * 密码长度校验器
 *
 * @author dango.yxm
 * @version : UserNameBasicValidator.java 2018/10/05 下午11:14 dango.yxm
 */
public class PasswordStrengthValidator implements Validator<UserManageRequest> {

    /**
     * 密码强度最低要求
     */
    private final static int PASSWORD_STRENGTH = 2;


    @Override
    public boolean support(UserManageRequest request) {
        return true;
    }

    @Override
    public void validate(UserManageRequest request) {
        AssertUtil.assertNotNull(request, "请求不能为空");
        AssertUtil.assertStringNotBlank(request.getPassword(), "密码不能为空");
        AssertUtil.assertTrue(parsePasswordStrength(request.getPassword()) >= PASSWORD_STRENGTH, "密码强度过弱");
    }

    /**
     * 密码强度等级计算
     *
     * @param password
     * @return
     */
    private int parsePasswordStrength(String password) {
        boolean containNum = false;
        boolean containUppercase = false;
        boolean containLowercase = false;
        boolean containSymbol = false;
        for (char s : password.toCharArray()) {
            // 数字
            if (48 <= s && 57 >= s) {
                containNum = true;
                continue;
            }
            // 小写字符
            if (65 <= s && 90 >= s) {
                containUppercase = true;
                continue;
            }
            // 大写字符
            if (97 <= s && 122 >= s) {
                containLowercase = true;
                continue;
            }
            // 不是字母和数字 认为是符号或者是特殊字符
            containSymbol = true;
        }
        return (containNum ? 1 : 0) + (containUppercase ? 1 : 0) + (containLowercase ? 1 : 0) + (containSymbol ? 1 : 0);
    }
}

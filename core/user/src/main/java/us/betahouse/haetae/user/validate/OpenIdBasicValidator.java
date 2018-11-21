/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.LoggerUtil;
import us.betahouse.util.validator.Validator;

/**
 * 用户名基础校验器
 *
 * @author dango.yxm
 * @version : UserNameBasicValidator.java 2018/10/05 下午11:14 dango.yxm
 */
public class OpenIdBasicValidator implements Validator<UserManageRequest> {

    private final Logger LOGGER = LoggerFactory.getLogger(OpenIdBasicValidator.class);

    @Autowired
    private UserRepoService userRepoService;

    @Override
    public boolean support(UserManageRequest user) {
        return true;
    }

    @Override
    public void validate(UserManageRequest request) {
        AssertUtil.assertNotNull(request, "请求不能为空");
        AssertUtil.assertStringNotBlank(request.getOpenId(), "用户名不能为空");
        UserBO userBO = userRepoService.queryByUserName(request.getOpenId());
        if(userBO!=null){
            LoggerUtil.warn(LOGGER, "用户已经绑定openId, userBO={0}", userBO);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "openId已经绑定用户");
        }
    }
}

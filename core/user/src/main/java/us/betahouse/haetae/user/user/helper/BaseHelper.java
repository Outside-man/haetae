/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import us.betahouse.util.enums.CommonResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.user.model.CommonUser;
import us.betahouse.util.utils.AssertUtil;

/**
 * 助手基类
 *
 * @author dango.yxm
 * @version : BaseHelper.java 2018/11/17 下午10:47 dango.yxm
 */
public abstract class BaseHelper {

    protected final Logger LOGGER = LoggerFactory.getLogger(BaseHelper.class);

    @Autowired
    protected PermRepoService permRepoService;

    @Autowired
    protected RoleRepoService roleRepoService;

    @Autowired
    protected UserInfoRepoService userInfoRepoService;


    /**
     * 检查user模型
     *
     * @param user
     */
    protected void checkBaseUser(CommonUser user) {
        AssertUtil.assertNotNull(user, CommonResultCode.SYSTEM_ERROR.getMessage(), "用户不能为空");
        String userId = user.getUserId();
        AssertUtil.assertStringNotBlank(userId, CommonResultCode.SYSTEM_ERROR.getMessage(), "用户id不能为空");
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.user.BaseUser;


/**
 * 用户助手类
 *
 * @author dango.yxm
 * @version : UserHelper.java 2018/11/17 下午8:34 dango.yxm
 */
@Component
public class UserHelper extends BaseHelper {

    public void fillUserInfo(BaseUser user) {
        checkBaseUser(user);
        user.setUserInfo(userInfoRepoService.queryUserInfoByUserId(user.getUserId()));

    }


    public void fillRole(BaseUser user) {
        checkBaseUser(user);
        user.setRoleInfo(roleRepoService.queryRolesByUserId(user.getUserId()));
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.model.BasicUser;
import us.betahouse.haetae.user.model.CommonUser;


/**
 * 用户助手类
 *
 * @author dango.yxm
 * @version : UserHelper.java 2018/11/17 下午8:34 dango.yxm
 */
@Component
public class UserHelper extends BaseHelper {

    /**
     * 填充用户信息
     *
     * @param user
     */
    public void fillUserInfo(BasicUser user) {
        checkBaseUser(user);
        user.setUserInfo(userInfoRepoService.queryUserInfoByUserId(user.getUserId()));

    }


    /**
     * 填充角色信息
     *
     * @param user
     */
    public void fillRole(CommonUser user) {
        checkBaseUser(user);
        roleRepoService.queryRolesByUserId(user.getUserId()).forEach(user::putRole);
    }
}

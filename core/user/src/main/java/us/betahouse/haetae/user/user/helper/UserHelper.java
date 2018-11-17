/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.dal.service.UserRepoService;

/**
 * 用户助手类
 *
 * @author dango.yxm
 * @version : UserHelper.java 2018/11/17 下午8:34 dango.yxm
 */
@Component
public class UserHelper {

    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private RoleRepoService roleRepoService;
}

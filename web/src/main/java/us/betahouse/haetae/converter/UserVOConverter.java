/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.converter;

import us.betahouse.haetae.model.user.vo.UserVO;
import us.betahouse.haetae.user.model.CommonUser;

import java.util.ArrayList;

/**
 * 用户页面模型转换器
 *
 * @author dango.yxm
 * @version : UserVOConverter.java 2018/11/25 10:44 AM dango.yxm
 */
final public class UserVOConverter {

    /**
     * 用户业务模型转前端模型
     *
     * @param commonUser
     * @return
     */
    public static UserVO convert(CommonUser commonUser) {
        if (commonUser == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setToken(commonUser.getToken());
        userVO.setUserId(commonUser.getUserId());
        userVO.setUserInfo(commonUser.getUserInfo());
        userVO.setRoleInfo(new ArrayList<>(commonUser.getRoleInfo().keySet()));
        return userVO;
    }
}

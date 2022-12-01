/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.converter;

import us.betahouse.haetae.model.user.vo.UserVO;
import us.betahouse.haetae.user.model.CommonUser;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
        userVO.setAvatarUrl(commonUser.getAvatarUrl());
//        userVO.setRoleInfo(new ArrayList<>(CollectionUtils.toStream(commonUser.getRoleInfo().values()).map(RoleBO::getRoleCode).collect(Collectors.toSet())));
        return userVO;
    }
}

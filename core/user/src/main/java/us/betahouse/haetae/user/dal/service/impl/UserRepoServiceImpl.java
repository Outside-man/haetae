/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.model.perm.UserDO;
import us.betahouse.haetae.user.dal.repo.perm.UserDORepo;
import us.betahouse.haetae.user.dal.service.UserRepoService;
import us.betahouse.haetae.user.idfactory.BizIdFactory;
import us.betahouse.haetae.user.user.model.basic.perm.UserBO;
import us.betahouse.util.utils.LoggerUtil;


/**
 * 用户服务实现
 *
 * @author dango.yxm
 * @version : UserRepoServiceImpl.java 2018/11/16 下午7:39 dango.yxm
 */
@Service
public class UserRepoServiceImpl implements UserRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserRepoServiceImpl.class);

    @Autowired
    private UserDORepo userDORepo;

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory userBizIdFactory;

    @Override
    public boolean checkUserExistByUserId(String userId) {
        return userDORepo.existsByUserId(userId);
    }

    @Override
    public UserBO queryByUserName(String userName) {
        return convert(userDORepo.findByUsername(userName));
    }

    @Override
    public UserBO queryByUserId(String userId) {
        return convert(userDORepo.findByUserId(userId));
    }

    @Override
    public UserBO createUser(UserBO userBO) {
        if (StringUtils.isBlank(userBO.getUserId())) {
            userBO.setUserId(userBizIdFactory.getUserId());
        }
        return convert(userDORepo.save(convert(userBO)));
    }

    @Override
    public UserBO updateUserByUserId(UserBO userBO) {
        UserDO userDO = userDORepo.findByUserId(userBO.getUserId());
        if(userDO == null){
            LoggerUtil.error(LOGGER, "更新的用户不存在 UserBO={0}", userBO);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "更新的用户不存在");
        }
        UserDO newUserDO = convert(userBO);
        if (newUserDO.getUsername() != null) {
            userDO.setUsername(newUserDO.getUsername());
        }
        if (newUserDO.getPassword() != null) {
            userDO.setPassword(newUserDO.getPassword());
        }
        if (newUserDO.getSalt() != null) {
            userDO.setSalt(newUserDO.getSalt());
        }
        if (newUserDO.getOpenId() != null) {
            userDO.setOpenId(newUserDO.getOpenId());
        }
        if (newUserDO.getLastLoginIP() != null) {
            userDO.setLastLoginIP(newUserDO.getLastLoginIP());
        }
        if (newUserDO.getLastLoginDate() != null) {
            userDO.setLastLoginDate(newUserDO.getLastLoginDate());
        }
        return convert(userDORepo.save(userDO));
    }


    /**
     * DO2BO
     *
     * @param userDO
     * @return
     */
    private UserBO convert(UserDO userDO) {
        if (userDO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
        userBO.setUserId(userDO.getUserId());
        userBO.setUserName(userDO.getUsername());
        userBO.setPassword(userDO.getPassword());
        userBO.setSalt(userDO.getSalt());
        userBO.setOpenId(userDO.getOpenId());
        userBO.setLastLoginIP(userDO.getLastLoginIP());
        userBO.setLastLoginDate(userDO.getLastLoginDate());
        return userBO;
    }

    /**
     * BO2DO
     *
     * @param userBO
     * @return
     */
    private UserDO convert(UserBO userBO) {
        if (userBO == null) {
            return null;
        }
        UserDO userDO = new UserDO();
        userDO.setUserId(userBO.getUserId());
        userDO.setUsername(userBO.getUserName());
        userDO.setPassword(userBO.getPassword());
        userDO.setSalt(userBO.getSalt());
        userDO.setOpenId(userBO.getOpenId());
        userDO.setLastLoginIP(userBO.getLastLoginIP());
        userDO.setLastLoginDate(userBO.getLastLoginDate());
        return userDO;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import us.betahouse.haetae.user.dal.convert.EntityConverter;
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
import us.betahouse.haetae.user.model.basic.perm.UserBO;
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
        return EntityConverter.convert(userDORepo.findByUsername(userName));
    }

    @Override
    public UserBO queryByUserId(String userId) {
        return EntityConverter.convert(userDORepo.findByUserId(userId));
    }

    @Override
    public UserBO createUser(UserBO userBO) {
        if (StringUtils.isBlank(userBO.getUserId())) {
            userBO.setUserId(userBizIdFactory.getUserId());
        }
        return EntityConverter.convert(userDORepo.save(EntityConverter.convert(userBO)));
    }

    @Override
    public UserBO updateUserByUserId(UserBO userBO) {
        UserDO userDO = userDORepo.findByUserId(userBO.getUserId());
        if (userDO == null) {
            LoggerUtil.error(LOGGER, "更新的用户不存在 UserBO={0}", userBO);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "更新的用户不存在");
        }
        UserDO newUserDO = EntityConverter.convert(userBO);
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
        return EntityConverter.convert(userDORepo.save(userDO));
    }

    @Override
    public UserBO queryByOpenId(String openId) {
        return EntityConverter.convert(userDORepo.findByOpenId(openId));
    }

    @Override
    public UserBO clearOpenId(String userId) {
        UserDO userDO = userDORepo.findByUserId(userId);
        if (userDO == null) {
            LoggerUtil.error(LOGGER, "用户不存在 userId={0}", userId);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "用户不存在");
        }
        userDO.setOpenId(null);
        return EntityConverter.convert(userDORepo.save(userDO));
    }
}

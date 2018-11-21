/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.model.UserInfoDO;
import us.betahouse.haetae.user.dal.model.perm.UserDO;
import us.betahouse.haetae.user.dal.repo.UserInfoDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserDORepo;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.idfactory.BizIdFactory;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.util.utils.AssertUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息仓储服务实现
 *
 * @author dango.yxm
 * @version : UserInfoRepoServiceImpl.java 2018/11/17 下午8:37 dango.yxm
 */
@Service
public class UserInfoRepoServiceImpl implements UserInfoRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserInfoRepoServiceImpl.class);

    @Autowired
    private UserInfoDORepo userInfoDORepo;

    @Autowired
    private UserDORepo userDORepo;

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory userBizIdFactory;

    @Override
    public void bindUserInfo(String userId, UserInfoBO userInfoBO) {
        // 获取用户
        UserDO userDO = userDORepo.findByUserId(userId);
        AssertUtil.assertNotNull(userDO, "用户不存在");
        userInfoBO.setUserId(userId);

        // 获取用户信息
        UserInfoDO userInfoDO = userInfoDORepo.findByUserId(userId);
        AssertUtil.assertNull(userInfoDO, "用户已经绑定过用户信息，只能修改");

        //填充用户信息id
        if (StringUtils.isBlank(userInfoBO.getUserInfoId())) {
            userInfoBO.setUserInfoId(userBizIdFactory.getUserInfoId(userId));
        }
        userInfoDORepo.save(convert(userInfoBO));
    }

    @Override
    public UserInfoBO queryUserInfoByUserId(String userId) {
        return convert(userInfoDORepo.findByUserId(userId));
    }

    @Override
    public UserInfoBO queryUserInfoByStuId(String stuId) {
        return convert(userInfoDORepo.findByStuId(stuId));
    }

    @Override
    public UserInfoBO modifyUserInfoByUserId(String userId, UserInfoBO userInfoBO) {
        UserInfoDO userInfoDO = userInfoDORepo.findByUserId(userId);
        AssertUtil.assertNotNull(userInfoDO, "修改的用户信息不存在");

        UserInfoDO newUserInfoDO = convert(userInfoBO);
        if (newUserInfoDO.getClassId() != null) {
            userInfoDO.setClassId(newUserInfoDO.getClassId());
        }
        if (newUserInfoDO.getMajorId() != null) {
            userInfoDO.setMajorId(newUserInfoDO.getMajorId());
        }
        if (newUserInfoDO.getRealName() != null) {
            userInfoDO.setRealName(newUserInfoDO.getRealName());
        }
        if (newUserInfoDO.getEnrollDate() != null) {
            userInfoDO.setEnrollDate(newUserInfoDO.getEnrollDate());
        }
        if (newUserInfoDO.getSex() != null) {
            userInfoDO.setSex(newUserInfoDO.getSex());
        }
        if (newUserInfoDO.getStuId() != null) {
            userInfoDO.setStuId(newUserInfoDO.getStuId());
        }

        // 特殊逻辑 拓展信息为null 时认为是主动清空
        if (newUserInfoDO.getExtInfo() == null) {
            userInfoDO.setExtInfo(JSON.toJSONString(new HashMap<>()));
        } else if (!userInfoBO.getExtInfo().isEmpty()) {
            // 只有当拓展信息不为空时才会更新
            userInfoDO.setExtInfo(newUserInfoDO.getExtInfo());
        }
        return convert(userInfoDORepo.save(userInfoDO));
    }

    /**
     * 用户信息 DO2BO
     *
     * @param userInfoDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private UserInfoBO convert(UserInfoDO userInfoDO) {
        if (userInfoDO == null) {
            return null;
        }
        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setUserInfoId(userInfoDO.getUserInfoId());
        userInfoBO.setUserId(userInfoDO.getUserId());
        userInfoBO.setStuId(userInfoDO.getStuId());
        userInfoBO.setRealName(userInfoDO.getRealName());
        userInfoBO.setSex(userInfoDO.getSex());
        userInfoBO.setMajorId(userInfoDO.getMajorId());
        userInfoBO.setClassId(userInfoDO.getClassId());
        userInfoBO.setEnrollDate(userInfoDO.getEnrollDate());
        userInfoBO.setExtInfo(JSON.parseObject(userInfoDO.getExtInfo(), Map.class));
        return userInfoBO;
    }

    /**
     * 用户信息 BO2DO
     *
     * @param userInfoBO
     * @return
     */
    private UserInfoDO convert(UserInfoBO userInfoBO) {
        if (userInfoBO == null) {
            return null;
        }
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setUserInfoId(userInfoBO.getUserInfoId());
        userInfoDO.setUserId(userInfoBO.getUserId());
        userInfoDO.setStuId(userInfoBO.getStuId());
        userInfoDO.setRealName(userInfoBO.getRealName());
        userInfoDO.setSex(userInfoBO.getSex());
        userInfoDO.setMajorId(userInfoBO.getMajorId());
        userInfoDO.setClassId(userInfoBO.getClassId());
        userInfoDO.setEnrollDate(userInfoBO.getEnrollDate());
        userInfoDO.setExtInfo(JSON.toJSONString(userInfoBO.getExtInfo()));
        return userInfoDO;
    }
}

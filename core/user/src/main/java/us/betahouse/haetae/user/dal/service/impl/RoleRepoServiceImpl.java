/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import com.alibaba.fastjson.JSON;
import enums.CommonResultCode;
import exceptions.BetahouseException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.model.perm.RoleDO;
import us.betahouse.haetae.user.dal.model.perm.UserDO;
import us.betahouse.haetae.user.dal.model.perm.UserRoleRelationDO;
import us.betahouse.haetae.user.dal.repo.perm.RoleDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserRoleRelationDORepo;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.idfactory.BizIdFactory;
import us.betahouse.haetae.user.model.RoleBO;
import us.betahouse.haetae.user.model.UserRoleRelationBO;
import utils.AssertUtil;
import utils.LoggerUtil;
import utils.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色仓储服务
 *
 * @author dango.yxm
 * @version : RoleRepoServiceImpl.java 2018/11/16 下午9:50 dango.yxm
 */
@Service
public class RoleRepoServiceImpl implements RoleRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(RoleRepoServiceImpl.class);

    @Autowired
    private RoleDORepo roleDORepo;

    @Autowired
    private UserRoleRelationDORepo userRoleRelationDORepo;

    @Autowired
    private UserDORepo userDORepo;

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory bizIdFactory;

    @Override
    public RoleBO createRole(RoleBO roleBO) {
        if (StringUtils.isBlank(roleBO.getRoleId())) {
            roleBO.setRoleId(bizIdFactory.getRoleId());
        }
        return convert(roleDORepo.save(convert(roleBO)));
    }

    @Override
    public List<RoleBO> queryRolesByRoleIds(List<String> roleIds) {
        List<RoleDO> roleDOList = roleDORepo.findAllByRoleIdIn(roleIds);
        return CollectionUtils.toStream(roleDOList)
                .filter(Objects::nonNull)
                .map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<RoleBO> queryRolesByUserId(String userId) {
        List<UserRoleRelationDO> userRoleRelations = userRoleRelationDORepo.findAllByUserId(userId);
        List<String> roleIds = CollectionUtils.toStream(userRoleRelations)
                .filter(Objects::nonNull)
                .map(UserRoleRelationDO::getRoleId)
                .collect(Collectors.toList());
        return queryRolesByRoleIds(roleIds);
    }

    @Override
    public List<UserRoleRelationBO> userBindRoles(String userId, List<String> roleIds) {
        // 获取用户信息
        UserDO userDO = userDORepo.findByUserId(userId);
        AssertUtil.assertNotNull(userDO, "用户不存在");

        // 获取 roleIds 对应的角色信息
        List<RoleDO> roleDOList = roleDORepo.findAllByRoleIdIn(roleIds);

        // roleId 有查不到的就属于异常
        if (CollectionUtils.isEmpty(roleDOList) || roleDOList.size() != roleIds.size()) {
            LoggerUtil.error(LOGGER, "绑定的角色id部分不存在 roleIds={0}, roleList={1}", roleIds, roleDOList);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "绑定的角色id不存在");
        }

        // 查询已经绑定的权限
        List<String> userBoundRoleIds = CollectionUtils.toStream(userRoleRelationDORepo.findAllByUserId(userId))
                .filter(Objects::nonNull).map(UserRoleRelationDO::getRoleId)
                .collect(Collectors.toList());

        // 存在已绑定的角色,需要特殊处理 必须要迭代器删除不然会并发修改问题
        if (!CollectionUtils.isEmpty(userBoundRoleIds)) {
            // 构建迭代器
            Iterator<RoleDO> roleIterator = roleDOList.iterator();
            while (roleIterator.hasNext()) {
                RoleDO role = roleIterator.next();
                for (String boundId : userBoundRoleIds) {
                    LoggerUtil.warn(LOGGER, "重复绑定角色 userId={0}, role={1}", userId, role);
                    // 是已绑的角色 就从里面溢出
                    if (StringUtils.equals(boundId, role.getRoleId())) {
                        roleIterator.remove();
                    }
                }
            }
        }

        // 构建关联关系实体
        List<UserRoleRelationDO> relationDOList = new ArrayList<>();
        for (RoleDO roleDO : roleDOList) {
            UserRoleRelationBO relationBO = new UserRoleRelationBO();
            relationBO.setRoleId(roleDO.getRoleId());
            relationBO.setUserId(userId);
            // 通过 id 工厂构建关联id
            relationBO.setUserRoleId(bizIdFactory.getRoleUserRelationId(roleDO.getRoleId(), userId));
            relationDOList.add(convert(relationBO));
        }
        return CollectionUtils.toStream(userRoleRelationDORepo.saveAll(relationDOList))
                .filter(Objects::nonNull)
                .map(this::convert).collect(Collectors.toList());
    }

    /**
     * 用户角色关系 DO2BO
     *
     * @param relationDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private UserRoleRelationBO convert(UserRoleRelationDO relationDO) {
        if (relationDO == null) {
            return null;
        }
        UserRoleRelationBO relationBO = new UserRoleRelationBO();
        relationBO.setUserRoleId(relationDO.getUserRoleId());
        relationBO.setRoleId(relationDO.getRoleId());
        relationBO.setUserId(relationDO.getUserId());
        relationBO.setExtInfo(JSON.parseObject(relationDO.getExtInfo(), Map.class));
        return relationBO;
    }

    /**
     * 用户角色关系 BO2DO
     *
     * @param relationBO
     * @return
     */
    private UserRoleRelationDO convert(UserRoleRelationBO relationBO) {
        if (relationBO == null) {
            return null;
        }
        UserRoleRelationDO relationDO = new UserRoleRelationDO();
        relationDO.setUserRoleId(relationBO.getUserRoleId());
        relationDO.setRoleId(relationBO.getRoleId());
        relationDO.setUserId(relationBO.getUserId());
        relationDO.setExtInfo(JSON.toJSONString(relationBO.getExtInfo()));
        return relationDO;
    }

    /**
     * 角色DO2BO
     *
     * @param roleDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private RoleBO convert(RoleDO roleDO) {
        if (roleDO == null) {
            return null;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(roleDO.getRoleName());
        roleBO.setRoleId(roleDO.getRoleId());
        roleBO.setRoleDesc(roleDO.getRoleDesc());
        roleBO.setExtInfo(JSON.parseObject(roleDO.getExtInfo(), Map.class));
        return roleBO;
    }

    /**
     * 角色BO2DO
     *
     * @param roleBO
     * @return
     */
    private RoleDO convert(RoleBO roleBO) {
        if (roleBO == null) {
            return null;
        }
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleName(roleBO.getRoleName());
        roleDO.setRoleId(roleBO.getRoleId());
        roleDO.setRoleDesc(roleBO.getRoleDesc());
        roleDO.setExtInfo(JSON.toJSONString(roleBO.getExtInfo()));
        return roleDO;
    }
}

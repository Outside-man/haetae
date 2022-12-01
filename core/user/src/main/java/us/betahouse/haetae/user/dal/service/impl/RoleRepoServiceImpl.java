/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.user.dal.convert.EntityConverter;
import us.betahouse.haetae.user.dal.convert.RelationConverter;
import us.betahouse.haetae.user.dal.model.perm.RoleDO;
import us.betahouse.haetae.user.dal.model.perm.UserDO;
import us.betahouse.haetae.user.dal.model.perm.UserRoleRelationDO;
import us.betahouse.haetae.user.dal.repo.perm.RoleDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserRoleRelationDORepo;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.enums.RoleCode;
import us.betahouse.haetae.user.idfactory.BizIdFactory;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.haetae.user.model.basic.perm.UserRoleRelationBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

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
    private BizIdFactory userBizIdFactory;

    @Override
    public RoleBO createRole(RoleBO roleBO) {
        if (StringUtils.isBlank(roleBO.getRoleId())) {
            roleBO.setRoleId(userBizIdFactory.getRoleId());
        }
        return EntityConverter.convert(roleDORepo.save(EntityConverter.convert(roleBO)));
    }

    @Override
    public List<RoleBO> queryRolesByRoleIds(List<String> roleIds) {
        List<RoleDO> roleDOList = roleDORepo.findAllByRoleIdIn(roleIds);
        return CollectionUtils.toStream(roleDOList)
                .filter(Objects::nonNull)
                .map(EntityConverter::convert).collect(Collectors.toList());
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
    public List<RoleBO> userBindRoles(String userId, List<String> roleIds) {
        // 获取用户信息
        UserDO userDO = userDORepo.findByUserId(userId);
        AssertUtil.assertNotNull(userDO, "用户不存在");

        // 获取 roleIds 对应的角色信息
        List<RoleDO> roleDOList = roleDORepo.findAllByRoleIdIn(roleIds);

        // roleId 有查不到的就属于异常
        if (roleDOList.size() != roleIds.size()) {
            LoggerUtil.error(LOGGER, "绑定的角色id不存在 roleIds={0}, roleList={1}", roleIds, roleDOList);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "绑定的角色id不存在");
        }

        // 绑定的用户角色
        List<RoleBO> bindRoles = CollectionUtils.toStream(roleDOList)
                .filter(Objects::nonNull)
                .map(EntityConverter::convert).collect(Collectors.toList());

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
                    // 是已绑的角色 就从里面移除
                    if (StringUtils.equals(boundId, role.getRoleId())) {
                        LoggerUtil.warn(LOGGER, "重复绑定角色 userId={0}, role={1}", userId, role);
                        roleIterator.remove();
                    }
                }
            }
        }

        // 构建关联关系实体
        List<UserRoleRelationDO> relations = new ArrayList<>();
        for (RoleDO roleDO : roleDOList) {
            UserRoleRelationBO relation = new UserRoleRelationBO();
            relation.setRoleId(roleDO.getRoleId());
            relation.setUserId(userId);
            // 通过 id 工厂构建关联id
            relation.setUserRoleId(userBizIdFactory.getRoleUserRelationId(roleDO.getRoleId(), userId));
            relations.add(RelationConverter.convert(relation));
        }
        // 绑定
        userRoleRelationDORepo.saveAll(relations);

        return bindRoles;
    }

    @Override
    public RoleBO userBindRolesByCode(String userId, RoleCode roleCode) {
        RoleDO roleDO = roleDORepo.findByRoleCode(roleCode.getCode());
        AssertUtil.assertNotNull(roleCode, "角色不存在");
        userBindRoles(userId, Collections.singletonList(roleDO.getRoleId()));
        return EntityConverter.convert(roleDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userUnbindRoles(String userId, List<String> roleIds) {
        // 获取用户信息
        UserDO userDO = userDORepo.findByUserId(userId);
        AssertUtil.assertNotNull(userDO, "用户不存在");

        // 获取 roleIds 对应的角色信息
        List<RoleDO> roleDOList = roleDORepo.findAllByRoleIdIn(roleIds);

        // roleId 有查不到的就属于异常
        if (roleDOList.size() != roleIds.size()) {
            LoggerUtil.error(LOGGER, "解绑的角色id不存在 roleIds={0}, roleList={1}", roleIds, roleDOList);
        }

        userRoleRelationDORepo.deleteByUserIdAndRoleIdIn(userId, roleIds);
    }

    @Override
    public List<UserBO> usersBindRole(List<String> userIds, String roleId) {
        RoleDO roleDO = roleDORepo.findByRoleId(roleId);
        AssertUtil.assertNotNull(roleDO, "角色不存在");

        List<UserDO> userDOList = userDORepo.findAllByUserIdIn(userIds);
        if (userDOList.size() != userIds.size()) {
            LoggerUtil.error(LOGGER, "绑定的用户id不存在 userIds={0}, userList={1}", userIds, userDOList);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "需要绑定的用户不存在");
        }

        // 绑定的用户角色
        List<UserBO> bindUsers = CollectionUtils.toStream(userDOList)
                .filter(Objects::nonNull)
                .map(EntityConverter::convert).collect(Collectors.toList());

        // 查询已经绑定的权限
        List<String> boundUserIds = CollectionUtils.toStream(userRoleRelationDORepo.findAllByRoleId(roleId))
                .filter(Objects::nonNull).map(UserRoleRelationDO::getUserId)
                .collect(Collectors.toList());

        // 存在已绑定的角色,需要特殊处理 必须要迭代器删除不然会并发修改问题
        if (!CollectionUtils.isEmpty(boundUserIds)) {
            // 构建迭代器
            Iterator<UserDO> userIterator = userDOList.iterator();
            while (userIterator.hasNext()) {
                UserDO user = userIterator.next();
                for (String boundId : boundUserIds) {
                    // 是已绑的角色 就从里面移除
                    if (StringUtils.equals(boundId, user.getUserId())) {
                        LoggerUtil.warn(LOGGER, "重复绑定角色 roleId={0}, user={1}", roleId, user);
                        userIterator.remove();
                    }
                }
            }
        }

        // 构建关联关系实体
        List<UserRoleRelationDO> relations = new ArrayList<>();
        for (UserDO userDO : userDOList) {
            UserRoleRelationBO relation = new UserRoleRelationBO();
            relation.setRoleId(roleId);
            relation.setUserId(userDO.getUserId());
            // 通过 idfactory 工厂构建关联id
            relation.setUserRoleId(userBizIdFactory.getRoleUserRelationId(roleId, userDO.getUserId()));
            relations.add(RelationConverter.convert(relation));
        }
        // 绑定
        userRoleRelationDORepo.saveAll(relations);

        return bindUsers;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void usersUnbindRole(List<String> userIds, String roleId) {
        RoleDO roleDO = roleDORepo.findByRoleId(roleId);
        AssertUtil.assertNotNull(roleDO, "角色不存在");

        List<UserDO> userDOList = userDORepo.findAllByUserIdIn(userIds);
        if (userDOList.size() != userIds.size()) {
            LoggerUtil.error(LOGGER, "绑定的用户id不存在 userIds={0}, userList={1}", userIds, userDOList);
        }
        // 解绑
        userRoleRelationDORepo.deleteByRoleIdAndUserIdIn(roleId, userIds);
    }

    @Override
    @Transactional
    public void detachAllUser(String roleId) {
        RoleDO roleDO = roleDORepo.findByRoleId(roleId);
        AssertUtil.assertNotNull(roleDO, "角色不存在");
        userRoleRelationDORepo.deleteByRoleId(roleId);
    }

    @Override
    public RoleBO initRole(RoleBO roleBO) {
        RoleDO roleDO = roleDORepo.findByRoleCode(roleBO.getRoleCode());
        if (roleDO == null) {
            if (StringUtils.isBlank(roleBO.getRoleId())) {
                roleBO.setRoleId(userBizIdFactory.getRoleId());
            }
            roleDO = roleDORepo.save(EntityConverter.convert(roleBO));
        }
        return EntityConverter.convert(roleDO);
    }

    @Override
    public List<RoleBO> findAllRole() {
        List<RoleDO> roledos=roleDORepo.findAll();
        return CollectionUtils.toStream(roledos)
                .filter(Objects::nonNull)
                .map(EntityConverter::convert)
                .collect(Collectors.toList());
    }
}

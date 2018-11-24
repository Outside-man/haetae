/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import us.betahouse.haetae.user.dal.convert.EntityConverter;
import us.betahouse.haetae.user.model.basic.perm.UserBO;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.model.perm.*;
import us.betahouse.haetae.user.dal.repo.perm.*;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.idfactory.BizIdFactory;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 权限仓储服务实现
 *
 * @author dango.yxm
 * @version : PermRepoServiceImpl.java 2018/11/16 下午11:23 dango.yxm
 */
@Service
public class PermRepoServiceImpl implements PermRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(PermRepoServiceImpl.class);

    @Autowired
    private PermDORepo permDORepo;

    @Autowired
    private RoleDORepo roleDORepo;

    @Autowired
    private UserDORepo userDORepo;

    @Autowired
    private UserPermRelationDORepo userPermRelationDORepo;

    @Autowired
    private RolePermRelationDORepo rolePermRelationDORepo;

    /**
     * id工厂
     */
    @Autowired
    private BizIdFactory userBizIdFactory;


    @Override
    public PermBO createPerm(PermBO permBO) {
        if (StringUtils.isBlank(permBO.getPermId())) {
            permBO.setPermId(userBizIdFactory.getPermId());
        }
        return EntityConverter.convert(permDORepo.save(EntityConverter.convert(permBO)));
    }

    @Override
    public List<PermBO> queryPermsByPermIds(List<String> permIds) {
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIds);
        return CollectionUtils.toStream(permDOList)
                .filter(Objects::nonNull)
                .map(EntityConverter::convert).collect(Collectors.toList());
    }

    @Override
    public List<PermBO> queryPermByRoleId(String roleId) {
        // 获取映射关系
        List<RolePermRelationDO> rolePermRelations = rolePermRelationDORepo.findAllByRoleId(roleId);
        // 从映射关系抽出权限id
        List<String> permIds = CollectionUtils.toStream(rolePermRelations)
                .filter(Objects::nonNull)
                .map(RolePermRelationDO::getPermId)
                .collect(Collectors.toList());
        // 获取对应权限实体
        return queryPermsByPermIds(permIds);
    }

    @Override
    public List<PermBO> batchQueryPermByRoleId(List<String> roleIds) {
        // 获取映射关系
        List<RolePermRelationDO> rolePermRelations = rolePermRelationDORepo.findAllByRoleIdIn(roleIds);
        // 从映射关系抽出权限id
        List<String> permIds = CollectionUtils.toStream(rolePermRelations)
                .filter(Objects::nonNull)
                .map(RolePermRelationDO::getPermId)
                .collect(Collectors.toList());
        // 获取对应权限实体
        return queryPermsByPermIds(permIds);
    }

    @Override
    public List<PermBO> queryPermByUserId(String userId) {
        // 获取映射关系
        List<UserPermRelationDO> userPermRelations = userPermRelationDORepo.findAllByUserId(userId);
        // 从映射关系抽出权限id
        List<String> permIds = CollectionUtils.toStream(userPermRelations)
                .filter(Objects::nonNull)
                .map(UserPermRelationDO::getPermId)
                .collect(Collectors.toList());
        // 获取对应权限实体
        return queryPermsByPermIds(permIds);
    }

    @Override
    public List<PermBO> roleBindPerms(String roleId, List<String> permIds) {
        // 获取用户信息
        RoleDO roleDO = roleDORepo.findByRoleId(roleId);
        AssertUtil.assertNotNull(roleDO, "角色不存在");

        // 获取 permIds 对应的权限信息
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIds);

        // 绑定的权限
        List<PermBO> bindPerms = CollectionUtils.toStream(permDOList)
                .filter(Objects::nonNull)
                .map(EntityConverter::convert).collect(Collectors.toList());

        // permIds 有查不到的就属于异常
        if (permDOList.size() != permIds.size()) {
            LoggerUtil.error(LOGGER, "绑定的权限id不存在 permIds={0}, permDOList={1}", permIds, permDOList);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "绑定的权限id不存在");
        }

        // 查询已经绑定的权限
        List<String> roleBoundPermIds = CollectionUtils.toStream(rolePermRelationDORepo.findAllByRoleId(roleId))
                .filter(Objects::nonNull).map(RolePermRelationDO::getPermId)
                .collect(Collectors.toList());

        // 存在已绑定的权限,需要特殊处理 必须要迭代器删除不然会并发修改问题
        if (!CollectionUtils.isEmpty(roleBoundPermIds)) {
            // 构建迭代器
            Iterator<PermDO> permIterator = permDOList.iterator();
            while (permIterator.hasNext()) {
                PermDO perm = permIterator.next();
                for (String boundId : roleBoundPermIds) {
                    LoggerUtil.warn(LOGGER, "角色重复绑定权限 roleId={0}, perm={1}", roleId, perm);
                    // 是已绑的权限 就从里面移除
                    if (StringUtils.equals(boundId, perm.getPermId())) {
                        permIterator.remove();
                    }
                }
            }
        }

        // 构建关联关系实体
        List<RolePermRelationDO> relations = new ArrayList<>();
        for (PermDO permDO : permDOList) {
            RolePermRelationDO relation = new RolePermRelationDO();
            relation.setPermId(permDO.getPermId());
            relation.setRoleId(roleId);
            // 通过 id 工厂构建关联id
            relation.setRolePermId(userBizIdFactory.getRoleUserRelationId(roleId, permDO.getPermId()));
            relations.add(relation);
        }
        // 绑定
        rolePermRelationDORepo.saveAll(relations);

        return bindPerms;
    }

    @Override
    public void roleUnbindPerms(String roleId, List<String> permIds) {
        // 获取用户信息
        RoleDO roleDO = roleDORepo.findByRoleId(roleId);
        AssertUtil.assertNotNull(roleDO, "角色不存在");

        // 获取 permIds 对应的权限信息
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIds);


        // permIds 有查不到的就属于异常
        if (permDOList.size() != permIds.size()) {
            LoggerUtil.warn(LOGGER, "解绑的权限id不存在 permIds={0}, permDOList={1}", permIds, permDOList);
        }
        rolePermRelationDORepo.deleteAllByRoleIdAndPermIdIn(roleId, permIds);
    }

    @Override
    public List<PermBO> userBindPerms(String userId, List<String> permIds) {
        // 获取用户信息
        UserDO userDO = userDORepo.findByUserId(userId);
        AssertUtil.assertNotNull(userDO, "用户不存在");

        // 获取 permIds 对应的权限信息
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIds);

        // 绑定的权限
        List<PermBO> bindPerms = CollectionUtils.toStream(permDOList)
                .filter(Objects::nonNull)
                .map(EntityConverter::convert).collect(Collectors.toList());

        // permIds 有查不到的就属于异常
        if (permDOList.size() != permIds.size()) {
            LoggerUtil.error(LOGGER, "绑定的权限id不存在 permIds={0}, permDOList={1}", permIds, permDOList);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "绑定的权限id不存在");
        }

        // 查询已经绑定的权限
        List<String> userBoundPermIds = CollectionUtils.toStream(userPermRelationDORepo.findAllByUserId(userId))
                .filter(Objects::nonNull).map(UserPermRelationDO::getPermId)
                .collect(Collectors.toList());

        // 存在已绑定的权限,需要特殊处理 必须要迭代器删除不然会并发修改问题
        if (!CollectionUtils.isEmpty(userBoundPermIds)) {
            // 构建迭代器
            Iterator<PermDO> permIterator = permDOList.iterator();
            while (permIterator.hasNext()) {
                PermDO perm = permIterator.next();
                for (String boundId : userBoundPermIds) {
                    LoggerUtil.warn(LOGGER, "用户重复绑定权限 userId={0}, perm={1}", userId, perm);
                    // 是已绑的角色 就从里面溢出
                    if (StringUtils.equals(boundId, perm.getPermId())) {
                        permIterator.remove();
                    }
                }
            }
        }

        // 构建关联关系实体
        List<UserPermRelationDO> relations = new ArrayList<>();
        for (PermDO permDO : permDOList) {
            UserPermRelationDO relation = new UserPermRelationDO();
            relation.setPermId(permDO.getPermId());
            relation.setUserId(userId);
            // 通过 id 工厂构建关联id
            relation.setUserPermId(userBizIdFactory.getUserPermRelationId(userId, permDO.getPermId()));
            relations.add(relation);
        }
        // 绑定
        userPermRelationDORepo.saveAll(relations);

        return bindPerms;
    }

    @Override
    public void userUnbindPerms(String userId, List<String> permIds) {
        // 获取用户信息
        UserDO userDO = userDORepo.findByUserId(userId);
        AssertUtil.assertNotNull(userDO, "用户不存在");

        // 获取 permIds 对应的权限信息
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIds);

        // permIds 有查不到的就属于异常
        if (permDOList.size() != permIds.size()) {
            LoggerUtil.error(LOGGER, "绑定的权限id不存在 permIds={0}, permDOList={1}", permIds, permDOList);
            throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "绑定的权限id不存在");
        }

        userPermRelationDORepo.deleteAllByUserIdAndPermIdIn(userId, permIds);

    }

    @Override
    public List<UserBO> usersBindPerm(List<String> userIds, String permId) {
        // 获取权限信息
        PermDO permDO = permDORepo.findByPermId(permId);
        AssertUtil.assertNotNull(permDO, "权限不存在");


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
        List<String> boundUserIds = CollectionUtils.toStream(userPermRelationDORepo.findAllByPermId(permId))
                .filter(Objects::nonNull).map(UserPermRelationDO::getUserId)
                .collect(Collectors.toList());

        // 存在已绑定的角色,需要特殊处理 必须要迭代器删除不然会并发修改问题
        CollectionUtils.removeDuplicate(userDOList, boundUserIds, UserDO::getUserId);

        // 构建关联关系实体
        List<UserPermRelationDO> relations = new ArrayList<>();
        for (UserDO userDO : userDOList) {
            UserPermRelationDO relation = new UserPermRelationDO();
            relation.setPermId(permId);
            relation.setUserId(userDO.getUserId());
            // 通过 id 工厂构建关联id
            relation.setUserPermId(userBizIdFactory.getUserPermRelationId(permId, userDO.getUserId()));
            relations.add(relation);
        }
        // 绑定
        userPermRelationDORepo.saveAll(relations);

        return bindUsers;
    }

    @Override
    public void usersUnbindPerm(List<String> userIds, String permId) {
        // 获取权限信息
        PermDO permDO = permDORepo.findByPermId(permId);
        AssertUtil.assertNotNull(permDO, "权限不存在");


        List<UserDO> userDOList = userDORepo.findAllByUserIdIn(userIds);
        if (userDOList.size() != userIds.size()) {
            LoggerUtil.error(LOGGER, "绑定的用户id不存在 userIds={0}, userList={1}", userIds, userDOList);
        }
        userPermRelationDORepo.deleteAllByPermIdAndUserIdIn(permId, userIds);
    }

    @Override
    public void detachAllRoles(String permId) {
        // 获取权限信息
        PermDO permDO = permDORepo.findByPermId(permId);
        AssertUtil.assertNotNull(permDO, "权限不存在");
        rolePermRelationDORepo.deleteAllByPermId(permId);
    }

    @Override
    public void detachAllUsers(String permId) {
        // 获取权限信息
        PermDO permDO = permDORepo.findByPermId(permId);
        AssertUtil.assertNotNull(permDO, "权限不存在");
        userPermRelationDORepo.deleteAllByPermId(permId);
    }

    @Override
    public PermBO fetchUserPermRelationByPermId(String userId, String permId) {
        // 获取权限信息
        PermDO permDO = permDORepo.findByPermId(permId);
        AssertUtil.assertNotNull(permDO, "权限不存在");

        return userPermRelationDORepo.findByUserIdAndPermId(userId, permId) == null ? null : EntityConverter.convert(permDO);
    }

    @Override
    public PermBO fetchUserPermRelationByPermType(String userId, String permType) {
        // 获取权限信息
        PermDO permDO = permDORepo.findByPermType(permType);
        AssertUtil.assertNotNull(permDO, "权限不存在");

        return userPermRelationDORepo.findByUserIdAndPermId(userId, permDO.getPermId()) == null ? null : EntityConverter.convert(permDO);

    }

    @Override
    public PermBO fetchRolePermRelationByPermId(String userId, String permId) {
        // 获取权限信息
        PermDO permDO = permDORepo.findByPermId(permId);
        AssertUtil.assertNotNull(permDO, "权限不存在");


        return rolePermRelationDORepo.findByRoleIdAndPermId(userId, permId) == null ? null : EntityConverter.convert(permDO);
    }

    @Override
    public PermBO fetchRolePermRelationByPermType(String userId, String permType) {
        // 获取权限信息
        PermDO permDO = permDORepo.findByPermType(permType);
        AssertUtil.assertNotNull(permDO, "权限不存在");

        return rolePermRelationDORepo.findByRoleIdAndPermId(userId, permDO.getPermId()) == null ? null : EntityConverter.convert(permDO);
    }
}

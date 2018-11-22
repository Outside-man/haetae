/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import com.alibaba.fastjson.JSON;
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
import us.betahouse.haetae.user.model.basic.perm.RolePermRelationBO;
import us.betahouse.haetae.user.model.basic.perm.UserPermRelationBO;
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
        return convert(permDORepo.save(convert(permBO)));
    }

    @Override
    public List<PermBO> queryPermsByPermIds(List<String> permIds) {
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIds);
        return CollectionUtils.toStream(permDOList)
                .filter(Objects::nonNull)
                .map(this::convert).collect(Collectors.toList());
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
    public void roleBindPerms(String roleId, List<String> permIds) {
        // 获取用户信息
        RoleDO roleDO = roleDORepo.findByRoleId(roleId);
        AssertUtil.assertNotNull(roleDO, "角色不存在");

        // 获取 permIds 对应的权限信息
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIds);


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
    }

    @Override
    public void userBindPerms(String userId, List<String> permIds) {
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
    }

    /**
     * 权限DO2BO
     *
     * @param permDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private PermBO convert(PermDO permDO) {
        if (permDO == null) {
            return null;
        }
        PermBO permBO = new PermBO();
        permBO.setPermId(permDO.getPermId());
        permBO.setPermType(permDO.getPermType());
        permBO.setPermName(permDO.getPermName());
        permBO.setPermDesc(permDO.getPermDesc());
        permBO.setExtInfo(JSON.parseObject(permDO.getExtInfo(), Map.class));
        return permBO;
    }

    /**
     * 权限BO2DO
     *
     * @param permBO
     * @return
     */
    private PermDO convert(PermBO permBO) {
        if (permBO == null) {
            return null;
        }
        PermDO permDO = new PermDO();
        permDO.setPermId(permBO.getPermId());
        permDO.setPermType(permBO.getPermType());
        permDO.setPermName(permBO.getPermName());
        permDO.setPermDesc(permBO.getPermDesc());
        permDO.setExtInfo(JSON.toJSONString(permBO.getExtInfo()));
        return permDO;
    }

    /**
     * 用户权限关系DO2BO
     *
     * @param relationDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private UserPermRelationBO convert(UserPermRelationDO relationDO) {
        if (relationDO == null) {
            return null;
        }
        UserPermRelationBO relationBO = new UserPermRelationBO();
        relationBO.setUserPermId(relationDO.getUserPermId());
        relationBO.setUserId(relationDO.getUserId());
        relationBO.setPermId(relationDO.getPermId());
        relationBO.setExtInfo(JSON.parseObject(relationDO.getExtInfo(), Map.class));
        return relationBO;
    }

    /**
     * 用户权限关系BO2DO
     *
     * @param relationBO
     * @return
     */
    private UserPermRelationDO convert(UserPermRelationBO relationBO) {
        if (relationBO == null) {
            return null;
        }
        UserPermRelationDO relationDO = new UserPermRelationDO();
        relationDO.setUserPermId(relationBO.getUserPermId());
        relationDO.setUserId(relationBO.getUserId());
        relationDO.setPermId(relationBO.getPermId());
        relationDO.setExtInfo(JSON.toJSONString(relationBO.getExtInfo()));
        return relationDO;
    }

    /**
     * 角色权限关系DO2BO
     *
     * @param relationDO
     * @return
     */
    @SuppressWarnings("unchecked")
    private RolePermRelationBO convert(RolePermRelationDO relationDO) {
        if (relationDO == null) {
            return null;
        }
        RolePermRelationBO relationBO = new RolePermRelationBO();
        relationBO.setRolePermId(relationDO.getRolePermId());
        relationBO.setRoleId(relationDO.getRoleId());
        relationBO.setPermId(relationDO.getPermId());
        relationBO.setExtInfo(JSON.parseObject(relationDO.getExtInfo(), Map.class));
        return relationBO;
    }

    /**
     * 角色权限关系BO2DO
     *
     * @param relationBO
     * @return
     */
    private RolePermRelationDO convert(RolePermRelationBO relationBO) {
        if (relationBO == null) {
            return null;
        }
        RolePermRelationDO relationDO = new RolePermRelationDO();
        relationDO.setRolePermId(relationBO.getRolePermId());
        relationDO.setRoleId(relationBO.getRoleId());
        relationDO.setPermId(relationBO.getPermId());
        relationDO.setExtInfo(JSON.toJSONString(relationBO.getExtInfo()));
        return relationDO;
    }
}

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
import us.betahouse.haetae.user.dal.model.perm.*;
import us.betahouse.haetae.user.dal.repo.perm.*;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.idfactory.BizIdFactory;
import us.betahouse.haetae.user.model.PermBO;
import us.betahouse.haetae.user.model.RolePermRelationBO;
import us.betahouse.haetae.user.model.UserPermRelationBO;
import utils.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 权限仓储服务实现
 *
 * @author dango.yxm
 * @version : PermRepoServiceImpl.java 2018/11/16 下午11:23 dango.yxm
 */
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
    private BizIdFactory bizIdFactory;


    @Override
    public PermBO createPerm(PermBO permBO) {
        if (StringUtils.isBlank(permBO.getPermId())) {
            permBO.setPermId(bizIdFactory.getPermId());
        }
        return convert(permDORepo.save(convert(permBO)));
    }

    @Override
    public List<PermBO> queryPermByRoleId(String roleId) {
        // 获取映射关系
        List<RolePermRelationDO> rolePermRelations = rolePermRelationDORepo.findAllByRoleId(roleId);
        // 从映射关系抽出权限id
        List<String> permIs = CollectionUtils.toStream(rolePermRelations)
                .filter(Objects::nonNull)
                .map(RolePermRelationDO::getPermId)
                .collect(Collectors.toList());
        // 获取对应权限实体
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIs);
        return CollectionUtils.toStream(permDOList)
                .filter(Objects::nonNull)
                .map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<PermBO> queryPermByUserId(String userId) {
        // 获取映射关系
        List<UserPermRelationDO> userPermRelations = userPermRelationDORepo.findAllByUserId(userId);
        // 从映射关系抽出权限id
        List<String> permIs = CollectionUtils.toStream(userPermRelations)
                .filter(Objects::nonNull)
                .map(UserPermRelationDO::getPermId)
                .collect(Collectors.toList());
        // 获取对应权限实体
        List<PermDO> permDOList = permDORepo.findAllByPermIdIn(permIs);
        return CollectionUtils.toStream(permDOList)
                .filter(Objects::nonNull)
                .map(this::convert).collect(Collectors.toList());
    }

    @Override
    public List<RolePermRelationBO> roleBindPerms(String roleId, List<String> permIds) {
        return null;
    }

    @Override
    public List<UserPermRelationBO> userBindPerms(String userId, List<String> permIds) {
        return null;
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
        permBO.setPermName(permDO.getPermName());
        permBO.setPermDesc(permDO.getPermDesc());
        permBO.setExtInfo(JSON.parseObject(permDO.getExtInfo(), Map.class));
        return permBO;
    }

    /**
     * 权限DO2BO
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
        permDO.setPermName(permBO.getPermName());
        permDO.setPermDesc(permBO.getPermDesc());
        permDO.setExtInfo(JSON.toJSONString(permBO.getExtInfo()));
        return permDO;
    }
}

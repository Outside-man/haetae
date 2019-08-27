/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.finance.constant.FinancePermType;
import us.betahouse.haetae.serviceimpl.user.request.RoleUserPermRequest;
import us.betahouse.haetae.serviceimpl.user.service.PermService;
import us.betahouse.haetae.user.dal.convert.EntityConverter;
import us.betahouse.haetae.user.dal.model.perm.PermDO;
import us.betahouse.haetae.user.dal.repo.perm.PermDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserRoleRelationDORepo;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.request.PermManageRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限服务实现
 *
 * @author guofan.cp
 * @version : PermServiceImpl.java 2019/08/23 10:28 guofan.cp
 */
@Service
public class PermServiceImpl implements PermService {


    @Autowired
    PermManager permManager;

    @Autowired
    PermDORepo permDORepo;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Autowired
    private UserRoleRelationDORepo userRoleRelationDORepo;

    private static final String permType = "MANAGER_CREATE";

    @Override
    public PermBO createPerm(RoleUserPermRequest request, OperateContext context) {
        PermManageRequest permManageRequest = new PermManageRequest();
        PermBO permbo = new PermBO();
        permbo.setPermDesc(request.getPermDescribe());
        permbo.setPermName(request.getPermName());
        //权限类型设定为管理员创建
        permbo.setPermType(permType);
        permbo.setExtInfo(request.getExtInfo());
        permManageRequest.setPermBO(permbo);
        return permManager.createPerm(permManageRequest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserInfoBO> batchUsersBindPerms(RoleUserPermRequest request, OperateContext context) {
        List<String> useIds = new ArrayList<>();
        List<String> stuids = request.getStuIds();
        List<UserInfoBO> userInfoBOS = new ArrayList<>();
        String userId;
        for (int i = 0; i < stuids.size(); i++) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(stuids.get(i));
            userId = userInfoBO.getUserId();
            AssertUtil.assertNotNull(userId, "用户学号不存在：" + stuids.get(i) + "绑定权限失败");
            userInfoBOS.add(userInfoBO);
            useIds.add(userId);
        }
        PermManageRequest permManageRequest = new PermManageRequest();
        permManageRequest.setUserIds(useIds);
        PermDO permDO = permDORepo.findByPermId(request.getPermId());
        AssertUtil.assertNotNull(permDO, "权限不存在");
        permManageRequest.setPermBO(EntityConverter.convert(permDO));
        permManager.batchUsersBindPerms(permManageRequest);
        return userInfoBOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUsersUnbindPerms(RoleUserPermRequest request, OperateContext context) {
        List<String> useIds = new ArrayList<>();
        List<String> stuids = request.getStuIds();
        String userId;
        for (int i = 0; i < stuids.size(); i++) {
            userId = userInfoRepoService.queryUserInfoByStuId(stuids.get(i)).getUserId();
            AssertUtil.assertNotNull(userId, "用户学号不存在：" + stuids.get(i) + "解绑失败");
            useIds.add(userId);
        }
        PermManageRequest permManageRequest = new PermManageRequest();
        permManageRequest.setUserIds(useIds);
        PermDO permDO = permDORepo.findByPermId(request.getPermId());
        AssertUtil.assertNotNull(permDO, "权限不存在");
        permManageRequest.setPermBO(EntityConverter.convert(permDO));
        permManager.batchUsersUnbindPerms(permManageRequest);
    }

    @Override
    public void detachAllUsers(RoleUserPermRequest request, OperateContext context) {
        String permId = request.getPermId();
        AssertUtil.assertNotNull(permDORepo.findByPermId(permId), "权限id不存在");
        permManager.detachAllUsers(request.getPermId());
    }

    @Override
    public List<UserInfoBO> getPermUsers(RoleUserPermRequest request, OperateContext context) {
        String permId = request.getPermId();
        AssertUtil.assertNotNull(permDORepo.findByPermId(permId), "权限id不存在");
        List<String> userIds = permManager.getPermUsers(permId);
        List<UserInfoBO> userInfoBOS = new ArrayList<>();
        for (String userId : userIds) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByUserId(userId);
            userInfoBOS.add(userInfoBO);
        }
        return userInfoBOS;
    }

    @Override
    public List<PermBO> findAllNotContainStamperAndFinance() {
        List<PermDO> permDos = permDORepo.findAll();
        return CollectionUtils.toStream(permDos)
                .filter(permDO -> !(permDO.getPermType().equals(ActivityPermType.ACTIVITY_STAMPER)))
                .filter(permDO -> !(permDO.getPermType().equals(FinancePermType.FINANCE_BAN)))
                .filter(permDO -> !(permDO.getPermType().equals(FinancePermType.FINANCE_MANAGER)))
                .map(EntityConverter::convert)
                .collect(Collectors.toList());
    }
}

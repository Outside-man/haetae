/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.user.enums.UserRoleCode;
import us.betahouse.haetae.serviceimpl.user.request.RoleUserPermRequest;
import us.betahouse.haetae.serviceimpl.user.service.RoleService;
import us.betahouse.haetae.user.dal.convert.EntityConverter;
import us.betahouse.haetae.user.dal.model.perm.UserRoleRelationDO;
import us.betahouse.haetae.user.dal.repo.perm.RoleDORepo;
import us.betahouse.haetae.user.dal.repo.perm.UserRoleRelationDORepo;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.manager.RoleManager;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.model.basic.perm.UserRoleRelationBO;
import us.betahouse.haetae.user.request.RoleManageRequest;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色服务
 *
 * @author dango.yxm
 * @version : RoleServiceImpl.java 2018/11/23 10:33 AM dango.yxm
 */
@Service
public class RoleServiceImpl implements RoleService {

    //创建角色 默认role_code
    private static final String roleCode = "MANAGE_CREATE";


    @Autowired
    private RoleManager roleManager;
    @Autowired
    private RoleDORepo roleDORepo;
    @Autowired
    private UserInfoRepoService userInfoRepoService;
    @Autowired
    private UserRoleRelationDORepo userRoleRelationDORepo;

    @Override
    public List<RoleBO> findAllRole() {
        return roleManager.findAllRole();
    }

    @Override
    public RoleBO createRole(RoleUserPermRequest request, OperateContext context) {
        //创建permBO 判断权限是否存在  useid是否存在
        RoleManageRequest roleManageRequest = new RoleManageRequest();
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(request.getRoleName());
        roleBO.setRoleDesc(request.getRoleDescribe());
        roleBO.setRoleCode(roleCode);
        roleManageRequest.setRole(roleBO);
        if (request.getPermIds() != null) {
            roleManageRequest.setPermIds(request.getPermIds());
        }
        List<String> userIds=new ArrayList<>();
        if (request.getStuIds() != null) {
            for (String stuId : request.getStuIds()) {
                String useId=userInfoRepoService.queryUserInfoByStuId(stuId).getUserId();
                AssertUtil.assertNotNull(stuId+"学号不存在");
                userIds.add(useId);
            }
            roleManageRequest.setUserIds(userIds);
        }
        return roleManager.createRole(roleManageRequest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void bindUsers(RoleUserPermRequest request, OperateContext context) {
        List<String> useIds = new ArrayList<>();
        List<String> stuids = request.getStuIds();
        //获取useid
        String userId;
        for (int i = 0; i < stuids.size(); i++) {
            userId = userInfoRepoService.queryUserInfoByStuId(stuids.get(i)).getUserId();
            AssertUtil.assertNotNull(userId);
            useIds.add(userId);
        }
        //获取角色
        RoleManageRequest roleManageRequest = new RoleManageRequest();
        RoleBO roleBO = EntityConverter.convert(roleDORepo.findByRoleId(request.getRoleId()));
        roleManageRequest.setRole(roleBO);
        roleManageRequest.setUserIds(useIds);
        roleManager.batchUsersBindRole(roleManageRequest);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unbindUsers(RoleUserPermRequest request, OperateContext context) {
        List<String> useids = request.getUserIds();
        List<String> stuids = request.getStuIds();
        String userId;
        if (useids == null && stuids == null) {
            AssertUtil.assertNotNull("解绑用户主体不存在");
        }
        if (useids == null) {
            useids = new ArrayList<String>();
        }
        if (stuids.size() != 0) {
            for (int i = 0; i < stuids.size(); i++) {
                userId = userInfoRepoService.queryUserInfoByStuId(stuids.get(i)).getUserId();
                AssertUtil.assertNotNull(userId);
                useids.add(userId);
            }
        }
        RoleBO roleBO = EntityConverter.convert(roleDORepo.findByRoleId(request.getRoleId()));
        RoleManageRequest roleManageRequest = new RoleManageRequest();
        roleManageRequest.setUserIds(useids);
        roleManageRequest.setRole(roleBO);
        roleManager.batchUsersUnbindRole(roleManageRequest);
    }

    @Override
    public List<UserRoleRelationBO> findAllUserRelationRole() {
        //过滤掉活动盖章员和证书审核员
        String activityStampRoleId = roleDORepo.findByRoleCode(UserRoleCode.CERTIFICATE_CONFIRM.getCode()).getRoleId();
        String certificateRoleId = roleDORepo.findByRoleCode(UserRoleCode.ACTIVITY_STAMPER.getCode()).getRoleId();
        String noStudentRoleId = roleDORepo.findByRoleCode(UserRoleCode.NOT_STUDENT.getCode()).getRoleId();
        List<UserRoleRelationDO> userRoleRelationDOS = userRoleRelationDORepo.findAll();
        List<UserRoleRelationBO> userRoleRelationBOList = CollectionUtils.toStream(userRoleRelationDOS)
                .filter(Objects::nonNull)
                .filter(userRoleRelationDO -> !(userRoleRelationDO.getRoleId().equals(activityStampRoleId)))
                .filter(userRoleRelationDO -> !(userRoleRelationDO.getRoleId().equals(certificateRoleId)))
                .filter(userRoleRelationDO -> !(userRoleRelationDO.getRoleId().equals(noStudentRoleId)))
                .map(EntityConverter::convert)
                .collect(Collectors.toList());
        for (int i = 0; i < userRoleRelationBOList.size(); i++) {
            String name = userInfoRepoService.queryUserInfoByUserId(userRoleRelationBOList.get(i).getUserId()).getRealName();
            userRoleRelationBOList.get(i).setUserNama(name);
            String rolename = roleDORepo.findByRoleId(userRoleRelationBOList.get(i).getRoleId()).getRoleName();
            userRoleRelationBOList.get(i).setRoleName(rolename);
        }
        return userRoleRelationBOList;
    }
}

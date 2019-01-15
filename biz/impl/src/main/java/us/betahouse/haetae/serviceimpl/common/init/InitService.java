/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.common.init;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityPermTypeEnum;
import us.betahouse.haetae.serviceimpl.user.enums.UserRoleCode;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.enums.PermType;
import us.betahouse.haetae.user.enums.RoleCode;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.user.builder.PermBOBuilder;
import us.betahouse.haetae.user.user.builder.RoleBOBuilder;

import java.util.*;

/**
 * 初始化设置
 *
 * @author dango.yxm
 * @version : InitService.java 2018/11/25 4:54 PM dango.yxm
 */
@Service
public class InitService {

    @Autowired
    private PermRepoService permRepoService;

    @Autowired
    private RoleRepoService roleRepoService;

    private final static List<String> activityManagerPerm = new ArrayList<>();

    static {
        activityManagerPerm.add(ActivityPermType.ACTIVITY_CREATE);
        activityManagerPerm.add(ActivityPermType.ACTIVITY_PUBLISH);
        activityManagerPerm.add(ActivityPermType.ACTIVITY_FINISH);
        activityManagerPerm.add(ActivityPermType.ACTIVITY_RESTART);
        activityManagerPerm.add(ActivityPermType.STAMPER_MANAGE);
    }


    public void init() {

        // 初始化权限
        Map<String, String> initPermMap = new HashMap<>();
        PermBOBuilder permBuilder = PermBOBuilder.getInstance();
        for (PermType permType : ActivityPermTypeEnum.values()) {
            if (permType.isInit()) {
                permBuilder.withPermType(permType.getCode())
                        .withPermName(permType.getDesc());
                initPermMap.put(permType.getCode(), permRepoService.initPerm(permBuilder.build()).getPermId());
            }
        }

        // 初始化角色
        Map<String, String> initRoleMap = new HashMap<>();
        RoleBOBuilder roleBOBuilder = RoleBOBuilder.getInstance();
        for (RoleCode roleCode : UserRoleCode.values()) {
            roleBOBuilder.withRoleCode(roleCode.getCode())
                    .withRoleName(roleCode.getDesc());
            RoleBO role = roleRepoService.initRole(roleBOBuilder.build());
            intRoleBindPerm(role, initPermMap);
        }
    }


    private void intRoleBindPerm(RoleBO role, Map<String, String> initPermMap) {

        // 初始化 活动管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.ACTIVITY_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            activityManagerPerm.forEach(activityPermType -> {
                permIds.add(initPermMap.get(activityPermType));
            });
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }

    }
}

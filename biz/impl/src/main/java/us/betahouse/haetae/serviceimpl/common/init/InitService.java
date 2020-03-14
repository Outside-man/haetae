/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.common.init;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.finance.manager.FinanceManager;
import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;
import us.betahouse.haetae.organization.manager.OrganizationManager;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityPermTypeEnum;
import us.betahouse.haetae.serviceimpl.asset.constant.AssetPermType;
import us.betahouse.haetae.serviceimpl.asset.enums.AssetPermTypeEnum;
import us.betahouse.haetae.serviceimpl.certificate.constant.CertificatePermType;
import us.betahouse.haetae.serviceimpl.certificate.enums.CertificatePermTypeEnum;
import us.betahouse.haetae.serviceimpl.finance.constant.FinancePermExInfoKey;
import us.betahouse.haetae.serviceimpl.finance.enums.FinancePermTypeEnum;
import us.betahouse.haetae.serviceimpl.locale.constant.LocalePermType;
import us.betahouse.haetae.serviceimpl.locale.enums.LocalePermTypeEnum;
import us.betahouse.haetae.serviceimpl.organization.constant.OrganizationPermType;
import us.betahouse.haetae.serviceimpl.organization.enums.OrganizationPermTypeEnum;
import us.betahouse.haetae.serviceimpl.user.enums.UserRoleCode;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.RoleRepoService;
import us.betahouse.haetae.user.enums.PermType;
import us.betahouse.haetae.user.enums.RoleCode;
import us.betahouse.haetae.user.model.basic.perm.RoleBO;
import us.betahouse.haetae.user.user.builder.PermBOBuilder;
import us.betahouse.haetae.user.user.builder.RoleBOBuilder;

import java.math.BigDecimal;
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


    @Autowired
    private FinanceManager financeManager;

    @Autowired
    private OrganizationManager organizationManager;

    private final static List<String> activityManagerPerm = new ArrayList<>();

    private final static List<String> assetManagerPerm = new ArrayList<>();

    private final static List<String> organizationManagerPerm = new ArrayList<>();

    private final static List<String> certificateManagerPerm = new ArrayList<>();

    private final static List<String> certificateConfirmPerm = new ArrayList<>();

    private final static List<String> localeManagerPerm = new ArrayList<>();


    static {
        //活动
        activityManagerPerm.add(ActivityPermType.ACTIVITY_CREATE);
        activityManagerPerm.add(ActivityPermType.ACTIVITY_PUBLISH);
        activityManagerPerm.add(ActivityPermType.ACTIVITY_FINISH);
        activityManagerPerm.add(ActivityPermType.ACTIVITY_RESTART);
        activityManagerPerm.add(ActivityPermType.STAMPER_MANAGE);
        //物资
        assetManagerPerm.add(AssetPermType.ASSET_CREATE);
        assetManagerPerm.add(AssetPermType.ASSET_UPDATE);
        assetManagerPerm.add(AssetPermType.ASSET_DELETE);
        assetManagerPerm.add(AssetPermType.ASSET_SEEK);
        //组织
        organizationManagerPerm.add(OrganizationPermType.ALL_ORG_MANAGE);
        organizationManagerPerm.add(OrganizationPermType.ALL_ORG_MEMBER_MANAGE);
        organizationManagerPerm.add(OrganizationPermType.ALL_ORG_RELATION_MANAGE);
        //证书
        certificateConfirmPerm.add(CertificatePermType.CONFIRM_CERTIFICATE);
        certificateConfirmPerm.add(CertificatePermType.GET_CERTIFICATES);
        certificateConfirmPerm.add(CertificatePermType.MODIFY_CERTIFICATE);

        certificateManagerPerm.add(CertificatePermType.GET_CERTIFICATES);
        certificateManagerPerm.add(CertificatePermType.DELETE_CERTIFICATE);
        certificateManagerPerm.add(CertificatePermType.CONFIRM_CERTIFICATE);
        certificateManagerPerm.add(CertificatePermType.IMPORT_CERTIFICATE);
        certificateManagerPerm.add(CertificatePermType.MODIFY_CERTIFICATE);
        certificateManagerPerm.add(CertificatePermType.MANAGER_CONFIRM);
        //场地
        localeManagerPerm.add(LocalePermType.LOCALE_APPLY);
        localeManagerPerm.add(LocalePermType.APPLY_CHECK);
        localeManagerPerm.add(LocalePermType.APPLY_FIRST_CHECK);

    }


    public void init() {

        // 初始化权限
        Map<String, String> initPermMap = new HashMap<>(16);
        PermBOBuilder permBuilder = PermBOBuilder.getInstance();

        //活动权限
        for (PermType permType : ActivityPermTypeEnum.values()) {
            if (permType.isInit()) {
                permBuilder.withPermType(permType.getCode())
                        .withPermName(permType.getDesc());
                initPermMap.put(permType.getCode(), permRepoService.initPerm(permBuilder.build()).getPermId());
            }
        }

        //物资权限
        for (PermType permType : AssetPermTypeEnum.values()) {
            if (permType.isInit()) {
                permBuilder.withPermType(permType.getCode())
                        .withPermName(permType.getDesc());
                initPermMap.put(permType.getCode(), permRepoService.initPerm(permBuilder.build()).getPermId());
            }
        }

        //场地权限
        for (PermType permType : LocalePermTypeEnum.values()) {
            if (permType.isInit()) {
                permBuilder.withPermType(permType.getCode())
                        .withPermName(permType.getDesc());
                initPermMap.put(permType.getCode(), permRepoService.initPerm(permBuilder.build()).getPermId());
            }
        }

        //财务权限
        List<OrganizationBO> organizationBOList = organizationManager.queryAllOrganization();
        for (OrganizationBO organizationBO : organizationBOList) {
            PermType permType = FinancePermTypeEnum.FINANCE_MANAGE;
            Map<String, String> map = new HashMap<>(16);
            map.put(FinancePermExInfoKey.FINANCE_ORGANIZATION_ID, organizationBO.getOrganizationId());
            permBuilder.withPermType(permType.getCode())
                    .withPermName(permType.getDesc())
                    .withExtInfo(map);
            initPermMap.put(permType.getCode(), permRepoService.initFinancePerm(permBuilder.build()).getPermId());
        }
        permBuilder.withPermType(FinancePermTypeEnum.FINANCE_BAN.getCode())
                .withPermName(FinancePermTypeEnum.FINANCE_BAN.getDesc())
                .withExtInfo(new HashMap<>(0));
        initPermMap.put(FinancePermTypeEnum.FINANCE_BAN.getCode(), permRepoService.initPerm(permBuilder.build()).getPermId());

        //组织权限
        for (PermType permType : OrganizationPermTypeEnum.values()) {
            if (permType.isInit()) {
                permBuilder.withPermType(permType.getCode())
                        .withPermName(permType.getDesc());
                initPermMap.put(permType.getCode(), permRepoService.initPerm(permBuilder.build()).getPermId());
            }
        }

        //证书权限
        for (PermType permType : CertificatePermTypeEnum.values()) {
            if (permType.isInit()) {
                permBuilder.withPermType(permType.getCode())
                        .withPermName(permType.getDesc());
                initPermMap.put(permType.getCode(), permRepoService.initPerm(permBuilder.build()).getPermId());
            }
        }

        // 初始化角色
        Map<String, String> initRoleMap = new HashMap<>(16);
        RoleBOBuilder roleBOBuilder = RoleBOBuilder.getInstance();
        for (RoleCode roleCode : UserRoleCode.values()) {
            roleBOBuilder.withRoleCode(roleCode.getCode())
                    .withRoleName(roleCode.getDesc());
            RoleBO role = roleRepoService.initRole(roleBOBuilder.build());
            intRoleBindPerm(role, initPermMap);
        }
        // 初始化财务统计
        for (OrganizationBO organizationBO : organizationBOList) {
            FinanceTotalBO financeTotalBO = new FinanceTotalBO();
            financeTotalBO.setOrganizationId(organizationBO.getOrganizationId());
            financeTotalBO.setOrganizationName(organizationBO.getOrganizationName());
            financeTotalBO.setTotalMoney(BigDecimal.ZERO);
            financeTotalBO.setTotalMoneyIncludeBudget(BigDecimal.ZERO);
            financeManager.initTotalMoney(financeTotalBO);
        }
    }


    private void intRoleBindPerm(RoleBO role, Map<String, String> initPermMap) {

        // 初始化 活动管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.ACTIVITY_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            activityManagerPerm.forEach(activityPermType -> permIds.add(initPermMap.get(activityPermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }
        // 初始化 活动管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.PARTY_ACTIVITY_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            activityManagerPerm.forEach(activityPermType -> permIds.add(initPermMap.get(activityPermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }
        // 初始化 活动管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.PRACTICE_ACTIVITY_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            activityManagerPerm.forEach(activityPermType -> permIds.add(initPermMap.get(activityPermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }
        // 初始化 活动管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.VOLUNTEER_ACTIVITY_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            activityManagerPerm.forEach(activityPermType -> permIds.add(initPermMap.get(activityPermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }
        // 初始化 活动管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.VOLUNTEER_WORK_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            activityManagerPerm.forEach(activityPermType -> permIds.add(initPermMap.get(activityPermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }
        //初始化 物资管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.ASSET_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            assetManagerPerm.forEach(assetPermType -> permIds.add(initPermMap.get(assetPermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }

        // 初始化 组织管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.ORGANIZATION_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            organizationManagerPerm.forEach(organizationPermType -> permIds.add(initPermMap.get(organizationPermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }
        //初始化 证书管理员权限
        if (StringUtils.equals((role.getRoleCode()), UserRoleCode.CERTIFICATE_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            certificateManagerPerm.forEach(certificatePermType -> permIds.add(initPermMap.get(certificatePermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }
        //初始化 证书审核员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.CERTIFICATE_CONFIRM.getCode())) {
            Set<String> permId = new HashSet<>();
            certificateConfirmPerm.forEach(certificatePermType -> permId.add(initPermMap.get(certificatePermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permId));
        }
        //初始化 团委管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.LEAGUE_MANAGER.getCode())) {
            Set<String> permId = new HashSet<>();
            localeManagerPerm.forEach(localePermType -> permId.add(initPermMap.get(localePermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permId));
        }
        //初始化 学工部管理员权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.LEARNING_MANAGER.getCode())) {
            Set<String> permId = new HashSet<>();
            localeManagerPerm.forEach(localePermType -> permId.add(initPermMap.get(localePermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permId));
        }
        //初始化 场地申请人权限
        if (StringUtils.equals(role.getRoleCode(), UserRoleCode.LOCALE_MEMBER.getCode())) {
            Set<String> permId = new HashSet<>();
            localeManagerPerm.forEach(localePermType -> permId.add(initPermMap.get(localePermType)));
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permId));
        }
    }
}

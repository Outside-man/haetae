/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.common.init;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.activity.model.basic.OrganizationBO;
import us.betahouse.haetae.finance.manager.FinanceManager;
import us.betahouse.haetae.finance.model.basic.FinanceTotalBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.serviceimpl.activity.enums.ActivityPermTypeEnum;
import us.betahouse.haetae.serviceimpl.asset.constant.AssetPermType;
import us.betahouse.haetae.serviceimpl.asset.enums.AssetPermTypeEnum;
import us.betahouse.haetae.serviceimpl.finance.constant.FinancePermExInfoKey;
import us.betahouse.haetae.serviceimpl.finance.enums.FinancePermTypeEnum;
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

    // TODO @dango.yxm 使用新模块
//    @Autowired
//    private OrganizationRepoService organizationRepoService;

    @Autowired
    private FinanceManager financeManager;

    private final static List<String> activityManagerPerm = new ArrayList<>();

    private final static List<String> assetManagerPerm = new ArrayList<>();

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
        for(PermType permType : AssetPermTypeEnum.values()){
            if(permType.isInit()){
                permBuilder.withPermType(permType.getCode())
                        .withPermName(permType.getDesc());
                initPermMap.put(permType.getCode(), permRepoService.initPerm(permBuilder.build()).getPermId());
            }
        }
            //财务权限
//        List<OrganizationBO> organizationBOList=organizationRepoService.queryAllOrganization();
//        for(OrganizationBO organizationBO:organizationBOList){
//            PermType permType=FinancePermTypeEnum.FINANCE_MANAGE;
//            Map<String,String> map= new HashMap<>(16);
//            map.put(FinancePermExInfoKey.ORGANIZATION_ID, organizationBO.getOrganizationId());
//            permBuilder.withPermType(permType.getCode())
//                    .withPermName(permType.getDesc())
//                    .withExtInfo(map);
//            initPermMap.put(permType.getCode(), permRepoService.initFinancePerm(permBuilder.build()).getPermId());
//        }
//        permBuilder.withPermType(FinancePermTypeEnum.FINANCE_BAN.getCode())
//                .withPermName(FinancePermTypeEnum.FINANCE_BAN.getDesc())
//                .withExtInfo(new HashMap<>(0));
//        initPermMap.put(FinancePermTypeEnum.FINANCE_BAN.getCode(),permRepoService.initPerm(permBuilder.build()).getPermId());
        // 初始化角色
        Map<String, String> initRoleMap = new HashMap<>(16);
        RoleBOBuilder roleBOBuilder = RoleBOBuilder.getInstance();
//        for (RoleCode roleCode : UserRoleCode.values()) {
//            roleBOBuilder.withRoleCode(roleCode.getCode())
//                    .withRoleName(roleCode.getDesc());
//            RoleBO role = roleRepoService.initRole(roleBOBuilder.build());
//            intRoleBindPerm(role, initPermMap);
//        }
        //初始化财务统计
//        for(OrganizationBO organizationBO:organizationBOList){
//            FinanceTotalBO financeTotalBO=new FinanceTotalBO();
//            financeTotalBO.setOrganizationId(organizationBO.getOrganizationId());
//            financeTotalBO.setOrganizationName(organizationBO.getOrganizationName());
//            financeTotalBO.setTotalMoney(BigDecimal.ZERO);
//            financeTotalBO.setTotalMoneyIncludeBudget(BigDecimal.ZERO);
//            financeManager.initTotalMoney(financeTotalBO);
//        }
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

        //初始化 物资管理员权限
        if(StringUtils.equals(role.getRoleCode(), UserRoleCode.ASSET_MANAGER.getCode())) {
            Set<String> permIds = new HashSet<>();
            assetManagerPerm.forEach(assetPermType -> {
                permIds.add(initPermMap.get(assetPermType));
            });
            permRepoService.roleBindPerms(role.getRoleId(), new ArrayList<>(permIds));
        }
    }
}

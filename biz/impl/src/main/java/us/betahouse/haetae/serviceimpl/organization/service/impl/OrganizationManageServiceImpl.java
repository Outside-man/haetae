/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.organization.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.organization.manager.OrganizationManager;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermExInfoKey;
import us.betahouse.haetae.serviceimpl.activity.constant.organization.OrganizationExtInfoKey;
import us.betahouse.haetae.serviceimpl.activity.constant.organization.OrganizationPermExInfoKey;
import us.betahouse.haetae.serviceimpl.activity.constant.organization.OrganizationPermType;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;
import us.betahouse.haetae.serviceimpl.organization.service.OrganizationManageService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.request.PermManageRequest;
import us.betahouse.haetae.user.user.builder.PermBOBuilder;
import us.betahouse.util.utils.AssertUtil;

import java.util.Collections;

/**
 * 组织管理服务实现
 *
 * @author dango.yxm
 * @version : OrganizationManageServiceImpl.java 2019/03/26 22:49 dango.yxm
 */
@Service
public class OrganizationManageServiceImpl implements OrganizationManageService {

    @Autowired
    private OrganizationManager organizationManager;

    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Autowired
    private PermManager permManager;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrganizationBO create(OrganizationRequest request) {
        // 学号转换成用户id
        if (StringUtils.isNotBlank(request.getStuId())) {
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(request.getStuId());
            AssertUtil.assertNotNull(userInfoBO, "组织指定主管用户不存在");
            request.setMemberId(userInfoBO.getUserId());
        }
        // 创建组织领域相关信息
        OrganizationBO organizationBO = organizationManager.createOrganization(request);

        // 创建成员管理权限
        PermBO memberManagePerm = PermBOBuilder.getInstance(OrganizationPermType.ORG_MEMBER_MANAGE, organizationBO.getOrganizationName() + "_成员管理权限").build();
        // 将组织id 保存到拓展信息
        memberManagePerm.putExtInfo(OrganizationPermExInfoKey.ORGANIZATION_ID, organizationBO.getOrganizationId());
        // 构建请求
        PermManageRequest memberPermRequest = new PermManageRequest();
        memberPermRequest.setPermBO(memberManagePerm);
        // 如果指定主管直接绑定权限
        if(StringUtils.isNotBlank(request.getMemberId())){
            memberPermRequest.setUserIds(Collections.singletonList(request.getMemberId()));
        }
        memberManagePerm = permManager.createPerm(memberPermRequest);

        // 创建成员身份管理权限
        PermBO memberTypeManagePerm = PermBOBuilder.getInstance(OrganizationPermType.ORG_MEMBER_TYPE_MANAGE, organizationBO.getOrganizationName() + "_成员身份管理权限").build();
        // 将组织id 保存到拓展信息
        memberTypeManagePerm.putExtInfo(OrganizationPermExInfoKey.ORGANIZATION_ID, organizationBO.getOrganizationId());
        // 构建请求
        PermManageRequest memberTypePermRequest = new PermManageRequest();
        memberTypePermRequest.setPermBO(memberTypeManagePerm);
        // 如果指定主管直接绑定权限
        if(StringUtils.isNotBlank(request.getMemberId())){
            memberTypePermRequest.setUserIds(Collections.singletonList(request.getMemberId()));
        }
        memberTypeManagePerm = permManager.createPerm(memberTypePermRequest);

        // 更新组织信息 拓展信息添加权限
        organizationBO.putExtInfo(OrganizationExtInfoKey.ORG_MEMBER_MANAGE_PERM, memberManagePerm.getPermId());
        organizationBO.putExtInfo(OrganizationExtInfoKey.ORG_MEMBER_TYPE_MANAGE_PERM, memberTypeManagePerm.getPermId());
        organizationRepoService.modify(organizationBO);
        return organizationBO;
    }
}
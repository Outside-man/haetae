/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.organization.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.organization.dal.service.OrganizationMemberRepoService;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.organization.manager.OrganizationManager;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.organization.model.OrganizationMemberBO;
import us.betahouse.haetae.serviceimpl.organization.constant.OrganizationExtInfoKey;
import us.betahouse.haetae.serviceimpl.organization.constant.OrganizationPermExInfoKey;
import us.betahouse.haetae.serviceimpl.organization.constant.OrganizationPermType;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;
import us.betahouse.haetae.serviceimpl.organization.service.OrganizationService;
import us.betahouse.haetae.user.dal.service.PermRepoService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.manager.PermManager;
import us.betahouse.haetae.user.manager.UserManager;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.model.basic.perm.PermBO;
import us.betahouse.haetae.user.request.PermManageRequest;
import us.betahouse.haetae.user.request.UserManageRequest;
import us.betahouse.haetae.user.user.builder.PermBOBuilder;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 组织管理服务实现
 *
 * @author dango.yxm
 * @version : OrganizationServiceImpl.java 2019/03/26 22:49 dango.yxm
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationManager organizationManager;

    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Autowired
    private OrganizationMemberRepoService organizationMemberRepoService;

    @Autowired
    private PermManager permManager;

    @Autowired
    private PermRepoService permRepoService;

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrganizationBO create(OrganizationRequest request) {
        // 学号转换成用户id
        if (StringUtils.isBlank(request.getMemberId()) && StringUtils.isNotBlank(request.getStuId())) {
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
        memberManagePerm = permManager.createPerm(memberPermRequest);

        // 创建成员身份管理权限
        PermBO memberTypeManagePerm = PermBOBuilder.getInstance(OrganizationPermType.ORG_MEMBER_TYPE_MANAGE, organizationBO.getOrganizationName() + "_成员身份管理权限").build();
        // 将组织id 保存到拓展信息
        memberTypeManagePerm.putExtInfo(OrganizationPermExInfoKey.ORGANIZATION_ID, organizationBO.getOrganizationId());
        // 构建请求
        PermManageRequest memberTypePermRequest = new PermManageRequest();
        memberTypePermRequest.setPermBO(memberTypeManagePerm);
        memberTypeManagePerm = permManager.createPerm(memberTypePermRequest);

        // 如果指定主管直接绑定权限
        if (StringUtils.isNotBlank(request.getMemberId())) {
            UserManageRequest principalPermRequest = new UserManageRequest();
            principalPermRequest.setPermIds(Arrays.asList(memberManagePerm.getPermId(), memberTypeManagePerm.getPermId()));
            principalPermRequest.setUserId(request.getMemberId());
            userManager.batchBindPerm(principalPermRequest);
        }

        // 更新组织信息 拓展信息添加权限
        organizationBO.putExtInfo(OrganizationExtInfoKey.ORG_MEMBER_MANAGE_PERM, memberManagePerm.getPermId());
        organizationBO.putExtInfo(OrganizationExtInfoKey.ORG_MEMBER_TYPE_MANAGE_PERM, memberTypeManagePerm.getPermId());
        organizationRepoService.modify(organizationBO);
        return organizationBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void memberManage(OrganizationRequest request) {
        // 验证用户
        if (StringUtils.isBlank(request.getMemberId())) {
            AssertUtil.assertStringNotBlank(request.getStuId());
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(request.getStuId());
            AssertUtil.assertNotNull(userInfoBO, "用户不存在");
            request.setMemberId(userInfoBO.getUserId());
        }

        // 验证组织
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationId(request.getOrganizationId());
        AssertUtil.assertNotNull(organizationBO, "组织不存在");

        // type 分发
        AssertUtil.assertNotNull(request.getMemberType(), "管理成员时未指定身份");
        // 组织对应权限id
        String orgMemberManagePermId = organizationBO.fetchExtInfo(OrganizationExtInfoKey.ORG_MEMBER_MANAGE_PERM);
        String orgMemberTypeManagePermId = organizationBO.fetchExtInfo(OrganizationExtInfoKey.ORG_MEMBER_TYPE_MANAGE_PERM);

        switch (request.getMemberType()) {
            case PRINCIPAL:
                // 移除原主管权限 赋予新主管
                OrganizationMemberBO principal = organizationMemberRepoService.queryPrincipal(request.getOrganizationId());
                UserManageRequest principalPermRequest = new UserManageRequest();
                principalPermRequest.setPermIds(Arrays.asList(orgMemberManagePermId, orgMemberTypeManagePermId));

                // 移除原主管权限
                principalPermRequest.setUserId(principal.getMemberId());
                userManager.batchUnbindPerm(principalPermRequest);
                // 给新主管添加权限
                principalPermRequest.setUserId(request.getMemberId());
                userManager.batchBindPerm(principalPermRequest);
                break;
            case ADMIN:
                // 赋予管理员权限
                UserManageRequest adminPermRequest = new UserManageRequest();
                adminPermRequest.setPermIds(Collections.singletonList(orgMemberManagePermId));
                adminPermRequest.setUserId(request.getMemberId());
                userManager.batchBindPerm(adminPermRequest);
                break;
            case MEMBER:
                // 无权限操作
                break;
            default:
                throw new BetahouseException("非法成员类型");
        }
        // 处理成员表
        organizationManager.manageMember(request);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeMember(OrganizationRequest request) {
        // 验证用户
        if (StringUtils.isBlank(request.getMemberId())) {
            AssertUtil.assertStringNotBlank(request.getStuId());
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(request.getStuId());
            AssertUtil.assertNotNull(userInfoBO, "用户不存在");
            request.setMemberId(userInfoBO.getUserId());
        }

        // 验证组织
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationId(request.getOrganizationId());
        AssertUtil.assertNotNull(organizationBO, "组织不存在");

        // 组织对应权限id
        String orgMemberManagePermId = organizationBO.fetchExtInfo(OrganizationExtInfoKey.ORG_MEMBER_MANAGE_PERM);
        String orgMemberTypeManagePermId = organizationBO.fetchExtInfo(OrganizationExtInfoKey.ORG_MEMBER_TYPE_MANAGE_PERM);

        OrganizationMemberBO member = organizationMemberRepoService.queryMember(request.getOrganizationId(), request.getMemberId());

        // 成员存在
        if (member != null) {
            // 移除所有权限
            UserManageRequest PermRequest = new UserManageRequest();
            PermRequest.setPermIds(Arrays.asList(orgMemberManagePermId, orgMemberTypeManagePermId));
            PermRequest.setUserId(member.getMemberId());
            userManager.batchUnbindPerm(PermRequest);

            organizationMemberRepoService.removeMember(request.getOrganizationId(), request.getMemberId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disband(OrganizationRequest request) {
        // 验证组织
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationId(request.getOrganizationId());
        AssertUtil.assertNotNull(organizationBO, "组织不存在");

        // 组织对应权限id
        String orgMemberManagePermId = organizationBO.fetchExtInfo(OrganizationExtInfoKey.ORG_MEMBER_MANAGE_PERM);
        String orgMemberTypeManagePermId = organizationBO.fetchExtInfo(OrganizationExtInfoKey.ORG_MEMBER_TYPE_MANAGE_PERM);

        // 跟所有人解绑权限
        permManager.detachAllUsers(orgMemberManagePermId);
        permManager.detachAllUsers(orgMemberTypeManagePermId);
        // 删除权限
        permRepoService.deletePerm(orgMemberManagePermId);
        permRepoService.deletePerm(orgMemberTypeManagePermId);
        // 删除组织
        organizationManager.disbandOrganization(request);
    }

    @Override
    public List<OrganizationMemberBO> queryOrganizationMemberByMemberId(OrganizationRequest request) {
        organizationManager.queryOrganizationMemberByMemberId(request.getMemberId()).forEach(System.out::println);
        return organizationManager.queryOrganizationMemberByMemberId(request.getMemberId());
    }
}
/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.organization.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.organization.dal.service.OrganizationMemberRepoService;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.organization.enums.MemberType;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.organization.model.OrganizationMemberBO;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyPerm;
import us.betahouse.haetae.serviceimpl.organization.constant.OrganizationExtInfoKey;
import us.betahouse.haetae.serviceimpl.organization.constant.OrganizationPermType;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;
import us.betahouse.haetae.serviceimpl.organization.service.OrganizationManagerService;
import us.betahouse.haetae.serviceimpl.organization.service.OrganizationService;
import us.betahouse.haetae.user.dal.service.UserInfoRepoService;
import us.betahouse.haetae.user.model.basic.UserInfoBO;
import us.betahouse.haetae.user.user.service.UserBasicService;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.utils.AssertUtil;

import java.util.Collections;

/**
 * 组织管理器实现
 *
 * @author dango.yxm
 * @version : OrganizationManagerServiceImpl.java 2019/03/27 22:02 dango.yxm
 */
@Service
public class OrganizationManagerServiceImpl implements OrganizationManagerService {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Autowired
    private OrganizationMemberRepoService organizationMemberRepoService;

    @Autowired
    private UserBasicService userBasicService;

    @Autowired
    private UserInfoRepoService userInfoRepoService;

    @Override
    @VerifyPerm(permType = OrganizationPermType.ALL_ORG_MANAGE)
    public OrganizationBO create(OrganizationRequest request) {
        return organizationService.create(request);
    }

    @Override
    @VerifyPerm(permType = OrganizationPermType.ALL_ORG_MANAGE)
    public void disband(OrganizationRequest request) {
        organizationService.disband(request);
    }

    @Override
    @VerifyPerm(permType = OrganizationPermType.ALL_ORG_MEMBER_MANAGE)
    public void manageMember(OrganizationRequest request) {
        organizationService.memberManage(request);
    }

    @Override
    public void changeMemberType(OrganizationRequest request) {
        AssertUtil.assertTrue(verifyMemberTypePerm(request), CommonResultCode.FORBIDDEN, "没有该组织的身份管理权限");
        organizationService.memberManage(request);
    }

    @Override
    public void addMember(OrganizationRequest request) {
        // 防止未填充
        if (request.getMemberType() == null) {
            request.setMemberType(MemberType.MEMBER);
        }
        // 如果是只是添加成员 校验成员管理权限
        if (MemberType.MEMBER == request.getMemberType()) {
            AssertUtil.assertTrue(verifyMemberPerm(request), CommonResultCode.FORBIDDEN, "没有该组织的成员管理权限");
        } else {
            AssertUtil.assertTrue(verifyMemberTypePerm(request), CommonResultCode.FORBIDDEN, "没有该组织的身份管理权限");
        }
        organizationService.memberManage(request);
    }

    @Override
    public void removeMember(OrganizationRequest request) {
        // 验证用户
        if (StringUtils.isBlank(request.getMemberId())) {
            AssertUtil.assertStringNotBlank(request.getStuId());
            UserInfoBO userInfoBO = userInfoRepoService.queryUserInfoByStuId(request.getStuId());
            AssertUtil.assertNotNull(userInfoBO, "用户不存在");
            request.setMemberId(userInfoBO.getUserId());
        }
        OrganizationMemberBO member = organizationMemberRepoService.queryMember(request.getOrganizationId(), request.getMemberId());

        // 存在用户则移除
        if (member != null) {
            MemberType memberType = MemberType.getByType(member.getMemberType());
            AssertUtil.assertNotNull(memberType, "管理用户类型为空, 请联系管理员");
            // 如果是只是添加成员 校验成员管理权限
            if (MemberType.MEMBER == request.getMemberType()) {
                AssertUtil.assertTrue(verifyMemberPerm(request), CommonResultCode.FORBIDDEN, "没有该组织的成员管理权限");
            } else {
                AssertUtil.assertTrue(verifyMemberTypePerm(request), CommonResultCode.FORBIDDEN, "没有该组织的身份管理权限");
            }
            organizationService.removeMember(request);
        }
    }

    /**
     * 校验成员管理权限
     *
     * @param request
     * @return
     */
    private boolean verifyMemberPerm(OrganizationRequest request) {
        // 验证组织
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationId(request.getOrganizationId());
        AssertUtil.assertNotNull(organizationBO, "组织不存在");
        // 获取权限
        String memberPermId = organizationBO.fetchExtInfo(OrganizationExtInfoKey.ORG_MEMBER_MANAGE_PERM);
        AssertUtil.assertStringNotBlank(memberPermId, "组织没有成员管理权限");
        return userBasicService.verifyPermissionByPermId(request.getVerifyUserId(), Collections.singletonList(memberPermId));
    }

    /**
     * 校验身份管理权限
     *
     * @param request
     * @return
     */
    private boolean verifyMemberTypePerm(OrganizationRequest request) {
        // 验证组织
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationId(request.getOrganizationId());
        AssertUtil.assertNotNull(organizationBO, "组织不存在");
        // 获取权限
        String memberTypePermId = organizationBO.fetchExtInfo(OrganizationExtInfoKey.ORG_MEMBER_TYPE_MANAGE_PERM);
        AssertUtil.assertStringNotBlank(memberTypePermId, "组织没有成员身份管理权限");
        return userBasicService.verifyPermissionByPermId(request.getVerifyUserId(), Collections.singletonList(memberTypePermId));
    }
}
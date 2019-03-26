/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.manager.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.organization.dal.service.OrganizationMemberRepoService;
import us.betahouse.haetae.organization.dal.service.OrganizationRelationRepoService;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.organization.enums.MemberType;
import us.betahouse.haetae.organization.manager.OrganizationManager;
import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.organization.model.OrganizationMemberBO;
import us.betahouse.haetae.organization.model.OrganizationRelationBO;
import us.betahouse.haetae.organization.request.OrganizationManageRequest;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.LoggerUtil;

import java.util.List;

/**
 * 组织管理器实现
 *
 * @author dango.yxm
 * @version : OrganizationManagerImpl.java 2019/03/25 23:52 dango.yxm
 */
@Service
public class OrganizationManagerImpl implements OrganizationManager {

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationManagerImpl.class);

    private final int PRINCIPAL_TOTAL = 1;

    private final int FIRST = 0;


    @Autowired
    private OrganizationRepoService organizationRepoService;

    @Autowired
    private OrganizationMemberRepoService organizationMemberRepoService;

    @Autowired
    private OrganizationRelationRepoService organizationRelationRepoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrganizationBO createOrganization(OrganizationManageRequest request) {
        OrganizationBO organizationBO = new OrganizationBO();
        organizationBO.setOrganizationName(request.getOrganizationName());
        organizationBO.setOrganizationType(request.getOrganizationType());
        organizationBO = organizationRepoService.create(organizationBO);

        // 创建主组织关系
        if (StringUtils.isNotBlank(request.getPrimaryOrganizationId())) {
            OrganizationRelationBO relationBO = new OrganizationRelationBO();
            relationBO.setPrimaryOrganizationId(request.getPrimaryOrganizationId());
            relationBO.setSubOrganizationId(organizationBO.getOrganizationId());
            organizationRelationRepoService.createRelation(relationBO);
        }

        // 创建主管关系
        if (StringUtils.isNotBlank(request.getMemberId())) {
            // 防止未设置
            request.setMemberType(MemberType.PRINCIPAL);
            OrganizationMemberBO memberBO = parseMember(request);
            organizationMemberRepoService.addMember(memberBO);
        }
        return organizationBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void manageMember(OrganizationManageRequest request) {
        if (request.getMemberType() == MemberType.PRINCIPAL) {
            // 如果是主管变更 需要涉及特殊逻辑
            changePrincipal(request);
        } else {
            // 成员和管理员直接操作就行
            saveMember(parseMember(request));
        }
    }

    /**
     * 主管变更 需要把原有主管变为成员
     *
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    private void changePrincipal(OrganizationManageRequest request) {
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationId(request.getOrganizationId());
        if (organizationBO == null) {
            LoggerUtil.warn(LOGGER, "组织不存在 OrganizationId={0}", request.getOrganizationId());
            throw new BetahouseException("组织不存在");
        }
        List<OrganizationMemberBO> principals = organizationMemberRepoService.queryMembers(request.getOrganizationId(), MemberType.PRINCIPAL.getType());

        // 有主管时 需要将原来主管改成普通成员
        if (!CollectionUtils.isEmpty(principals)) {
            // 主管超出指定数量 认为系统存在异常
            if (principals.size() > PRINCIPAL_TOTAL) {
                LoggerUtil.error(LOGGER, "{0}组织主管超出限制, 组织id={1}, 主管人数={2}",
                        organizationBO.getOrganizationName(), organizationBO.getOrganizationId(), principals.size());
            }
            // 已经是主管就不需要重复设置
            for (OrganizationMemberBO memberBO : principals) {
                if (StringUtils.equals(memberBO.getMemberId(), request.getMemberId())) {
                    LoggerUtil.warn(LOGGER, "已是主管");
                    return;
                }
            }
            // 未指定新称呼 沿用老称呼
            if (StringUtils.isBlank(request.getMemberDesc())) {
                request.setMemberDesc(principals.get(FIRST).getMemberDescription());
            }
            principals.forEach(principal -> {
                principal.setMemberType(MemberType.MEMBER.getType());
                principal.setMemberDescription(MemberType.MEMBER.getDesc());
                organizationMemberRepoService.updateMember(principal);
            });
        }
        saveMember(parseMember(request));
    }

    /**
     * 保存成员关系  无则创  有则改
     * @param memberBO
     */
    private void saveMember(OrganizationMemberBO memberBO) {
        OrganizationMemberBO preMemberBO = organizationMemberRepoService.queryMember(memberBO.getOrganizationId(), memberBO.getMemberId());
        if (preMemberBO == null) {
            organizationMemberRepoService.addMember(memberBO);
        } else {
            memberBO.setOrganizationMemberId(preMemberBO.getOrganizationMemberId());
            organizationMemberRepoService.updateMember(memberBO);
        }
    }


    /**
     * 解析成员信息
     *
     * @param request
     * @return
     */
    private OrganizationMemberBO parseMember(OrganizationManageRequest request) {
        OrganizationMemberBO memberBO = new OrganizationMemberBO();
        memberBO.setOrganizationId(request.getOrganizationId());
        memberBO.setMemberId(request.getMemberId());
        memberBO.setMemberType(request.getMemberType().getType());
        // 是否传入称呼描述
        if (StringUtils.isNotBlank(request.getMemberDesc())) {
            memberBO.setMemberDescription(request.getMemberDesc());
        } else {
            // 使用默认
            memberBO.setMemberDescription(request.getMemberType().getDesc());
        }
        return memberBO;
    }
}
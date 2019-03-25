/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.manager.impl;

import org.apache.commons.lang.StringUtils;
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

/**
 * 组织管理器实现
 *
 * @author dango.yxm
 * @version : OrganizationManagerImpl.java 2019/03/25 23:52 dango.yxm
 */
@Service
public class OrganizationManagerImpl implements OrganizationManager {

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
        if (StringUtils.isNotBlank(request.getPrinipalId())) {
            OrganizationMemberBO memberBO = new OrganizationMemberBO();
            memberBO.setOrganizationId(organizationBO.getOrganizationId());
            memberBO.setMemberId(request.getPrinipalId());
            memberBO.setMemberType(MemberType.PRINCIPAL.getType());
            // 是否传入称呼描述
            if (StringUtils.isNotBlank(request.getMemberDesc())) {
                memberBO.setMemberDescription(request.getMemberDesc());
            } else {
                memberBO.setMemberDescription(MemberType.PRINCIPAL.getDesc());
            }
            organizationMemberRepoService.addMember(memberBO);
        }
        return organizationBO;
    }
}
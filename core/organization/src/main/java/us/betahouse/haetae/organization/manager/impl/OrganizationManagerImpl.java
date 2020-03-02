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
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 组织管理器实现
 *
 * @author dango.yxm
 * @version : OrganizationManagerImpl.java 2019/03/25 23:52 dango.yxm
 */
@Service
public class OrganizationManagerImpl implements OrganizationManager {

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationManagerImpl.class);

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
        //如果存在组织id则加入
        if(StringUtils.isNotBlank(request.getOrganizationId())){
            organizationBO.setOrganizationId(request.getOrganizationId());
        }
        organizationBO = organizationRepoService.create(organizationBO);
        request.setOrganizationId(organizationBO.getOrganizationId());

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
    public void disbandOrganization(OrganizationManageRequest request) {
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationId(request.getOrganizationId());
        if (organizationBO != null) {
            // 解除所有成员关系
            organizationMemberRepoService.disband(organizationBO.getOrganizationId());
            // 解除所有相关组织关系
            organizationRelationRepoService.disband(organizationBO.getOrganizationId());
            // 删除组织
            organizationRepoService.disband(organizationBO.getOrganizationId());
        }
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

    @Override
    public List<OrganizationBO> queryOrganizationByMemberId(String memberId) {
        List<String> organizationIds = CollectionUtils.toStream(organizationMemberRepoService.queryOrganizations(memberId))
                .filter(Objects::nonNull)
                .map(OrganizationMemberBO::getOrganizationId)
                .collect(Collectors.toList());
        return organizationRepoService.queryByOrganizationIds(organizationIds);
    }

    @Override
    public List<OrganizationMemberBO> queryOrganizationMemberByMemberId(String memberId) {
        return CollectionUtils.toStream(organizationMemberRepoService.queryOrganizations(memberId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrganizationBO> queryAllOrganization() {
        return CollectionUtils.toStream(organizationRepoService.queryAllOrganization())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 主管变更 需要把原有主管变为成员
     *
     * @param request
     */
    @Transactional(rollbackFor = Exception.class)
    void changePrincipal(OrganizationManageRequest request) {
        OrganizationBO organizationBO = organizationRepoService.queryByOrganizationId(request.getOrganizationId());
        if (organizationBO == null) {
            LoggerUtil.warn(LOGGER, "组织不存在 OrganizationId={0}", request.getOrganizationId());
            throw new BetahouseException("组织不存在");
        }
        // 获取主管
        OrganizationMemberBO principal = organizationMemberRepoService.queryPrincipal(request.getOrganizationId());
        // 有主管时 需要将原来主管改成普通成员
        if (principal != null) {
            // 已经是主管就不需要重复设置
            if (StringUtils.equals(principal.getMemberId(), request.getMemberId())) {
                LoggerUtil.warn(LOGGER, "已是主管");
                return;
            }
            // 未指定新称呼 沿用老称呼
            if (StringUtils.isBlank(request.getMemberDesc())) {
                request.setMemberDesc(principal.getMemberDescription());
            }
            // 原来主管改成普通成员
            principal.setMemberType(MemberType.MEMBER.getType());
            principal.setMemberDescription(MemberType.MEMBER.getDesc());
            organizationMemberRepoService.updateMember(principal);
        }
        saveMember(parseMember(request));
    }

    /**
     * 保存成员关系  无则创  有则改
     *
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
        memberBO.setOrganizationName(organizationRepoService.queryByOrganizationId(request.getOrganizationId()).getOrganizationName());
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
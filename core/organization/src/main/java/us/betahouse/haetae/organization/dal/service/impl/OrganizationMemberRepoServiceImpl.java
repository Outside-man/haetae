/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.organization.dal.convert.EntityConverter;
import us.betahouse.haetae.organization.dal.model.OrganizationMemberDO;
import us.betahouse.haetae.organization.dal.repo.OrganizationMemberRepo;
import us.betahouse.haetae.organization.dal.repo.OrganizationRepo;
import us.betahouse.haetae.organization.dal.service.OrganizationMemberRepoService;
import us.betahouse.haetae.organization.idfactory.BizIdFactory;
import us.betahouse.haetae.organization.model.OrganizationMemberBO;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.LoggerUtil;

import java.util.HashMap;

/**
 * 组织成员仓储服务实现
 *
 * @author dango.yxm
 * @version : OrganizationMemberRepoServiceImpl.java 2019/03/25 15:42 dango.yxm
 */
@Service
public class OrganizationMemberRepoServiceImpl implements OrganizationMemberRepoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OrganizationRepoServiceImpl.class);

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private OrganizationMemberRepo organizationMemberRepo;

    @Autowired
    private BizIdFactory organizationBizIdFactory;

    @Override
    public void addMember(OrganizationMemberBO memberBO) {
        AssertUtil.assertNotNull(memberBO);
        String organizationId = memberBO.getOrganizationId();
        String memberId = memberBO.getMemberId();
        AssertUtil.assertStringNotBlank(organizationId, "组织id不能为空");
        AssertUtil.assertStringNotBlank(memberId, "成员id不能为空");

        OrganizationMemberDO memberDO = organizationMemberRepo.findByOrganizationAndMemberId(organizationId, memberId);
        if (memberDO != null) {
            LoggerUtil.warn(LOGGER, "重复加入组织 memberDO={0}", memberDO);
            return;
        }
        // 未加入组织 则创建关系
        if (StringUtils.isBlank(memberBO.getOrganizationMemberId())) {
            memberBO.setOrganizationMemberId(organizationBizIdFactory.getOrganizationMemberId(organizationId, memberId));
        }
        organizationMemberRepo.save(EntityConverter.convert(memberBO));
    }

    @Override
    public void removeMember(String organizationId, String memberId) {
        AssertUtil.assertStringNotBlank(organizationId, "组织id不能为空");
        AssertUtil.assertStringNotBlank(memberId, "成员id不能为空");

        OrganizationMemberDO memberDO = organizationMemberRepo.findByOrganizationAndMemberId(organizationId, memberId);
        if (memberDO == null) {
            LoggerUtil.warn(LOGGER, "未加入组织 organizationId={0} memberId={1}", organizationId, memberId);
            return;
        }

        // 在组织内 则删除关系
        organizationMemberRepo.deleteByOrganizationAndMemberId(organizationId, memberId);

    }

    @Override
    public OrganizationMemberBO updateMember(OrganizationMemberBO memberBO) {
        AssertUtil.assertNotNull(memberBO);
        OrganizationMemberDO memberDO = null;
        if (StringUtils.isNotBlank(memberBO.getOrganizationMemberId())) {
            // 指定组织成员id获取关系
            memberDO = organizationMemberRepo.findByOrganizationMemberId(memberBO.getOrganizationMemberId());
            AssertUtil.assertNotNull(memberDO, "组织成员关系不存在");
        } else {
            // 指定组织和成员获取关系
            String organizationId = memberBO.getOrganizationId();
            String memberId = memberBO.getMemberId();
            AssertUtil.assertStringNotBlank(organizationId, "组织id不能为空");
            AssertUtil.assertStringNotBlank(memberId, "成员id不能为空");
            memberDO = organizationMemberRepo.findByOrganizationAndMemberId(organizationId, memberId);
            AssertUtil.assertNotNull(memberDO, "组织成员关系不存在");
        }
        // 修改逻辑
        OrganizationMemberDO newMemberDO = EntityConverter.convert(memberBO);
        if (newMemberDO.getMemberType() != null) {
            memberDO.setMemberType(newMemberDO.getMemberType());
        }
        if (newMemberDO.getMemberDescription() != null) {
            memberDO.setMemberDescription(newMemberDO.getMemberDescription());
        }
        // 特殊逻辑 只有extInfo == null 时认为主动清空
        if (memberBO.getExtInfo() == null) {
            memberDO.setExtInfo(JSON.toJSONString(new HashMap<>()));
        } else if (!memberBO.getExtInfo().isEmpty()) {
            memberDO.setExtInfo(newMemberDO.getExtInfo());
        }
        return EntityConverter.convert(organizationMemberRepo.save(memberDO));
    }
}
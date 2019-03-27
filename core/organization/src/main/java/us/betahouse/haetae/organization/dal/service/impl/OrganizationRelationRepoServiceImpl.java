/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import us.betahouse.haetae.organization.dal.convert.EntityConverter;
import us.betahouse.haetae.organization.dal.model.OrganizationRelationDO;
import us.betahouse.haetae.organization.dal.repo.OrganizationRelationRepo;
import us.betahouse.haetae.organization.dal.service.OrganizationRelationRepoService;
import us.betahouse.haetae.organization.idfactory.BizIdFactory;
import us.betahouse.haetae.organization.model.OrganizationRelationBO;
import us.betahouse.util.utils.AssertUtil;

/**
 * 组织关系仓储服务实现
 *
 * @author dango.yxm
 * @version : OrganizationRelationRepoServiceImpl.java 2019/03/25 23:06 dango.yxm
 */
@Service
public class OrganizationRelationRepoServiceImpl implements OrganizationRelationRepoService {

    @Autowired
    private OrganizationRelationRepo organizationRelationRepo;

    @Autowired
    private BizIdFactory organizationBizIdFactory;

    @Override
    public OrganizationRelationBO createRelation(OrganizationRelationBO relationBO) {
        AssertUtil.assertTrue(!StringUtils.equals(relationBO.getPrimaryOrganizationId(), relationBO.getSubOrganizationId()), "同组织不能生成关系");
        // 检查是否存在组织关系
        OrganizationRelationDO relationDO = organizationRelationRepo.findByPrimaryOrganizationIdAndSubOrganizationId(relationBO.getPrimaryOrganizationId(), relationBO.getSubOrganizationId());
        if (relationDO != null) {
            return EntityConverter.convert(relationDO);
        }
        // 检查是否存在相反组织关系
        relationDO = organizationRelationRepo.findByPrimaryOrganizationIdAndSubOrganizationId(relationBO.getSubOrganizationId(), relationBO.getPrimaryOrganizationId());
        AssertUtil.assertNull(relationDO, "已存在相反的组织关系");

        // 创建组织关系
        if (StringUtils.isBlank(relationBO.getOrganizationRelationId())) {
            relationBO.setOrganizationRelationId(organizationBizIdFactory.getOrganizationId());
        }
        return EntityConverter.convert(organizationRelationRepo.save(EntityConverter.convert(relationBO)));
    }

    @Override
    public void removeRelation(String primaryOrganizationId, String subOrganizationId) {
        // 检查是否存在组织关系
        OrganizationRelationDO relationDO = organizationRelationRepo.findByPrimaryOrganizationIdAndSubOrganizationId(primaryOrganizationId, subOrganizationId);
        if (relationDO != null) {
            // 存在则删除
            organizationRelationRepo.delete(relationDO);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disband(String organizationId) {
        AssertUtil.assertStringNotBlank(organizationId, "组织id不能为空");
        organizationRelationRepo.deleteByPrimaryOrganizationId(organizationId);
        organizationRelationRepo.deleteBySubOrganizationId(organizationId);
    }
}
/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service;

import us.betahouse.haetae.organization.model.OrganizationRelationBO;

/**
 * 组织关系仓储服务
 *
 * @author dango.yxm
 * @version : OrganizationRelationRepoService.java 2019/03/25 22:54 dango.yxm
 */
public interface OrganizationRelationRepoService {

    /**
     * 创建组织间关系
     *
     * @param relationBO
     * @return
     */
    OrganizationRelationBO createRelation(OrganizationRelationBO relationBO);

    /**
     * 删除组织间关系
     *
     * @param primaryOrganizationId
     * @param subOrganizationId
     * @return
     */
    void removeRelation(String primaryOrganizationId, String subOrganizationId);
}
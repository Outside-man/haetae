/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service;

import us.betahouse.haetae.organization.model.OrganizationBO;

/**
 * 组织仓储服务
 *
 * @author dango.yxm
 * @version : OrganizationRepoService.java 2019/03/25 14:33 dango.yxm
 */
public interface OrganizationRepoService {

    /**
     * 创建组织
     *
     * @param organizationBO
     * @return
     */
    OrganizationBO create(OrganizationBO organizationBO);

    /**
     * 组织修改
     *
     * @param organizationId
     * @param organizationBO
     * @return
     */
    OrganizationBO modify(String organizationId, OrganizationBO organizationBO);
}
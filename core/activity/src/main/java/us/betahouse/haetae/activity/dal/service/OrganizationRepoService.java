/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.service;

import us.betahouse.haetae.activity.model.OrganizationBO;

import java.util.List;

/**
 * 组织仓储服务
 * @author MessiahJK
 * @version : OrganizationRepoService.java 2018/11/17 20:01 MessiahJK
 */
public interface OrganizationRepoService {
    /**
     * 查询所有组织
     * @return
     */
    List<OrganizationBO> queryAllOrganization();

    /**
     * 通过组织id查询组织
     * @return
     */
    OrganizationBO queryOrganizationByOrganizationId(String organizationId);

    /**
     * 创建组织
     * @param organizationBO
     * @return
     */
    OrganizationBO createOrganization(OrganizationBO organizationBO);
}

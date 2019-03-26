/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service;

import us.betahouse.haetae.organization.model.OrganizationBO;

import java.util.List;

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
     * @param organizationBO
     * @return
     */
    OrganizationBO modify(OrganizationBO organizationBO);

    /**
     * 获取全部组织
     *
     * @return
     */
    List<OrganizationBO> queryAllOrganization();

    /**
     * 通过id获取组织
     *
     * @param organizationId
     * @return
     */
    OrganizationBO queryByOrganizationId(String organizationId);

    /**
     * 查询组织
     *
     * @param organizationId
     * @return
     */
    List<OrganizationBO> queryByOrganizationIds(List<String> organizationId);

    /**
     * 查询组织 通过组织名称
     *
     * @param organizationName
     * @return
     */
    OrganizationBO queryByOrganizationName(String organizationName);
}
/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.organization.dal.model.OrganizationRelationDO;

/**
 * 组织关系仓储
 *
 * @author dango.yxm
 * @version : OrganizationRelationRepo.java 2019/03/25 10:37 dango.yxm
 */
@Repository
public interface OrganizationRelationRepo extends JpaRepository<OrganizationRelationDO, Long> {

    /**
     * 通过主组织id和子组织id获取
     *
     * @param primaryOrganizationId
     * @param subOrganizationId
     * @return
     */
    OrganizationRelationDO findByPrimaryOrganizationIdAndSubOrganizationId(String primaryOrganizationId, String subOrganizationId);
}
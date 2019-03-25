/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.organization.dal.model.OrganizationRelationDO;

/**
 * 组织关系仓储
 *
 * @author dango.yxm
 * @version : OrganizationRelationRepo.java 2019/03/25 10:37 dango.yxm
 */
public interface OrganizationRelationRepo extends JpaRepository<OrganizationRelationDO, Long> {
}
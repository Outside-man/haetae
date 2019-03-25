/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.organization.dal.model.OrganizationDO;

/**
 * 组织仓储
 *
 * @author dango.yxm
 * @version : OrganizationRepo.java 2019/03/25 10:36 dango.yxm
 */
@Repository
public interface OrganizationRepo extends JpaRepository<OrganizationDO, Long> {
}
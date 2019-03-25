/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import us.betahouse.haetae.organization.dal.model.OrganizationMemberDO;

/**
 * 组织成员仓储
 *
 * @author dango.yxm
 * @version : OrganizationMemberRepo.java 2019/03/25 10:44 dango.yxm
 */
public interface OrganizationMemberRepo extends JpaRepository<OrganizationMemberDO, Long> {
}
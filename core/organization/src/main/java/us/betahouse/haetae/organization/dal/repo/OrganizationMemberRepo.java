/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.organization.dal.model.OrganizationMemberDO;

import java.util.List;

/**
 * 组织成员仓储
 *
 * @author dango.yxm
 * @version : OrganizationMemberRepo.java 2019/03/25 10:44 dango.yxm
 */
@Repository
public interface OrganizationMemberRepo extends JpaRepository<OrganizationMemberDO, Long> {

    /**
     * 通过组织id和用户id获取
     *
     * @param organizationId
     * @param memberId
     * @return
     */
    OrganizationMemberDO findByOrganizationAndMemberId(String organizationId, String memberId);

    /**
     * 通过组织成员id获取
     *
     * @param organizationMemberId
     * @return
     */
    OrganizationMemberDO findByOrganizationMemberId(String organizationMemberId);

    /**
     * 通过组织id和用户id删除
     *
     * @param organizationId
     * @param memberId
     */
    void deleteByOrganizationAndMemberId(String organizationId, String memberId);

}
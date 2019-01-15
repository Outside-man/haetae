/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.activity.dal.model.OrganizationDO;

/**
 * 组织实体存储
 *
 * @author MessiahJK
 * @version : OrganizationDORepo.java 2018/11/17 15:44 MessiahJK
 */
@Repository
public interface OrganizationDORepo extends JpaRepository<OrganizationDO, Long> {
    /**
     * 通过组织id获取对象
     *
     * @param organizationId
     * @return
     */
    OrganizationDO findByOrganizationId(String organizationId);


    /**
     * 通过组织名获取组织实体
     *
     * @param organizationName
     * @return
     */
    OrganizationDO findByOrganizationName(String organizationName);

}

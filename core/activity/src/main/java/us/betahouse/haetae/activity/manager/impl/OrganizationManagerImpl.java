/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.activity.dal.repo.OrganizationDORepo;
import us.betahouse.haetae.activity.dal.service.OrganizationRepoService;
import us.betahouse.haetae.activity.manager.OrganizationManager;
import us.betahouse.haetae.activity.model.OrganizationBO;

import java.util.List;

/**
 * @author MessiahJK
 * @version : OrganizationManagerImpl.java 2018/11/23 0:32 MessiahJK
 */
public class OrganizationManagerImpl implements OrganizationManager {
    @Autowired
    OrganizationRepoService organizationRepoService;
    /**
     * 查询所有组织信息
     * @return
     */
    @Override
    public List<OrganizationBO> findAll() {
        return organizationRepoService.queryAllOrganization();
    }
}

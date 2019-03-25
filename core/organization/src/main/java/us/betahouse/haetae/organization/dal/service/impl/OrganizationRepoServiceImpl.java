/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import us.betahouse.haetae.organization.dal.repo.OrganizationRepo;
import us.betahouse.haetae.organization.dal.service.OrganizationRepoService;
import us.betahouse.haetae.organization.idfactory.BizIdFactory;
import us.betahouse.haetae.organization.model.OrganizationBO;

/**
 * @author dango.yxm
 * @version : OrganizationRepoServiceImpl.java 2019/03/25 14:36 dango.yxm
 */
public class OrganizationRepoServiceImpl implements OrganizationRepoService {

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private BizIdFactory organizationBizIdFactory;

    @Override
    public OrganizationBO create(OrganizationBO organizationBO) {
        if (StringUtils.isBlank(organizationBO.getOrganizationId())) {
        }


        return null;
    }

    @Override
    public OrganizationBO modify(String organizationId, OrganizationBO organizationBO) {
        return null;
    }
}
/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.manager;

import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.organization.request.OrganizationManageRequest;

/**
 * 组织管理器
 *
 * @author dango.yxm
 * @version : OrganizationManager.java 2019/03/25 23:52 dango.yxm
 */
public interface OrganizationManager {

    /**
     * 创建组织
     *
     * @param request
     * @return
     */
    OrganizationBO createOrganization(OrganizationManageRequest request);
}
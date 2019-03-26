/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.manager;

import us.betahouse.haetae.organization.model.OrganizationBO;
import us.betahouse.haetae.organization.request.OrganizationManageRequest;

import java.util.List;

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

    /**
     * 管理成员
     *
     * @param request
     */
    void manageMember(OrganizationManageRequest request);

    /**
     * 查询用户所在的所有组织
     *
     * @param memberId
     * @return
     */
    List<OrganizationBO> queryOrganizationByMemberId(String memberId);
}
/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service;


import us.betahouse.haetae.organization.model.OrganizationMemberBO;

import java.util.List;


/**
 * 组织成员仓储服务
 *
 * @author dango.yxm
 * @version : OrganizationMemberRepoService.java 2019/03/25 15:37 dango.yxm
 */
public interface OrganizationMemberRepoService {

    /**
     * 添加成员
     *
     * @param memberBO
     */
    void addMember(OrganizationMemberBO memberBO);

    /**
     * 移除成员
     *
     * @param organizationId
     * @param memberId
     */
    void removeMember(String organizationId, String memberId);

    /**
     * 修改成员信息
     *
     * @param memberBO
     */
    OrganizationMemberBO updateMember(OrganizationMemberBO memberBO);

    /**
     * 查询成员
     *
     * @param organizationId
     * @param memberType
     * @return
     */
    List<OrganizationMemberBO> queryMembers(String organizationId, String memberType);

    /**
     * 查询主管
     *
     * @param organizationId
     * @return
     */
    OrganizationMemberBO queryPrincipal(String organizationId);

    /**
     * 查询成员
     *
     * @param organizationId
     * @param memberId
     * @return
     */
    OrganizationMemberBO queryMember(String organizationId, String memberId);

    /**
     * 查询成员组织
     *
     * @param memberId
     * @return
     */
    List<OrganizationMemberBO> queryOrganizations(String memberId);


    /**
     * 解散组织
     *
     * @param organizationId
     */
    void disband(String organizationId);
}
/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.dal.service;


import us.betahouse.haetae.organization.model.OrganizationMemberBO;


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
}
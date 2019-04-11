/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.organization.idfactory;

/**
 * 业务id生成工厂
 * 生成32位业务id
 *
 * @author dango.yxm
 * @version : BizIdFactory.java 2018/10/06 下午1:23 dango.yxm
 */
public interface BizIdFactory {

    /**
     * 生成组织id
     *
     * @return
     */
    String getOrganizationId();

    /**
     * 生成组织成员id
     *
     * @param organizationId
     * @param memberId
     * @return
     */
    String getOrganizationMemberId(String organizationId, String memberId);

    /**
     * 生成组织关系id
     *
     * @param primaryOrganizationId
     * @param subOrganizationId
     * @return
     */
    String getOrganizationRelation(String primaryOrganizationId, String subOrganizationId);

}

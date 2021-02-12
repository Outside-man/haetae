/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.organization;

import us.betahouse.haetae.common.RestRequest;

/**
 * 组织请求
 *
 * @author MessiahJK
 * @version : OrganizationRestRequest.java 2018/11/25 22:54 MessiahJK
 */
public class OrganizationRestRequest extends RestRequest {

    private static final long serialVersionUID = -4275609244754640217L;

    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 组织名
     */
    private String organizationName;

    /**
     * 组织类型
     */
    private String organizationType;

    /**
     * 成员学号
     */
    private String stuId;

    /**
     * 成员id
     */
    private String memberId;

    /**
     * 成员身份
     *
     * @see us.betahouse.haetae.organization.enums.MemberType
     */
    private String memberType;

    /**
     * 成员称呼
     */
    private String memberDesc;

    /**
     * 主组织id
     */
    private String primaryOrganizationId;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberDesc() {
        return memberDesc;
    }

    public void setMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
    }

    public String getPrimaryOrganizationId() {
        return primaryOrganizationId;
    }

    public void setPrimaryOrganizationId(String primaryOrganizationId) {
        this.primaryOrganizationId = primaryOrganizationId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}

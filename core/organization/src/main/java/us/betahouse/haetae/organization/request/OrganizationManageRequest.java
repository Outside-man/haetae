/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.request;

import us.betahouse.haetae.organization.enums.MemberType;

/**
 * 组织管理请求
 *
 * @author dango.yxm
 * @version : OrganizationManageRequest.java 2019/03/25 23:54 dango.yxm
 */
public class OrganizationManageRequest extends BaseRequest {

    private static final long serialVersionUID = -7616105484671271586L;

    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 组织类型
     */
    private String organizationType;

    /**
     * 成员id
     */
    private String memberId;

    /**
     * 成员类型
     */
    private MemberType memberType;

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

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
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
}
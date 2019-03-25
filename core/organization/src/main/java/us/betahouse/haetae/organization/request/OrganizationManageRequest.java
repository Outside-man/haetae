/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.request;

/**
 * 组织管理请求
 *
 * @author dango.yxm
 * @version : OrganizationManageRequest.java 2019/03/25 23:54 dango.yxm
 */
public class OrganizationManageRequest extends BaseRequest {

    private static final long serialVersionUID = -7616105484671271586L;

    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 组织类型
     */
    private String organizationType;

    /**
     * 主管id
     */
    private String prinipalId;

    /**
     * 主管称呼
     */
    private String memberDesc;

    /**
     * 主组织id
     */
    private String primaryOrganizationId;

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

    public String getPrinipalId() {
        return prinipalId;
    }

    public void setPrinipalId(String prinipalId) {
        this.prinipalId = prinipalId;
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
/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.request;

/**
 * 组织管理请求
 * @author MessiahJK
 * @version : OrganizationRequest.java 2018/11/22 16:35 MessiahJK
 */
public class OrganizationRequest extends BaseRequest {
    private static final long serialVersionUID = -2585031363161055737L;
    /**
     * 组织id
     */
    private String organizationId;

    /**
     * 组织名
     */
    private String organizationName;

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
}

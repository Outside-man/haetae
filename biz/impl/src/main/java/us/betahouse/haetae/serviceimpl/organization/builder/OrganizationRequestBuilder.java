/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.organization.builder;

import us.betahouse.haetae.organization.enums.MemberType;
import us.betahouse.haetae.serviceimpl.organization.request.OrganizationRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 组织请求构建器
 *
 * @author dango.yxm
 * @version : OrganizationRequestBuilder.java 2019/03/27 22:40 dango.yxm
 */
final public class OrganizationRequestBuilder {

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 操作员id
     */
    private String userId;

    /**
     * 学号
     */
    private String stuId;

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
    private String memberType;

    /**
     * 成员称呼
     */
    private String memberDesc;

    /**
     * 主组织id
     */
    private String primaryOrganizationId;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    public static OrganizationRequestBuilder getInstance() {
        return new OrganizationRequestBuilder();
    }

    public OrganizationRequest build() {
        OrganizationRequest request = new OrganizationRequest();
        request.setRequestId(requestId);
        request.setMemberType(MemberType.getByType(memberType));
        request.setOrganizationId(organizationId);
        request.setOrganizationName(organizationName);
        request.setStuId(stuId);
        request.setMemberId(memberId);
        request.setUserId(userId);
        request.setMemberDesc(memberDesc);
        request.setExtInfo(extInfo);
        request.setOrganizationType(organizationType);
        request.setPrimaryOrganizationId(primaryOrganizationId);
        return request;
    }

    private OrganizationRequestBuilder() {
    }

    public OrganizationRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public OrganizationRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public OrganizationRequestBuilder withStuId(String stuId) {
        this.stuId = stuId;
        return this;
    }

    public OrganizationRequestBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public OrganizationRequestBuilder withOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public OrganizationRequestBuilder withOrganizationType(String organizationType) {
        this.organizationType = organizationType;
        return this;
    }

    public OrganizationRequestBuilder withMemberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public OrganizationRequestBuilder withMemberType(String memberType) {
        this.memberType = memberType;
        return this;
    }

    public OrganizationRequestBuilder withMemberDesc(String memberDesc) {
        this.memberDesc = memberDesc;
        return this;
    }

    public OrganizationRequestBuilder withPrimaryOrganizationId(String primaryOrganizationId) {
        this.primaryOrganizationId = primaryOrganizationId;
        return this;
    }

    public OrganizationRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }
}
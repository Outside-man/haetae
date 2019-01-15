/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request.builder;

import us.betahouse.haetae.serviceimpl.activity.request.OrganizationManagerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : OrganizationManagerRequestBuilder.java 2018/11/25 22:39 MessiahJK
 */
public final class OrganizationManagerRequestBuilder {
    private String organizationId;
    private String organizationName;
    private String userId;
    private String requestId;
    private Map<String, String> extInfo = new HashMap<>();

    private OrganizationManagerRequestBuilder() {
    }

    public static OrganizationManagerRequestBuilder getInstance() {
        return new OrganizationManagerRequestBuilder();
    }

    public OrganizationManagerRequestBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public OrganizationManagerRequestBuilder withOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public OrganizationManagerRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public OrganizationManagerRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public OrganizationManagerRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public OrganizationManagerRequest build() {
        OrganizationManagerRequest organizationManagerRequest = new OrganizationManagerRequest();
        organizationManagerRequest.setOrganizationId(organizationId);
        organizationManagerRequest.setOrganizationName(organizationName);
        organizationManagerRequest.setUserId(userId);
        organizationManagerRequest.setRequestId(requestId);
        organizationManagerRequest.setExtInfo(extInfo);
        return organizationManagerRequest;
    }
}

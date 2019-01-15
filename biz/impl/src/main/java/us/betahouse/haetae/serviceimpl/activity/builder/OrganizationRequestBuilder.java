/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.builder;

import us.betahouse.haetae.activity.request.OrganizationRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : OrganizationRequestBuilder.java 2018/11/22 18:58 MessiahJK
 */
public final class OrganizationRequestBuilder {
    /**
     * 组织id
     */
    private String organizationId;
    /**
     * 组织名
     */
    private String organizationName;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 额外信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    private OrganizationRequestBuilder() {
    }

    public static OrganizationRequestBuilder anOrganizationRequest() {
        return new OrganizationRequestBuilder();
    }

    public OrganizationRequestBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public OrganizationRequestBuilder withOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }

    public OrganizationRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public OrganizationRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public OrganizationRequest build() {
        OrganizationRequest organizationRequest = new OrganizationRequest();
        organizationRequest.setOrganizationId(organizationId);
        organizationRequest.setOrganizationName(organizationName);
        organizationRequest.setRequestId(requestId);
        organizationRequest.setExtInfo(extInfo);
        return organizationRequest;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request.builder;

import us.betahouse.haetae.serviceimpl.activity.request.PositionRecordManagerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : PositionRecordManagerRequestBuilder.java 2018/11/25 22:42 MessiahJK
 */
public final class PositionRecordManagerRequestBuilder {
    private String positionRecordId;
    private String userId;
    private String organizationId;
    private String requestId;
    private String position;
    private Map<String, String> extInfo = new HashMap<>();
    private String term;
    private String status;

    private PositionRecordManagerRequestBuilder() {
    }

    public static PositionRecordManagerRequestBuilder getInstance() {
        return new PositionRecordManagerRequestBuilder();
    }

    public PositionRecordManagerRequestBuilder withPositionRecordId(String positionRecordId) {
        this.positionRecordId = positionRecordId;
        return this;
    }

    public PositionRecordManagerRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public PositionRecordManagerRequestBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public PositionRecordManagerRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public PositionRecordManagerRequestBuilder withPosition(String position) {
        this.position = position;
        return this;
    }

    public PositionRecordManagerRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public PositionRecordManagerRequestBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public PositionRecordManagerRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public PositionRecordManagerRequest build() {
        PositionRecordManagerRequest positionRecordManagerRequest = new PositionRecordManagerRequest();
        positionRecordManagerRequest.setPositionRecordId(positionRecordId);
        positionRecordManagerRequest.setUserId(userId);
        positionRecordManagerRequest.setOrganizationId(organizationId);
        positionRecordManagerRequest.setRequestId(requestId);
        positionRecordManagerRequest.setPosition(position);
        positionRecordManagerRequest.setExtInfo(extInfo);
        positionRecordManagerRequest.setTerm(term);
        positionRecordManagerRequest.setStatus(status);
        return positionRecordManagerRequest;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.builder;

import us.betahouse.haetae.activity.request.PositionRecordRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : PositionRecordRequestBuilder.java 2018/11/22 17:25 MessiahJK
 */
public final class PositionRecordRequestBuilder {
    /**
     * 履历id
     */
    private String positionRecordId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 组织id
     */
    private String organizationId;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 职位
     */
    private String position;
    /**
     * 额外信息
     */
    private Map<String, String> extInfo = new HashMap<>();
    /**
     * 任期
     */
    private String term;
    /**
     * 状态
     */
    private String status;

    private PositionRecordRequestBuilder() {
    }

    public static PositionRecordRequestBuilder aPositionRecordRequest() {
        return new PositionRecordRequestBuilder();
    }

    public PositionRecordRequestBuilder withPositionRecordId(String positionRecordId) {
        this.positionRecordId = positionRecordId;
        return this;
    }

    public PositionRecordRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public PositionRecordRequestBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public PositionRecordRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public PositionRecordRequestBuilder withPosition(String position) {
        this.position = position;
        return this;
    }

    public PositionRecordRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public PositionRecordRequestBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public PositionRecordRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public PositionRecordRequest build() {
        PositionRecordRequest positionRecordRequest = new PositionRecordRequest();
        positionRecordRequest.setPositionRecordId(positionRecordId);
        positionRecordRequest.setUserId(userId);
        positionRecordRequest.setOrganizationId(organizationId);
        positionRecordRequest.setRequestId(requestId);
        positionRecordRequest.setPosition(position);
        positionRecordRequest.setExtInfo(extInfo);
        positionRecordRequest.setTerm(term);
        positionRecordRequest.setStatus(status);
        return positionRecordRequest;
    }
}

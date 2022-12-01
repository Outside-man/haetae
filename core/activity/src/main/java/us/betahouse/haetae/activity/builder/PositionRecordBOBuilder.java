/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.builder;

import us.betahouse.haetae.activity.model.basic.PositionRecordBO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : PositionRecordBOBuilder.java 2018/11/22 21:36 MessiahJK
 */
public final class PositionRecordBOBuilder {
    /**
     * 履历id
     */
    private String positionRecordId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 组织名
     */
    private String organizationId;

    /**
     * 职位
     */
    private String position;

    /**
     * 任期
     */
    private String term;

    /**
     * 状态
     */
    private String status;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    private PositionRecordBOBuilder() {
    }

    public static PositionRecordBOBuilder aPositionRecordBO() {
        return new PositionRecordBOBuilder();
    }

    public PositionRecordBOBuilder withPositionRecordId(String positionRecordId) {
        this.positionRecordId = positionRecordId;
        return this;
    }

    public PositionRecordBOBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public PositionRecordBOBuilder withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public PositionRecordBOBuilder withPosition(String position) {
        this.position = position;
        return this;
    }

    public PositionRecordBOBuilder withTerm(String term) {
        this.term = term;
        return this;
    }

    public PositionRecordBOBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public PositionRecordBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public PositionRecordBO build() {
        PositionRecordBO positionRecordBO = new PositionRecordBO();
        positionRecordBO.setPositionRecordId(positionRecordId);
        positionRecordBO.setUserId(userId);
        positionRecordBO.setOrganizationId(organizationId);
        positionRecordBO.setPosition(position);
        positionRecordBO.setTerm(term);
        positionRecordBO.setStatus(status);
        positionRecordBO.setExtInfo(extInfo);
        return positionRecordBO;
    }
}

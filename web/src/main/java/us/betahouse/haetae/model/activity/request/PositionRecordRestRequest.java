/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.activity.request;

import us.betahouse.haetae.common.RestRequest;

/**
 * @author MessiahJK
 * @version : PositionRecordRestRequest.java 2018/11/25 23:05 MessiahJK
 */
public class PositionRecordRestRequest extends RestRequest {
    private static final long serialVersionUID = -4965168913106475635L;
    /**
     * 履历id
     */
    private String positionRecordId;

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

    public String getPositionRecordId() {
        return positionRecordId;
    }

    public void setPositionRecordId(String positionRecordId) {
        this.positionRecordId = positionRecordId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

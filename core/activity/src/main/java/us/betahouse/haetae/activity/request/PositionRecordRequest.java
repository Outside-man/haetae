/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.request;

/**
 * 履历管理请求
 *
 * @author MessiahJK
 * @version : PositionRecordRequest.java 2018/11/22 16:39 MessiahJK
 */
public class PositionRecordRequest extends BaseRequest {

    private static final long serialVersionUID = 353972037428699435L;
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

    public String getPositionRecordId() {
        return positionRecordId;
    }

    public void setPositionRecordId(String positionRecordId) {
        this.positionRecordId = positionRecordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

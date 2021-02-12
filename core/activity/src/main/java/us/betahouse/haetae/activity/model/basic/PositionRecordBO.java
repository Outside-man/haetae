/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.model.basic;


import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : PositionRecordBO.java 2018/11/18 19:25 MessiahJK
 */
public class PositionRecordBO extends ToString {
    private static final long serialVersionUID = 2460640731372455912L;
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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

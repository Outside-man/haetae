/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.model.user.request;

import us.betahouse.haetae.common.RestRequest;

/**
 * 证书审核请求
 * @author guofan.cp
 * @version : ConfirmRequest.java 2019/04/26 17:04 guofan.cp
 */
public class ConfirmRequest extends RestRequest {

    private static final long serialVersionUID = 1951791991689053308L;
    /**
     * 待审核学生id
     */
    private String stuId;
    /**
     * 审核员id
     */
    private String confirmUserId;
    /**
     * 证书类型
     */
    private String certificateType;
    /**
     * 团队id （竞赛证书使用）
     */
    private String teamId;
    /**
     * 证书id
     */
    private String certificateId;
    /**
     * 证书驳回原因
     */
    private String rejectReason;
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(String confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }
}

/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.request;

/**
 * 证书审核记录管理请求
 *
 * @author guofan.cp
 * @version : CertificateConfirmRequest.java 2019/04/30 7:58 guofan.cp
 */
public class CertificateRecordRequest extends BaseRequest {

    private static final long serialVersionUID = 9122712340409053833L;

    /**
     * 证书id
     */
    private String certificateId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 审核员id
     */
    private String confirmUserId;
    /**
     * 审核员学号
     */
    private String confirmStuId;
    /**
     * 证书类型
     */
    private String certificateType;
    /**
     * 证书状态
     */
    private String certificateState;
    
    /**
     * 证书驳回原因
     */
    private String rejectReason;

    public String getCertificateId() {
        return certificateId;
    }
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    
    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCertificateState() {
        return certificateState;
    }

    public void setCertificateState(String certificateState) {
        this.certificateState = certificateState;
    }

    public String getConfirmStuId() {
        return confirmStuId;
    }

    public void setConfirmStuId(String confirmStuId) {
        this.confirmStuId = confirmStuId;
    }
}

/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.request;

/**
 * 资格证书请求管理
 *
 * @author guofan.cp
 * @version : QualificationsRequest.java 2019/04/03 15:20 guofan.cp
 */
public class QualificationsRequest extends BaseRequest {

    private static final long serialVersionUID = -6186986287202535254L;

    /**
     * 证书id
     */
    private String certificateId;
    /**
     * 证书名字
     */
    private String certificateName;
    /**
     * 证书归属学生id
     */
    private String userId;
    /**
     * 证书类型
     */
    private String type;
    /**
     * 证书归属组织
     */
    private String certificateOrganizationName;
    /**
     * 证书发布时间
     */
    private Long certificatePublishTime;
    /**
     * 证书编号
     */
    private String certificateNumber;
    /**
     * 审核员id
     */
    private String confirmUserId;

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCertificateOrganizationName() {
        return certificateOrganizationName;
    }

    public void setCertificateOrganizationName(String certificateOrganizationName) {
        this.certificateOrganizationName = certificateOrganizationName;
    }

    public Long getCertificatePublishTime() {
        return certificatePublishTime;
    }

    public void setCertificatePublishTime(Long certificatePublishTime) {
        this.certificatePublishTime = certificatePublishTime;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(String confirmUserId) {
        this.confirmUserId = confirmUserId;
    }
}

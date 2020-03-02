/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.builder;


import us.betahouse.haetae.serviceimpl.certificate.request.CertificateConfirmRequest;

/**
 * 证书盖章请求构造器
 *
 * @author guofan.cp
 * @version : CertificateConfirmRequest.java 2019/04/30 12:42 guofan.cp
 */
public class CertificateConfirmRequestBuilder {

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
     * 证书类型
     */
    private String certificateType;
    /**
     * 证书状态
     */
    private String certificateState;
    /**
     * 学生学号
     */
    private String confirmStuId;
    /**
     * 导入数据地址
     */
    private String localUrl;


    public static CertificateConfirmRequestBuilder getInstance() {
        return new CertificateConfirmRequestBuilder();
    }

    public CertificateConfirmRequest build() {
        CertificateConfirmRequest certificateConfirmRequest = new CertificateConfirmRequest();
        certificateConfirmRequest.setCertificateId(certificateId);
        certificateConfirmRequest.setCertificateState(certificateState);
        certificateConfirmRequest.setCertificateType(certificateType);
        certificateConfirmRequest.setConfirmStuId(confirmStuId);
        certificateConfirmRequest.setConfirmUserId(confirmUserId);
        certificateConfirmRequest.setUserId(userId);
        certificateConfirmRequest.setLocalURL(localUrl);
        return certificateConfirmRequest;
    }

    private CertificateConfirmRequestBuilder() {
    }

    public CertificateConfirmRequestBuilder withCertificateId(String certificateId) {
        this.certificateId = certificateId;
        return this;
    }

    public CertificateConfirmRequestBuilder withCertificateState(String certificateState) {
        this.certificateState = certificateState;
        return this;
    }

    public CertificateConfirmRequestBuilder withCertificateType(String certificateType) {
        this.certificateType = certificateType;
        return this;
    }

    public CertificateConfirmRequestBuilder withConfirmStuId(String confirmStuId) {
        this.confirmStuId = confirmStuId;
        return this;
    }

    public CertificateConfirmRequestBuilder withConfirmUserId(String confirmUserId) {
        this.confirmUserId = confirmUserId;
        return this;
    }
    public CertificateConfirmRequestBuilder withUserId(String userId){
        this.userId=userId;
        return this;
    }


}

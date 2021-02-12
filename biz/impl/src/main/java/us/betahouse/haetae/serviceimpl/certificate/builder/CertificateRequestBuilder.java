/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.certificate.builder;

import us.betahouse.haetae.serviceimpl.certificate.request.CertificateRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 证书请求构造器
 *
 * @author guofan.cp
 * @version : CertificateRequestBuilder.java 2019/04/06 11:49 guofan.cp
 */
final public class CertificateRequestBuilder {

    /**
     * 请求id
     */
    private String requestId;
    /**
     * 证书id
     */
    private String certificateId;
    /**
     * 证书名字
     */
    private String certificateName;
    /**
     * 比赛名字
     */
    private String competitionName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 证书内部类型
     */
    private String type;
    /**
     * 证书类型
     */
    private String certificateType;
    /**
     * 证书组织
     */
    private String certificateOrganization;
    /**
     * 证书发布时间
     */
    private Long certificatePublishTime;
    /**
     * 证书级别
     */
    private String rank;
    /**
     * 证书有效截止时间
     */
    private Long expirationTime;
    /**
     * 审核员id
     */
    private String confirmUserId;
    /**
     * 证书编号
     */
    private String certificateNumber;
    /**
     * 用户队员id
     */
    private List<String> workUserId;
    /**
     * 团队名称
     */
    private String teamName;
    /**
     * 指导老师
     */
    private List<Map<String, String>> teacher;
    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();
    /**
     * 竞赛证书团队id
     */
    private String teamId;
    /**
     * 成绩
     */
    private String certificateGrade;

    /**
     * 图片路径
     */
    private String pictureUrl;

    public static CertificateRequestBuilder getInstance() {
        return new CertificateRequestBuilder();
    }

    public CertificateRequest build() {
        CertificateRequest request = new CertificateRequest();
        request.setRequestId(requestId);
        request.setCertificateId(certificateId);
        request.setCertificateName(certificateName);
        request.setUserId(userId);
        request.setCertificateNumber(certificateNumber);
        request.setCertificateOrganization(certificateOrganization);
        request.setCertificatePublishTime(certificatePublishTime);
        request.setCertificateType(certificateType);
        request.setCompetitionName(competitionName);
        request.setConfirmUserId(confirmUserId);
        request.setExpirationTime(expirationTime);
        request.setExtInfo(extInfo);
        request.setRank(rank);
        request.setType(type);
        request.setTeacher(teacher);
        request.setTeamName(teamName);
        request.setWorkUserId(workUserId);
        request.setTeamId(teamId);
        request.setCertificateGrade(certificateGrade);
        request.setPictureUrl(pictureUrl);
        return request;
    }

    private CertificateRequestBuilder() {
    }

    public CertificateRequestBuilder withRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public CertificateRequestBuilder withPictureUrl(String pictureUrl){
        this.pictureUrl=pictureUrl;
        return this;
    }

    public CertificateRequestBuilder withCertificateId(String certificateId) {
        this.certificateId = certificateId;
        return this;
    }

    public CertificateRequestBuilder withCompetitionName(String competitionName){
        this.competitionName=competitionName;
        return this;
    }

    public CertificateRequestBuilder withCertificateName(String certificateName) {
        this.certificateName = certificateName;
        return this;
    }

    public CertificateRequestBuilder withUserID(String userId) {
        this.userId = userId;
        return this;
    }

    public CertificateRequestBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CertificateRequestBuilder withCertificateType(String certificateType) {
        this.certificateType = certificateType;
        return this;
    }

    public CertificateRequestBuilder withCertificateOrganization(String certificateOrganization) {
        this.certificateOrganization = certificateOrganization;
        return this;
    }

    public CertificateRequestBuilder withCertificatePublishTime(Long certificatePublishTime) {
        this.certificatePublishTime = certificatePublishTime;
        return this;
    }

    public CertificateRequestBuilder withRank(String rank) {
        this.rank = rank;
        return this;
    }

    public CertificateRequestBuilder withExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    public CertificateRequestBuilder withConfirmUserId(String confirmUserId) {
        this.confirmUserId = confirmUserId;
        return this;
    }

    public CertificateRequestBuilder withCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
        return this;
    }

    public CertificateRequestBuilder withWorkUserId(List<String> workUserId) {
        this.workUserId = workUserId;
        return this;
    }

    public CertificateRequestBuilder withTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public CertificateRequestBuilder withTeacher(List<Map<String, String>> teacher) {
        this.teacher = teacher;
        return this;
    }

    public CertificateRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public CertificateRequestBuilder withTeamId(String teamId){
        this.teamId=teamId;
        return  this;
    }
    public CertificateRequestBuilder withCertificateGrade(String certificateGrade) {
        this.certificateGrade = certificateGrade;
        return this;
    }

}


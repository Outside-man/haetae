/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.builder;

import us.betahouse.haetae.certificate.model.basic.CertificateBO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 证书请求构造器
 *
 * @author guofan.cp
 * @version : CertificateRequestBuilder.java 2019/04/06 11:49 guofan.cp
 */
final public class CertificateBOBuilder {

    /**
     * 证书id
     */
    private String certificateId;
    /**
     * 比赛名字
     */
    private String competitionName;
    /**
     * 证书名字
     */
    private String certificateName;
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
     * 证件状态
     */
    private String status;
    /**
     * 证书发布时间
     */
    private Date certificatePublishTime;
    /**
     * 证书级别
     */
    private String rank;
    /**
     * 证书有效截止时间
     */
    private Date expirationTime;
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

    public static CertificateBOBuilder getInstance() {
        return new CertificateBOBuilder();
    }

    public CertificateBO build() {
        CertificateBO certificateBO = new CertificateBO();
        certificateBO.setCertificateId(certificateId);
        certificateBO.setCertificateName(certificateName);
        certificateBO.setCompetitionName(competitionName);
        certificateBO.setUserId(userId);
        certificateBO.setType(type);
        certificateBO.setCertificateNumber(certificateNumber);
        certificateBO.setCertificateOrganization(certificateOrganization);
        certificateBO.setCertificatePublishTime(certificatePublishTime);
        certificateBO.setExpirationTime(expirationTime);
        certificateBO.setCertificateType(certificateType);
        certificateBO.setConfirmUserId(confirmUserId);
        certificateBO.setWorkUserId(workUserId);
        certificateBO.setExtInfo(extInfo);
        certificateBO.setRank(rank);
        certificateBO.setStatus(status);
        certificateBO.setTeacher(teacher);
        certificateBO.setTeamName(teamName);
        certificateBO.setTeamId(teamId);
        certificateBO.setCertificateGrade(certificateGrade);
        certificateBO.setPictureUrl(pictureUrl);
        return certificateBO;
    }

    private CertificateBOBuilder() {
    }


    public CertificateBOBuilder withCertificateId(String certificateId) {
        this.certificateId = certificateId;
        return this;
    }

    public CertificateBOBuilder withCertificateName(String certificateName) {
        this.certificateName = certificateName;
        return this;
    }

    public CertificateBOBuilder withCertificatePictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public CertificateBOBuilder withCompetitionName(String competitionName) {
        this.competitionName = competitionName;
        return this;
    }

    public CertificateBOBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public CertificateBOBuilder withUserID(String userId) {
        this.userId = userId;
        return this;
    }

    public CertificateBOBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CertificateBOBuilder withCertificateType(String certificateType) {
        this.certificateType = certificateType;
        return this;
    }

    public CertificateBOBuilder withCertificateOrganization(String certificateOrganization) {
        this.certificateOrganization = certificateOrganization;
        return this;
    }

    public CertificateBOBuilder withCertificatePublishTime(Date certificatePublishTime) {
        this.certificatePublishTime = certificatePublishTime;
        return this;
    }


    public CertificateBOBuilder withRank(String rank) {
        this.rank = rank;
        return this;
    }

    public CertificateBOBuilder withExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    public CertificateBOBuilder withConfirmUserId(String confirmUserId) {
        this.confirmUserId = confirmUserId;
        return this;
    }

    public CertificateBOBuilder withCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
        return this;
    }


    public CertificateBOBuilder withWorkUserId(List<String> workUserId) {
        this.workUserId = workUserId;
        return this;
    }


    public CertificateBOBuilder withTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }


    public CertificateBOBuilder withTeacher(List<Map<String, String>> teacher) {
        this.teacher = teacher;
        return this;
    }


    public CertificateBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public CertificateBOBuilder withTeamID(String teamId) {
        this.teamId = teamId;
        return this;
    }

    public CertificateBOBuilder withCertificateGrade(String certificateGrade) {
        this.certificateGrade = certificateGrade;
        return this;
    }

}


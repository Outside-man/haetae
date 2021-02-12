/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.request;

import java.util.List;
import java.util.Map;

/**
 * @author guofan.cp
 * @version : CertificateManagerRequest.java 2019/04/06 17:23 guofan.cp
 */
public class CertificateManagerRequest extends BaseRequest {

    private static final long serialVersionUID = 3492351300164953332L;

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
     * 证书详细信息
     */
    private String description;

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
     * 团队编号
     */
    private String teamId;
    /**
     * 成绩
     */
    private String certificateGrade;
    /**
     * 指导老师
     */
    private List<Map<String, String>> teacher;
    /**
     * 图片路径
     */
    private String pictureUrl;
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

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateOrganization() {
        return certificateOrganization;
    }

    public void setCertificateOrganization(String certificateOrganization) {
        this.certificateOrganization = certificateOrganization;
    }

    public Long getCertificatePublishTime() {
        return certificatePublishTime;
    }

    public void setCertificatePublishTime(Long certificatePublishTime) {
        this.certificatePublishTime = certificatePublishTime;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(String confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public List<String> getWorkUserId() {
        return workUserId;
    }

    public void setWorkUserId(List<String> workUserId) {
        this.workUserId = workUserId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public List<Map<String, String>> getTeacher() {
        return teacher;
    }

    public void setTeacher(List<Map<String, String>> teacher) {
        this.teacher = teacher;
    }

    public String getCertificateGrade() {
        return certificateGrade;
    }

    public void setCertificateGrade(String certificateGrade) {
        this.certificateGrade = certificateGrade;
    }

}

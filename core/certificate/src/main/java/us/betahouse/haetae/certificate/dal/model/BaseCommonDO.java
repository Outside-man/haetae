/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author guofan.cp
 * @version : BaseCommonDO.java 2019/04/02 14:40 guofan.cp
 * 证书实体类重复属性
 */
@MappedSuperclass
public abstract class BaseCommonDO extends BaseDO {

    private static final long serialVersionUID = 2492097357227220647L;

    /**
     * 证书id
     */
    @Column(name = "certificate_id", nullable = false, length = 32)
    private String certificateId;
    /**
     * 学生id
     */
    @Column(name = "user_id", nullable = false, length = 32)
    private String userId;
    /**
     * 证书状态 审核通过 未审核
     */
    @Column(name = "status", nullable = false)
    private String status;
    /**
     * 证书发布时间
     */
    @Column(name = "certificate_publish_time", nullable = false)
    private Date certificatePublishTime;
    /**
     * 德育分 左移2位
     */
    @Column(name = "grade", length = 8)
    private int grade;
    /**
     * 审核员id
     */
    @Column(name = "confirm_user_id", length = 32)
    private String confirmUserId;
    /**
     * 图片路径
     */
    @Column(name = "picture_url", length = 128)
    private String pictureUrl;

    public String getCertificateId() {
        return certificateId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCertificatePublishTime() {
        return certificatePublishTime;
    }

    public void setCertificatePublishTime(Date certificatePublishTime) {
        this.certificatePublishTime = certificatePublishTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(String confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

}

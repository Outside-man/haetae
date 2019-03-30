/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.haetae.certificate.dal.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @author guofan.cp
 * @version : CommonDO.java 2019/03/30 20:47 guofan.cp
 * <p>
 * 三个实体Do实体共同类
 */
@MappedSuperclass
public abstract class CommonDO extends BaseDO {

    /**
     * 证书id
     */
    @Column(name = "certificate_id",length = 32, nullable = false)
    private String certificateId;
    /**
     * 用户id
     */
    @Column(name = "user_id",length = 32,nullable = false)
    private String userId;
    /**
     * 证书状态  审核通过/未审核
     */
    @Column(name = "status",length = 20,nullable = false)
    private String status;
    /**
     * 证书发布时间
     */
    @Column(name = "certificate_publish_time")
    private Date certificatePublishTime;
    /**
     * 技能分
     */
    @Column(name = "grade" ,length = 4)
    private int grade;
    /**
     * 证书审核员id
     */
    @Column(name = "confirm_user_id",length = 32)
    private String confirmUserId;

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

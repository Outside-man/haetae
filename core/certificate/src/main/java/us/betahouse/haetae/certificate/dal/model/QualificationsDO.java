/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 资格证书实体
 *
 * @author guofan.cp
 * @version : QualificationsDO.java 2019/04/02 14:55 guofan.cp
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "certificate_qualifications_record",
        indexes = {
                @Index(name = "uk_certificate_id", columnList = "certificate_id", unique = true)
        })
public class QualificationsDO extends BaseCommonDO {

    private static final long serialVersionUID = -1030137179080154480L;

    /**
     * 证书名字
     */
    @Column(name = "certificate_name")
    private String certificateName;
    /**
     * 证书类型
     */
    @Column(name = "type", nullable = false)
    private String type;
    /**
     * 证书发布组织
     */
    @Column(name = "certificate_organization")
    private String certificateOrganization;
    /**
     * 证书编号
     */
    @Column(name = "certificate_number", length = 40)
    private String certificateNumber;
    /**
     * 成绩
     */
    @Column(name = "certificate_grade", length = 3)
    private String certificateGrade;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCertificateOrganization() {
        return certificateOrganization;
    }

    public void setCertificateOrganization(String certificateOrganization) {
        this.certificateOrganization = certificateOrganization;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getCertificateGrade() {
        return certificateGrade;
    }

    public void setCertificateGrade(String certificateGrade) {
        this.certificateGrade = certificateGrade;
    }
}

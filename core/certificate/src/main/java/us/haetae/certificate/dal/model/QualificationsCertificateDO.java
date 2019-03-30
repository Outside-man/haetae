/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.haetae.certificate.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Map;
/**
 * @author guofan.cp
 * @version : QualificationsCertificateDO.java 2019/03/30 21:25 guofan.cp
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "qualifications_certificate_record",
        indexes = {
                @Index(name = "uk_certificate_id", columnList = "certificate_id", unique = true)
        }
)
public class QualificationsCertificateDO extends CommonDO {

    private static final long serialVersionUID = 1839715535490648467L;
    /**
     * 证书名字
     */
    @Column(name = "certificate_name", length = 200, nullable = false)
    private String certificateName;
    /**
     * 证书类型
     * 1.普通
     * 2.acca cfa
     * 3.教师资格
     */
    @Column(name = "type", length = 32, nullable = false)
    private String type;
    /**
     * 证书发布组织
     */
    @Column(name = "certificate_organization")
    private String certificateOrganization;
    /**
     * 额外信息 奖状上的全部内容必填 教师必填信息 资格种类和学科也放在这边
     */
    @Column(name = "ext_info", nullable = false)
    private Map<String, String> extInfo;
    /**
     * 证书编号
     */
    @Column(name = "certification_number")
    private String certificationNumber;

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

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

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public String getCertificationNumber() {
        return certificationNumber;
    }

    public void setCertificationNumber(String certificationNumber) {
        this.certificationNumber = certificationNumber;
    }
}

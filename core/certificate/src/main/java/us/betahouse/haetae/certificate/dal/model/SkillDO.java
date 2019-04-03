/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 资格证书实体类
 *
 * @author guofan.cp
 * @version : SkillDO.java 2019/04/03 14:31 guofan.cp
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "skill_certificate_record",
        indexes = {
                @Index(name = "uk_certificate_id", columnList = "certificate_id", unique = true)
        })
public class SkillDO extends BaseCommonDO {

    private static final long serialVersionUID = -909204450267233042L;
    /**
     * 技能证书名字
     */
    @Column(name = "certificate_name", nullable = false)
    private String certificateName;
    /**
     * 技能证书等级
     */
    private String rank;
    /**
     * 证书截止有效时间
     */
    private Date effectiveTime;

    /**
     * 证书编号
     */
    @Column(name = "certificate_number")
    private String certificateNumber;

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }
}

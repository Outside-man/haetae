/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 履历实体
 *
 * @author MessiahJK
 * @version : RecordDO.java 2018/11/17 1:46 MessiahJK
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "activity_position_record",
        indexes = {
                @Index(name = "uk_position_record_id", columnList = "position_record_id", unique = true)
        })
public class PositionRecordDO extends BaseDO {

    private static final long serialVersionUID = -788359937210986705L;

    /**
     * 履历id
     */
    @Column(name = "position_record_id", length = 32, updatable = false)
    private String positionRecordId;

    /**
     * 学号
     */
    @Column(name = "stu_id", length = 32, updatable = false)
    private String stuId;

    /**
     * 组织名
     */
    @Column(name = "organization_id", length = 32, updatable = false)
    private String organizationId;

    /**
     * 职位
     */
    private String position;

    /**
     * 任期
     */
    private String term;

    /**
     * 状态
     */
    private String status;

    public String getPositionRecordId() {
        return positionRecordId;
    }

    public void setPositionRecordId(String positionRecordId) {
        this.positionRecordId = positionRecordId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

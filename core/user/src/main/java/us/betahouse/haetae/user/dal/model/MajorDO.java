/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 专业实体
 *
 * @author dango.yxm
 * @version : MajorDO.java 2018/11/15 下午10:50 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_major",
        indexes = {
                @Index(name = "uk_major_id", columnList = "major_id", unique = true)
        })
public class MajorDO extends BaseDO {

    private static final long serialVersionUID = -3091140701789200323L;

    /**
     * 专业id
     */
    @Column(name = "major_id", length = 32, updatable = false)
    private String majorId;

    /**
     * 专业名称
     */
    @Column(name = "major_name", length = 64)
    private String marjorName;

    /**
     * 年级
     */
    @Column(name = "grade", length = 64)
    private String grade;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMarjorName() {
        return marjorName;
    }

    public void setMarjorName(String marjorName) {
        this.marjorName = marjorName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}

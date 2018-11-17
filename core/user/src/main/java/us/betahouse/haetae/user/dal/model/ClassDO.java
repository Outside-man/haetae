/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 班级实体
 *
 * @author dango.yxm
 * @version : ClassDO.java 2018/11/16 下午6:38 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_class",
        indexes = {
                @Index(name = "uk_class_id", columnList = "class_id", unique = true),
                @Index(name = "uk_class_id_major_id", columnList = "class_id, major_id", unique = true)
        })
public class ClassDO extends BaseDO {

    private static final long serialVersionUID = -2245538802465034427L;

    /**
     * 班级id
     */
    @Column(name = "class_id", length = 32, updatable = false, nullable = false)
    private String classId;

    /**
     * 专业id
     */
    @Column(name = "major_id", length = 32)
    private String majorId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户实体
 *
 * @author dango.yxm
 * @version : UserInfoDO.java 2018/11/15 下午2:17 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_user_info",
        indexes = {
                @Index(name = "uk_major_id_class_id_user_info_id", columnList = "major_id, class_id, user_info_id", unique = true),
                @Index(name = "uk_user_info_id_user_id", columnList = "user_info_id, user_id", unique = true),
                @Index(name = "uk_user_id", columnList = "user_id", unique = true),
                @Index(name = "uk_user_info_id", columnList = "user_info_id", unique = true),
                @Index(name = "uk_stu_id", columnList = "stu_id", unique = true)
        })
public class UserInfoDO extends BaseDO {

    private static final long serialVersionUID = 4332447165010415804L;

    /**
     * 用户信息id
     */
    @Column(name = "user_info_id", length = 32, updatable = false, nullable = false)
    private String userInfoId;

    /**
     * 用户id
     */
    @Column(name = "user_id", length = 32, nullable = false)
    private String userId;

    /**
     * 学号
     */
    @Column(name = "stu_id", length = 32)
    private String stuId;

    /**
     * 姓名
     */
    @Column(length = 32)
    private String realName;

    /**
     * 性别
     */
    @Column(length = 6)
    private String sex;

    /**
     * 专业号
     */
    @Column(name = "major_id", length = 32)
    private String majorId;

    /**
     * 班级号
     */
    @Column(name = "class_id", length = 32)
    private String classId;

    /**
     * 年级
     */
    @Column(name = "grade", length = 64)
    private String grade;

    /**
     * 入学时间
     */
    private Date enrollDate;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

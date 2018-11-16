/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @author dango.yxm
 * @version : UserInfoDO.java 2018/11/15 下午2:17 dango.yxm
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "common_user_info")
public class UserInfoDO implements Serializable {

    private static final long serialVersionUID = 4332447165010415804L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 用户信息id
     */
    @Column(length = 32, updatable = false, unique = true)
    private String userInfoId;

    /**
     * 用户id
     */
    @Column(length = 32)
    private String userId;

    /**
     * 学号
     */
    @Column(length = 32)
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
    @Column(length = 32)
    private String majorId;

    /**
     * 班级号
     */
    @Column(length = 32)
    private String classId;

    /**
     * 入学年份
     */
    @Column(length = 8)
    private Integer enrollDate;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "gmt_create", nullable = false)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified", nullable = false)
    private Date gmtModified;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Integer enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}

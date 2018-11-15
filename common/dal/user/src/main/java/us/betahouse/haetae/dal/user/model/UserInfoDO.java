/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.dal.user.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.Date;

/**
 * 用户信息
 * @author dango.yxm
 * @version : UserInfoDO.java 2018/11/15 下午2:17 dango.yxm
 */
@Entity
@Table(name = "common_user_info")
@EntityListeners(AuditingEntityListener.class)
public class UserInfoDO {

    private Long id;

    /**
     * 用户信息id
     */
    private String userInfoId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 专业号
     */
    private String majorId;

    /**
     * 班级号
     */
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
    @Column(name = "gmt_create",nullable = false)
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "gmt_modified",nullable = false)
    private Date gmtModified;

    /**
     * 拓展信息
     */
    @Column(length = 2000)
    private String extInfo;
}

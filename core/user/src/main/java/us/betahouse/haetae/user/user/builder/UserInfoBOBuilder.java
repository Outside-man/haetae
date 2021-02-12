/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.builder;

import us.betahouse.haetae.user.model.basic.UserInfoBO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息构建者
 *
 * @author dango.yxm
 * @version : UserInfoBOBuilder.java 2018/11/18 下午11:11 dango.yxm
 */
final public class UserInfoBOBuilder {

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
    private Date enrollDate;

    private String grade;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    public static UserInfoBOBuilder getInstance() {
        return new UserInfoBOBuilder();
    }

    public UserInfoBO build() {
        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setClassId(classId);
        userInfoBO.setEnrollDate(enrollDate);
        userInfoBO.setMajor(majorId);
        userInfoBO.setRealName(realName);
        userInfoBO.setSex(sex);
        userInfoBO.setStuId(stuId);
        userInfoBO.setGrade(grade);
        if (extInfo != null) {
            userInfoBO.setExtInfo(extInfo);
        }
        return userInfoBO;
    }


    /**
     * 构造器
     */
    private UserInfoBOBuilder() {
    }

    public UserInfoBOBuilder withStuId(String stuId) {
        this.stuId = stuId;
        return this;
    }

    public UserInfoBOBuilder withRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public UserInfoBOBuilder withSex(String sex) {
        this.sex = sex;
        return this;
    }

    public UserInfoBOBuilder withMajorId(String majorId) {
        this.majorId = majorId;
        return this;
    }

    public UserInfoBOBuilder withClassId(String classId) {
        this.classId = classId;
        return this;
    }
    public UserInfoBOBuilder withGrade(String grade) {
        this.grade = grade;
        return this;
    }

    public UserInfoBOBuilder withEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
        return this;
    }

    public UserInfoBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }
}

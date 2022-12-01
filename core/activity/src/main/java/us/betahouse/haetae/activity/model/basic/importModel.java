/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.activity.model.basic;

import us.betahouse.util.common.ToString;

/**
 * @author guofan.cp
 * @version : importModel.java 2019/08/25 16:35 guofan.cp
 */
public class importModel extends ToString {

    private static final long serialVersionUID = -4747424967673952209L;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 学生学号
     */
    private String stuId;

    /**
     * 活动名字
     */
    private String activityName;

    /**
     * 时长
     */
    private String time;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 活动类型
     */
    private String type;

    /**
     * 行号
     */
    private String rownum;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public importModel() {
    }


    public importModel(String stuName, String stuId, String activityName, String rownum) {
        this.stuName = stuName;
        this.stuId = stuId;
        this.activityName = activityName;
        this.rownum = rownum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getRownum() {
        return rownum;
    }

    public void setRownum(String rownum) {
        this.rownum = rownum;
    }

    @Override
    public String toString() {
        return this.getActivityName() + this.getStuId() + this.getStuName() + this.getRownum();
    }
}

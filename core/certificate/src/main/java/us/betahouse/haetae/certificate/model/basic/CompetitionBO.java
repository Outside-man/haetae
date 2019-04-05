/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.model.basic;

import us.betahouse.util.common.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 竞赛证书模型
 *
 * @author guofan.cp
 * @version : CompetitionBO.java 2019/04/05 13:10 guofan.cp
 */
public class CompetitionBO extends ToString {

    private static final long serialVersionUID = -5874882634168108283L;

    /**
     * 证书id
     */
    private String certificateId;
    /**
     * 竞赛名字
     */
    private String competitionName;
    /**
     * 状态
     */
    private String status;
    /**
     * 类型
     */
    private String type;
    /**
     * 队员id
     */
    private List<String> workersUserId;
    /**
     * 颁发时间
     */
    private Date certificatePublishTime;
    /**
     * 指导老师
     */
    private Map<String, String> teacher = new HashMap<>();
    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    /**
     * 放入拓展信息
     *
     * @param key
     * @param value
     */
    public void putExtInfo(String key, String value) {
        if (extInfo == null) {
            extInfo = new HashMap<>();
        }
        extInfo.put(key, value);
    }

    /**
     * 取出拓展信息
     *
     * @param key
     * @return
     */
    public String fetchExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }

    /**
     * 放入指导老师信息 姓名和工号
     *
     * @param key
     * @param value
     * @return
     */
    public void putTeacher(String key, String value) {
        if (teacher == null) {
            teacher = new HashMap<>();
        }
        teacher.put(key, value);
    }

    public String fetchTeacher(String key) {
        if (teacher == null) {
            return null;
        }
        return teacher.get(key);
    }

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getWorkersUserId() {
        return workersUserId;
    }

    public void setWorkersUserId(List<String> workersUserId) {
        this.workersUserId = workersUserId;
    }

    public Date getCertificatePublishTime() {
        return certificatePublishTime;
    }

    public void setCertificatePublishTime(Date certificatePublishTime) {
        this.certificatePublishTime = certificatePublishTime;
    }

    public Map<String, String> getTeacher() {
        return teacher;
    }

    public void setTeacher(Map<String, String> teacher) {
        this.teacher = teacher;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.model.basic;

import us.betahouse.util.common.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 资格证书模型
 *
 * @author guofan.cp
 * @version : QualificationsBO.java 2019/04/05 13:56 guofan.cp
 */
public class QualificationsBO extends ToString {

    private static final long serialVersionUID = -8769073290268837997L;

    /**
     * 证书id
     */
    private String certificateId;
    /**
     * 证书名字
     */
    private String certificateName;
    /**
     * 状态
     */
    private String status;
    /**
     * 类型
     */
    private String type;
    /**
     * 证书颁发组织
     */
    private String certificateOrganization;
    /**
     * 颁发时间
     */
    private Date certificatePublishTime;
    /**
     * 证书编号
     */
    private String certificateNumber;
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

    public String getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
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

    public String getCertificateOrganization() {
        return certificateOrganization;
    }

    public void setCertificateOrganization(String certificateOrganization) {
        this.certificateOrganization = certificateOrganization;
    }

    public Date getCertificatePublishTime() {
        return certificatePublishTime;
    }

    public void setCertificatePublishTime(Date certificatePublishTime) {
        this.certificatePublishTime = certificatePublishTime;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

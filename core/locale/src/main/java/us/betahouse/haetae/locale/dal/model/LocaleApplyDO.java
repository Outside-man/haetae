/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "locale_apply",
        indexes = {
                @Index(name = "uk_locale_apply_id", columnList = "locale_apply_id", unique = true)
        })
public class LocaleApplyDO extends BaseDO {


    private static final long serialVersionUID = 5099628321958501996L;
    /**
     * 申请者的联系方式
     */
    @Column(name = "tel", length = 32)
    private String tel;

    /**
     * 申请的用途
     */
    @Column(name = "usages", length = 32)
    private String usages;

    /**
     * 申请的备注
     */
    @Column(name = "remark", length =256)
    private String remark;

    /**
     * 申请的附件
     */
    @Column(name = "document",columnDefinition="text", length = 32)
    private String document;

    /**
     * 是否状况 三种：提交申请 申请中 取消
     */
    @Column(name = "status", length = 32)
    private String status;

    /**
     * 场地使用日期
     */
    @Column(name = "time_date")
    private String timeDate;

    /**
     * 场地使用时间段
     */
    @Column(name = "time_bucket")
    private String timeBucket;

    /**
     * 场地代号
     */
    @Column(name = "locale_code", length = 32)
    private String localeCode;

    /**
     * 占用ID
     */
    @Column(name = "locale_area_id", length = 32, updatable = false)
    private String localeAreaId;

    /**
     * 场地ID
     */
    @Column(name = "locale_id", length = 32, updatable = false)
    private String localeId;

    /**
     * 场地借用者ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 申请ID
     */
    @Column(name = "locale_apply_id", length = 32, updatable = false)
    private String localeApplyId;


    /**
     * 组织名
     */
    @Column(name = "locale_organization_name", length = 32, updatable = false)
    private String organizationName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public String getTimeBucket() {
        return timeBucket;
    }

    public void setTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getLocaleAreaId() {
        return localeAreaId;
    }

    public void setLocaleAreaId(String localeAreaId) {
        this.localeAreaId = localeAreaId;
    }

    public String getLocaleId() {
        return localeId;
    }

    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocaleApplyId() {
        return localeApplyId;
    }

    public void setLocaleApplyId(String localeApplyId) {
        this.localeApplyId = localeApplyId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
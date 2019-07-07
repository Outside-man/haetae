/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.request;

/**
 * @author NathanDai
 * @version :  2019-07-05 06:25 NathanDai
 */
public class LocaleApplyRequest extends BaseRequest {
    private static final long serialVersionUID = -697605016962701559L;

    /**
     * 申请者的联系方式
     */

    private String tel;
    /**
     * 申请的用途
     */

    private String usages;
    /**
     * 申请的备注
     */

    private String remark;
    /**
     * 申请的附件
     */

    private String document;
    /**
     * 是否状况 三种：提交申请 申请中 取消
     */

    private String status;
    /**
     * 场地使用日期
     */

    private String timeDate;
    /**
     * 场地使用时间段
     */

    private String timeBucket;
    /**
     * 场地代号
     */

    private String localeCode;
    /**
     * 占用ID
     */

    private String localeAreaId;
    /**
     * 场地ID
     */

    private String localeId;
    /**
     * 场地借用者ID
     */

    private String userId;
    /**
     * 申请ID
     */

    private String localeApplyId;

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
}

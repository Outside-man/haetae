/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.builder;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NathanDai
 * @version :  2019-07-06 21:13 NathanDai
 */
public final class LocaleApplyBOBuilder {
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
    /**
     * 组织名
     */
    private String organizationName;
    /**
     * 场地申请额外信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    private LocaleApplyBOBuilder() {
    }

    public static LocaleApplyBOBuilder getInstance() {
        return new LocaleApplyBOBuilder();
    }

    public LocaleApplyBOBuilder withTel(String tel) {
        this.tel = tel;
        return this;
    }

    public LocaleApplyBOBuilder withUsages(String usages) {
        this.usages = usages;
        return this;
    }

    public LocaleApplyBOBuilder withRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public LocaleApplyBOBuilder withDocument(String document) {
        this.document = document;
        return this;
    }

    public LocaleApplyBOBuilder withLocaleCode(String localeCode) {
        this.localeCode = localeCode;
        return this;
    }


    public LocaleApplyBOBuilder withLocaleId(String localeId) {
        this.localeId = localeId;
        return this;
    }

    public LocaleApplyBOBuilder withLocaleAreaId(String localeAreaId) {
        this.localeAreaId = localeAreaId;
        return this;
    }

    public LocaleApplyBOBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public LocaleApplyBOBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public LocaleApplyBOBuilder withTimeDate(String timeDate) {
        this.timeDate = timeDate;
        return this;
    }

    public LocaleApplyBOBuilder withTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
        return this;
    }

    public LocaleApplyBOBuilder withLocaleApplyId(String localeApplyId) {
        this.localeApplyId = localeApplyId;
        return this;
    }

    public LocaleApplyBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public LocaleApplyBOBuilder withOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }
    public LocaleApplyBO build() {
        LocaleApplyBO localeApplyBO = new LocaleApplyBO();
        localeApplyBO.setTel(tel);
        localeApplyBO.setUsages(usages);
        localeApplyBO.setRemark(remark);
        localeApplyBO.setDocument(document);
        localeApplyBO.setLocaleAreaId(localeAreaId);
        localeApplyBO.setLocaleId(localeId);
        localeApplyBO.setLocaleCode(localeCode);
        localeApplyBO.setUserId(userId);
        localeApplyBO.setTimeDate(timeDate);
        localeApplyBO.setTimeBucket(timeBucket);
        localeApplyBO.setStatus(status);
        localeApplyBO.setLocaleApplyId(localeApplyId);
        localeApplyBO.setExtInfo(extInfo);
        localeApplyBO.setOrganizationName(organizationName);
        return localeApplyBO;
    }
}

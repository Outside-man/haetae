/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.request.builder;

import us.betahouse.haetae.serviceimpl.locale.request.LocaleApplyManagerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NathanDai
 * @version :  2019-07-05 19:26 NathanDai
 */
public final class LocaleApplyManagerRequestBuilder {
    private String tel;
    private String usages;
    private String remark;
    private String document;
    private String status;
    private String timeDate;
    private String timeBucket;
    private String localeCode;
    private String localeAreaId;
    private String localeId;
    private String userId;
    private String localeApplyId;
    private Map<String, String> extInfo = new HashMap<>();

    private Integer limit;
    private Integer page;
    private String orderRule;
    private String organizationName;

    private LocaleApplyManagerRequestBuilder() {
    }

    public static LocaleApplyManagerRequestBuilder getInstance() {
        return new LocaleApplyManagerRequestBuilder();
    }

    public LocaleApplyManagerRequestBuilder withTel(String tel) {
        this.tel = tel;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withUsages(String usages) {
        this.usages = usages;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withDocument(String document) {
        this.document = document;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withLocaleId(String localeId) {
        this.localeId = localeId;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withLocaleAreaId(String localeAreaId) {
        this.localeAreaId = localeAreaId;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withTimeDate(String timeDate) {
        this.timeDate = timeDate;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withLocaleCode(String localeCode) {
        this.localeCode = localeCode;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withLocaleApplyId(String localeApplyId) {
        this.localeApplyId = localeApplyId;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withPage(Integer page) {
        this.page = page;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withOrderRule(String orderRule) {
        this.orderRule = orderRule;
        return this;
    }

    public LocaleApplyManagerRequestBuilder withOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }
    public LocaleApplyManagerRequest build() {
        LocaleApplyManagerRequest localeApplyManagerRequest = new LocaleApplyManagerRequest();
        localeApplyManagerRequest.setTel(tel);
        localeApplyManagerRequest.setUsages(usages);
        localeApplyManagerRequest.setRemark(remark);
        localeApplyManagerRequest.setDocument(document);
        localeApplyManagerRequest.setLocaleAreaId(localeAreaId);
        localeApplyManagerRequest.setLocaleId(localeId);
        localeApplyManagerRequest.setUserId(userId);
        localeApplyManagerRequest.setTimeDate(timeDate);
        localeApplyManagerRequest.setLocaleCode(localeCode);
        localeApplyManagerRequest.setTimeBucket(timeBucket);
        localeApplyManagerRequest.setLocaleApplyId(localeApplyId);
        localeApplyManagerRequest.setStatus(status);
        localeApplyManagerRequest.setExtInfo(extInfo);
        localeApplyManagerRequest.setLimit(limit);
        localeApplyManagerRequest.setPage(page);
        localeApplyManagerRequest.setOrderRule(orderRule);
        localeApplyManagerRequest.setOrganizationName(organizationName);
        return localeApplyManagerRequest;
    }
}

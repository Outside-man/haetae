/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.request.builder;

import us.betahouse.haetae.serviceimpl.locale.request.LocaleAreaManagerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NathanDai
 * @version :  2019-07-05 19:26 NathanDai
 */
public final class LocaleAreaManagerRequestBuilder {

    private String localeAreaId;
    private String localeId;
    private String userId;
    private String timeDate;
    private String timeBucket;
    private String status;
    private Map<String, String> extInfo = new HashMap<>();

    private LocaleAreaManagerRequestBuilder() {
    }

    public static LocaleAreaManagerRequestBuilder getInstance() {
        return new LocaleAreaManagerRequestBuilder();
    }

    public LocaleAreaManagerRequestBuilder withLocaleId(String localeId) {
        this.localeId = localeId;
        return this;
    }

    public LocaleAreaManagerRequestBuilder withLocaleAreaId(String localeAreaId) {
        this.localeAreaId = localeAreaId;
        return this;
    }

    public LocaleAreaManagerRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public LocaleAreaManagerRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public LocaleAreaManagerRequestBuilder withTimeDate(String timeDate) {
        this.timeDate = timeDate;
        return this;
    }

    public LocaleAreaManagerRequestBuilder withTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
        return this;
    }

    public LocaleAreaManagerRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public LocaleAreaManagerRequest build() {
        LocaleAreaManagerRequest localeAreaManagerRequest = new LocaleAreaManagerRequest();
        localeAreaManagerRequest.setLocaleAreaId(localeAreaId);
        localeAreaManagerRequest.setLocaleId(localeId);
        localeAreaManagerRequest.setUserId(userId);
        localeAreaManagerRequest.setTimeDate(timeDate);
        localeAreaManagerRequest.setTimeBucket(timeBucket);
        localeAreaManagerRequest.setStatus(status);
        localeAreaManagerRequest.setExtInfo(extInfo);
        return localeAreaManagerRequest;
    }
}

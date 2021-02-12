/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.request.builder;

import us.betahouse.haetae.serviceimpl.locale.request.LocaleManagerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NathanDai
 * @version :  2019-07-04 09:57 NathanDai
 */
public final class LocaleManagerRequestBuilder {

    private String localeId;
    private String localeName;
    private String localeCode;
    private String status;
    private String userId;
    private Map<String, String> extInfo = new HashMap<>();

    private LocaleManagerRequestBuilder() {
    }

    public static LocaleManagerRequestBuilder getInstance() {
        return new LocaleManagerRequestBuilder();
    }

    public LocaleManagerRequestBuilder withLocaleId(String localeId) {
        this.localeId = localeId;
        return this;
    }

    public LocaleManagerRequestBuilder withLocaleName(String localeName) {
        this.localeName = localeName;
        return this;
    }

    public LocaleManagerRequestBuilder withLocaleCode(String localeCode) {
        this.localeCode = localeCode;
        return this;
    }

    public LocaleManagerRequestBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public LocaleManagerRequestBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public LocaleManagerRequestBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public LocaleManagerRequest build() {
        LocaleManagerRequest localeManagerRequest = new LocaleManagerRequest();
        localeManagerRequest.setLocaleId(localeId);
        localeManagerRequest.setLocaleName(localeName);
        localeManagerRequest.setLocaleCode(localeCode);
        localeManagerRequest.setStatus(status);
        localeManagerRequest.setUserId(userId);
        localeManagerRequest.setExtInfo(extInfo);
        return localeManagerRequest;
    }


}

/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.builder;

import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NathanDai
 * @version :  2019-07-06 12:55 NathanDai
 */
public final class LocaleAreaBOBuilder {
    /**
     * 占用ID
     */
    private String localeAreaId;

    /**
     * 场地ID
     */
    private String localeId;

    /**
     * 场地借用者
     */
    private String userId;

    /**
     * 场地使用日期
     */
    private String timeDate;

    /**
     * 场地使用时间段
     */
    private String timeBucket;

    /**
     * 是否状况 三种：提交申请 申请中 取消
     */
    private String status;

    /**
     * 场地占用额外信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    private LocaleAreaBOBuilder() {
    }

    public static LocaleAreaBOBuilder getInstance() {
        return new LocaleAreaBOBuilder();
    }

    public LocaleAreaBOBuilder withLocaleId(String localeId) {
        this.localeId = localeId;
        return this;
    }

    public LocaleAreaBOBuilder withLocaleAreaId(String localeAreaId) {
        this.localeAreaId = localeAreaId;
        return this;
    }

    public LocaleAreaBOBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public LocaleAreaBOBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public LocaleAreaBOBuilder withTimeDate(String timeDate) {
        this.timeDate = timeDate;
        return this;
    }

    public LocaleAreaBOBuilder withTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
        return this;
    }

    public LocaleAreaBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }

    public LocaleAreaBO build() {
        LocaleAreaBO localeAreaBO = new LocaleAreaBO();
        localeAreaBO.setLocaleAreaId(localeAreaId);
        localeAreaBO.setLocaleId(localeId);
        localeAreaBO.setUserId(userId);
        localeAreaBO.setTimeDate(timeDate);
        localeAreaBO.setTimeBucket(timeBucket);
        localeAreaBO.setStatus(status);
        localeAreaBO.setExtInfo(extInfo);
        return localeAreaBO;
    }

}

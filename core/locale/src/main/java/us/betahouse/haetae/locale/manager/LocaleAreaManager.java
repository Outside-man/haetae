/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.manager;

import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;
import us.betahouse.haetae.locale.request.LocaleAreaRequest;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-05 06:27 NathanDai
 */
public interface LocaleAreaManager {
    /**
     * 查询场地占用
     *
     * @param localeId 场地id
     * @param timeDate 申请日期
     * @param status   CANCEL
     * @return
     */
    List<LocaleAreaBO> findAllByLocaleIdAndTimeDateAndStatus(String localeId, String timeDate, String status);

    /**
     * 创建场地占用
     *
     * @param localeAreaRequest
     * @return
     */
    LocaleAreaBO create(LocaleAreaRequest localeAreaRequest);

    /**
     * 取消场地占用
     *
     * @param localeAreaRequest
     * @return
     */
    LocaleAreaBO update(LocaleAreaRequest localeAreaRequest);

    /**
     * 取消场地占用
     *
     * @param localeAreaId
     * @return
     */
    LocaleAreaBO cancel(String localeAreaId);

    /**
     * 校验场地是否被占用
     *
     * @param localeId
     * @param timeDate
     * @param timeBucket
     * @param status
     * @return
     */
    LocaleAreaBO findByLocaleIdAndTimeDateAndTimeBucketAndStatus(String localeId, String timeDate, String timeBucket, String status);
}

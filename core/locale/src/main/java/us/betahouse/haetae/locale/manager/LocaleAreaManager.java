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
     * @param localeId
     * @param timeDate
     * @param status
     * @return
     */
    List<LocaleAreaBO> findAllByLocaleIdAndTimeDateAndStatus(String localeId, String timeDate, String status);

    /**
     * @param localeAreaRequest
     * @return
     */
    LocaleAreaBO create(LocaleAreaRequest localeAreaRequest);

    /**
     * @param localeAreaRequest
     * @return
     */
    LocaleAreaBO update(LocaleAreaRequest localeAreaRequest);
}

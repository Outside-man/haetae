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
    List<LocaleAreaBO> findAllByLocaleIdAndTimeDateAndStatus(String localeId, String timeDate, String status);

    LocaleAreaBO create(LocaleAreaRequest localeAreaRequest);

    LocaleAreaBO update(LocaleAreaRequest localeAreaRequest);
}

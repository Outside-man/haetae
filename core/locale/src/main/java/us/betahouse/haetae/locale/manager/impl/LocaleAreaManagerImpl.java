/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.builder.LocaleAreaBOBuilder;
import us.betahouse.haetae.locale.dal.service.LocaleAreaDORepoService;
import us.betahouse.haetae.locale.manager.LocaleAreaManager;
import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;
import us.betahouse.haetae.locale.request.LocaleAreaRequest;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-05 06:28 NathanDai
 */
@Service
public class LocaleAreaManagerImpl implements LocaleAreaManager {
    @Autowired
    LocaleAreaDORepoService localeAreaDORepoService;

    /**
     * @param localeId
     * @param timeDate
     * @param status
     * @return
     */
    @Override
    public List<LocaleAreaBO> findAllByLocaleIdAndTimeDateAndStatus(String localeId, String timeDate, String status) {
        return localeAreaDORepoService.queryLocaleAreasByLocaleIdAndTimeDateAndStatusNot(localeId, timeDate, status);
    }

    /**
     * @param request
     * @return
     */
    @Override
    public LocaleAreaBO create(LocaleAreaRequest request) {
        LocaleAreaBOBuilder localeAreaBOBuilder = LocaleAreaBOBuilder.getInstance()
                .withLocaleId(request.getLocaleId())
                .withTimeDate(request.getTimeDate())
                .withTimeBucket(request.getTimeBucket())
                .withUserId(request.getUserId())
                .withStatus(request.getStatus());
        return localeAreaDORepoService.createLocaleArea(localeAreaBOBuilder.build());
    }

    /**
     * @param request
     * @return
     */
    @Override
    public LocaleAreaBO update(LocaleAreaRequest request) {
        LocaleAreaBOBuilder localeAreaBOBuilder = LocaleAreaBOBuilder.getInstance()
                .withLocaleAreaId(request.getLocaleAreaId())
                .withStatus(request.getStatus());
        return localeAreaDORepoService.updateLocaleArea(localeAreaBOBuilder.build());
    }
}

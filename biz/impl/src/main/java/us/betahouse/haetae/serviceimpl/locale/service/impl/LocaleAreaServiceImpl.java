/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.manager.LocaleAreaManager;
import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleAreaManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleAreaService;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-05 05:33 NathanDai
 */
@Service
public class LocaleAreaServiceImpl implements LocaleAreaService {

    @Autowired
    LocaleAreaManager localeAreaManager;

    @Override
    public List<LocaleAreaBO> findAllByLocaleIdAndTimeDateAndStatus(LocaleAreaManagerRequest request, OperateContext context) {
        List<LocaleAreaBO> localeAreaBO = localeAreaManager.findAllByLocaleIdAndTimeDateAndStatus(request.getLocaleId(), request.getTimeDate(), "CANCEL");
        return localeAreaBO;
    }

    @Override
    public LocaleAreaBO create(LocaleAreaManagerRequest request, OperateContext context) {
        LocaleAreaBO localeAreaBO = localeAreaManager.create(request);
        return localeAreaBO;
    }

    @Override
    public LocaleAreaBO update(LocaleAreaManagerRequest request, OperateContext context) {
        LocaleAreaBO localeAreaBO = localeAreaManager.update(request);
        return localeAreaBO;
    }
}

/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.builder.LocaleApplyBOBuilder;
import us.betahouse.haetae.locale.dal.service.LocaleApplyDORepoService;
import us.betahouse.haetae.locale.manager.LocaleApplyManager;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.request.LocaleApplyRequest;

/**
 * @author NathanDai
 * @version :  2019-07-05 06:28 NathanDai
 */
@Service
public class LocaleApplyManagerImpl implements LocaleApplyManager {
    @Autowired
    LocaleApplyDORepoService localeApplyDORepoService;

    /**
     * @param request
     * @return
     */
    @Override
    public LocaleApplyBO create(LocaleApplyRequest request) {
        LocaleApplyBOBuilder localeApplyBOBuilder = LocaleApplyBOBuilder.getInstance()
                .withLocaleId(request.getLocaleId())
                .withLocaleAreaId(request.getLocaleAreaId())
                .withLocaleCode(request.getLocaleCode())
                .withStatus(request.getStatus())
                .withDocument(request.getDocument())
                .withTel(request.getTel())
                .withRemark(request.getRemark())
                .withUsages(request.getUsages())
                .withTimeDate(request.getTimeDate())
                .withTimeBucket(request.getTimeBucket())
                .withUserId(request.getUserId());
        return localeApplyDORepoService.createLocaleApply(localeApplyBOBuilder.build());
    }

    @Override
    public LocaleApplyBO update(LocaleApplyRequest request) {
        LocaleApplyBOBuilder localeApplyBOBuilder = LocaleApplyBOBuilder.getInstance()
                .withLocaleApplyId(request.getLocaleApplyId())
                .withStatus(request.getStatus());
        return localeApplyDORepoService.updateLocaleApply(localeApplyBOBuilder.build());
    }
}

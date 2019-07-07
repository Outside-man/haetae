/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.locale.manager.LocaleApplyManager;
import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleApplyManagerRequest;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleApplyService;

/**
 * @author NathanDai
 * @version :  2019-07-05 05:33 NathanDai
 */

@Service
public class LocaleApplyServiceImpl implements LocaleApplyService {

    @Autowired
    LocaleApplyManager localeApplyManager;

    /**
     * 创建场地申请
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    public LocaleApplyBO create(LocaleApplyManagerRequest request, OperateContext context) {
        LocaleApplyBO localeApplyBO = localeApplyManager.create(request);
        return localeApplyBO;
    }

    /**
     * 更新场地申请状态
     *
     * @param request
     * @param context
     * @return
     */
    @Override
    public LocaleApplyBO update(LocaleApplyManagerRequest request, OperateContext context) {
        LocaleApplyBO localeApplyBO = localeApplyManager.update(request);
        return localeApplyBO;
    }

}

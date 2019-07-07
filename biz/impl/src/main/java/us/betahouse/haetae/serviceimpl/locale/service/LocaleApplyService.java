/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.service;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.model.common.PageList;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleApplyManagerRequest;

/**
 * @author NathanDai
 * @version :  2019-07-05 05:32 NathanDai
 */
public interface LocaleApplyService {
    /**
     * @param request
     * @param context
     * @return
     */
    LocaleApplyBO create(LocaleApplyManagerRequest request, OperateContext context);

    PageList<LocaleApplyBO> findAllByStatus(LocaleApplyManagerRequest request, OperateContext context);
}

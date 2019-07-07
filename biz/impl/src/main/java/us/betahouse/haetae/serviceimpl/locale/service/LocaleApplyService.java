/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.service;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleApplyManagerRequest;

/**
 * @author NathanDai
 * @version :  2019-07-05 05:32 NathanDai
 */
public interface LocaleApplyService {
    /**
     * 创建场地申请
     *
     * @param request
     * @param context
     * @return
     */
    LocaleApplyBO create(LocaleApplyManagerRequest request, OperateContext context);

    /**
     * 更新场地申请状态
     *
     * @param request
     * @param context
     * @return
     */
    LocaleApplyBO update(LocaleApplyManagerRequest request, OperateContext context);
}

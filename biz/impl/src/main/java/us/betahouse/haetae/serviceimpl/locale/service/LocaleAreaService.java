/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.service;

import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleAreaManagerRequest;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-05 05:32 NathanDai
 */
public interface LocaleAreaService {
    /**
     * 查询场地是否占用
     *
     * @param request
     * @param context
     * @return
     */
    List<LocaleAreaBO> findAllByLocaleIdAndTimeDateAndStatus(LocaleAreaManagerRequest request, OperateContext context);

    /**
     * 场地占用
     *
     * @param request
     * @param context
     * @return
     */
    LocaleAreaBO create(LocaleAreaManagerRequest request, OperateContext context);

    /**
     * 更新场地占用
     *
     * @param request
     * @param context
     * @return
     */
    LocaleAreaBO update(LocaleAreaManagerRequest request, OperateContext context);
}

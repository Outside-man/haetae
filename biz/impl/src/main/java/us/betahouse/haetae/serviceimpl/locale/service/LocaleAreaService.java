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
 * 场地占用业务
 *
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
     * @return LocaleAreaBO
     */
    LocaleAreaBO create(LocaleAreaManagerRequest request, OperateContext context);

    /**
     * 取消场地占用
     *
     * @param request LocaleAreaManagerRequest
     * @param context OperateContext
     * @return LocaleAreaBO
     */
    LocaleAreaBO update(LocaleAreaManagerRequest request, OperateContext context);
}

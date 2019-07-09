/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.locale.service;

import us.betahouse.haetae.locale.model.basic.LocaleBO;
import us.betahouse.haetae.serviceimpl.common.OperateContext;
import us.betahouse.haetae.serviceimpl.locale.request.LocaleManagerRequest;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface LocaleService {
    /**
     * 查询所有的场地
     *
     * @param request
     * @param context
     * @return
     */
    List<LocaleBO> findAllLocale(LocaleManagerRequest request, OperateContext context);

    /**
     * 通过场地状态查询所有场地
     *
     * @param request
     * @param context
     * @return
     */
    List<LocaleBO> findAllLocaleByStatus(LocaleManagerRequest request, OperateContext context);
}

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
 * 场地业务
 *
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface LocaleService {
    /**
     * 通过场地状态查询所有场地
     *
     * @param request
     * @param context
     * @return
     */
    List<LocaleBO> findAllLocalesByStatus(LocaleManagerRequest request, OperateContext context);

    /**
     * 通过场地代号查询场地名称
     *
     * @param request
     * @param context
     * @return
     */
    LocaleBO findLocaleNameByLocaleCode(LocaleManagerRequest request, OperateContext context);
}

/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service;

import us.betahouse.haetae.locale.model.basic.LocaleBO;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface LocaleDORepoService {

    /**
     * 新建一个场地
     *
     * @param localeBO
     * @return
     */
    LocaleBO createLocale(LocaleBO localeBO);

    /**
     * 查询所有场地
     *
     * @return
     */
    List<LocaleBO> queryAllLocales();

    /**
     * 通过状态查询可以用的场地
     *
     * @param status
     * @return
     */
    List<LocaleBO> queryLocalesByStatus(String status);

}

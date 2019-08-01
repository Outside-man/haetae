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
     * @return LocaleBO
     */
    LocaleBO createLocale(LocaleBO localeBO);

    /**
     * 更新场地状态
     *
     * @param localeBO
     * @return LocaleBO
     */
    LocaleBO updateLocale(LocaleBO localeBO);

    /**
     * 查询localeName
     *
     * @param localeCode
     * @return LocaleBO
     */
    LocaleBO findLocaleName(String localeCode);

    /**
     * 通过 状态 查询场地
     *
     * @param status
     * @return List<LocaleBO>
     */
    List<LocaleBO> queryAllLocalesByStatus(String status);
}

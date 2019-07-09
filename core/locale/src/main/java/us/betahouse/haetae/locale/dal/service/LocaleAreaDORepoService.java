/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service;


import us.betahouse.haetae.locale.model.basic.LocaleAreaBO;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface LocaleAreaDORepoService {

    /**
     * 新建一个场地占用
     *
     * @param localeAreaBO
     * @return LocaleAreaBO
     */
    LocaleAreaBO createLocaleArea(LocaleAreaBO localeAreaBO);

    /**
     * @param localeAreaBO
     * @return LocaleAreaBO
     */
    LocaleAreaBO updateLocaleArea(LocaleAreaBO localeAreaBO);

    /**
     * 通过 场地id 日期 状态!=CANCEL 查询场地占用
     *
     * @param LocaleId
     * @param TimeDate
     * @param Status
     * @return List<LocaleAreaBO>
     */
    List<LocaleAreaBO> queryLocaleAreasByLocaleIdAndTimeDateAndStatusNot(String LocaleId, String TimeDate, String Status);
}

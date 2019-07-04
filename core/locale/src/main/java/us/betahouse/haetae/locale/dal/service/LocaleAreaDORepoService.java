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
     * 创建一个场地占用
     *
     * @param localeAreaBO
     * @return
     */
    LocaleAreaBO createLocaleArea(LocaleAreaBO localeAreaBO);

    /**
     * 通过场地id 日期 状态 查询场地占用
     *
     * @param LocaleId
     * @param TimeDate
     * @param Status
     * @return
     */
    List<LocaleAreaBO> queryLocaleAreasByLocaleIdAndTimeDateAndStatusNot(String LocaleId, String TimeDate, String Status);

}

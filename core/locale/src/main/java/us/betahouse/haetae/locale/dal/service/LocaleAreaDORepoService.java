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
     * 更新场地占用状态
     *
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

    /**
     * 校验场地是否被占用
     *
     * @param LocaleId
     * @param TimeDate
     * @param TimeBucket
     * @param Status
     * @return
     */
    LocaleAreaBO queryLocaleAreasByLocaleIdAndTimeDateAndTimeBucketAndStatusNot(String LocaleId, String TimeDate, String TimeBucket, String Status);
}

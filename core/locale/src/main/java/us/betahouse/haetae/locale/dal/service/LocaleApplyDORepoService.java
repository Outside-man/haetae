/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface LocaleApplyDORepoService {

    /**
     * 新建一个申请
     * @param localeApplyBO
     * @return
     */
    LocaleApplyBO createLocaleApply(LocaleApplyBO localeApplyBO);

    /**
     * 查看所有的申请
     *
     * @return
     */
    LocaleApplyBO queryAllLocaleApply();
}

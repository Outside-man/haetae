/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.dal.service;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface LocaleApplyDORepoService {

    /**
     * 新建一个场地申请
     * @param localeApplyBO
     * @return
     */
    LocaleApplyBO createLocaleApply(LocaleApplyBO localeApplyBO);

    /**
     * 查询所有场地申请
     *
     * @return
     */
    List<LocaleApplyBO> queryAllLocaleApply();
}

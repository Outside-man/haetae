/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.manager;

import us.betahouse.haetae.locale.model.basic.LocaleBO;

import java.util.List;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface LocaleManager {
    /**
     * 查询所有场地
     *
     * @return
     */
    List<LocaleBO> findAll();

    /**
     * 通过场地状态查询所有场地
     *
     * @param status
     * @return
     */
    List<LocaleBO> findAllByStatus(String status);
}

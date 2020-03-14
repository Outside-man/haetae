/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.idfactory;


/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public interface BizIdFactory {
    /**
     * 生成场地id
     *
     * @return
     */
    String getLocaleId();

    /**
     * 生成占场地id
     *
     * @return
     */
    String getLocaleAreaId();

    /**
     * 生成申请场地id
     *
     * @return
     */
    String getLocaleApplyId();
}

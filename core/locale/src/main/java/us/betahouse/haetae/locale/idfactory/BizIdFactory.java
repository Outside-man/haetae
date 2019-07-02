package us.betahouse.haetae.locale.idfactory;

import org.springframework.stereotype.Service;

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

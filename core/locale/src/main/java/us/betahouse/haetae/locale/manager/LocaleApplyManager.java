/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.locale.manager;

import us.betahouse.haetae.locale.model.basic.LocaleApplyBO;
import us.betahouse.haetae.locale.request.LocaleApplyRequest;

/**
 * @author NathanDai
 * @version :  2019-07-05 06:28 NathanDai
 */
public interface LocaleApplyManager {
    /**
     * @param request
     * @return
     */
    LocaleApplyBO create(LocaleApplyRequest request);
}

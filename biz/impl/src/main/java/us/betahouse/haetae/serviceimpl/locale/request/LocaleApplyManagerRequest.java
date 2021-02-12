/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.request;

import us.betahouse.haetae.locale.request.LocaleApplyRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * @author NathanDai
 * @version :  2019-07-05 19:26 NathanDai
 */
public class LocaleApplyManagerRequest extends LocaleApplyRequest implements VerifyRequest {
    private static final long serialVersionUID = -8881681206219822609L;

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
}

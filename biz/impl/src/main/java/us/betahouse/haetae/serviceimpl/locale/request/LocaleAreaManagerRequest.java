/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.request;

import us.betahouse.haetae.locale.request.LocaleAreaRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * @author NathanDai
 * @version :  2019-07-05 19:26 NathanDai
 */
public class LocaleAreaManagerRequest extends LocaleAreaRequest implements VerifyRequest {
    private static final long serialVersionUID = -2568737136318069213L;

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
}

/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.request;

import us.betahouse.haetae.asset.request.AssetLoanRecordRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordManagerRequest.java 2019/01/26 0:51 yiyuk.hxy
 */
public class AssetLoanRecordManagerRequest extends AssetLoanRecordRequest implements VerifyRequest {

    private static final long serialVersionUID = 3245100919662928018L;

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
}

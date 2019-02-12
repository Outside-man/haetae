/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.request;

import us.betahouse.haetae.asset.request.AssetBackRecordRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordManagerRequest.java 2019/02/12 17:34 yiyuk.hxy
 */
public class AssetBackRecordManagerRequest extends AssetBackRecordRequest implements VerifyRequest {
    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
}

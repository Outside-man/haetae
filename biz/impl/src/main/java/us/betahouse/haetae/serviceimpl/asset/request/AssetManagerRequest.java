/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.request;

import us.betahouse.haetae.asset.request.AssetRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * @author guofan.cp
 * @version : AssetManagerRequest.java 2019/01/21 9:13 guofan.cp
 */
public class AssetManagerRequest extends AssetRequest implements VerifyRequest {

    private static final long serialVersionUID = 3245100919662928018L;

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
}

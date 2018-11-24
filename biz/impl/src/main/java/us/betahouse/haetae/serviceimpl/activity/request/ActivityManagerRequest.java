/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request;

import us.betahouse.haetae.activity.request.ActivityRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * @author MessiahJK
 * @version : ActivityManagerRequest.java 2018/11/24 22:07 MessiahJK
 */
public class ActivityManagerRequest extends ActivityRequest implements VerifyRequest {
    private static final long serialVersionUID = -7830970003881573855L;

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
}

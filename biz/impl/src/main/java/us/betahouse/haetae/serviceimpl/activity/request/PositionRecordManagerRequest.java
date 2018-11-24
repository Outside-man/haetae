/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request;

import us.betahouse.haetae.activity.request.PositionRecordRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * @author MessiahJK
 * @version : PositionRecordManagerRequest.java 2018/11/25 0:23 MessiahJK
 */
public class PositionRecordManagerRequest extends PositionRecordRequest implements VerifyRequest {
    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
    private String userId;

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }
}

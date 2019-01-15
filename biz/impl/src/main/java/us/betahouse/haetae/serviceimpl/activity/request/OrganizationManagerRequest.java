/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.request;

import us.betahouse.haetae.activity.request.OrganizationRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

/**
 * @author MessiahJK
 * @version : OrganizationManagerRequest.java 2018/11/25 0:14 MessiahJK
 */
public class OrganizationManagerRequest extends OrganizationRequest implements VerifyRequest {

    /**
     * 用户id
     */
    private String userId;

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

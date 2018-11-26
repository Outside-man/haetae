/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.operate;

import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;
import us.betahouse.util.common.ToString;

/**
 * 活动操作请求
 *
 * @author dango.yxm
 * @version : ActivityOperationRequest.java 2018/11/26 3:10 PM dango.yxm
 */
public class ActivityOperationRequest extends ToString implements VerifyRequest {

    private static final long serialVersionUID = 2793355343723310133L;

    private ActivityBO activity;

    private String userId;

    @Override
    public String getVerifyUserId() {
        return userId;
    }

    public ActivityBO getActivity() {
        return activity;
    }

    public void setActivity(ActivityBO activity) {
        this.activity = activity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

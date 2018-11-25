/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.user.request;

import us.betahouse.haetae.common.RestRequest;

/**
 * 用户活动章请求
 *
 * @author dango.yxm
 * @version : UserActivityStampRequest.java 2018/11/25 1:10 PM dango.yxm
 */
public class UserActivityStampRequest extends RestRequest {

    private static final long serialVersionUID = -8891245952523574347L;

    /**
     * 学期
     */
    private String term;

    /**
     * 活动类型
     */
    private String activityType;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}

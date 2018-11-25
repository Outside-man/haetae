/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.model.user.request;

import us.betahouse.haetae.common.RestRequest;

import java.util.List;

/**
 * 活动盖章请求
 *
 * @author dango.yxm
 * @version : StamperRequest.java 2018/11/25 3:32 PM dango.yxm
 */
public class StamperRequest extends RestRequest {

    private static final long serialVersionUID = -8891245952523574347L;

    /**
     * 参与者
     */
    private List<String> participants;

    /**
     * 活动类型
     */
    private String activityId;

    /**
     * 盖章员id 赋权时使用
     */
    private String scannerUserId;

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getScannerUserId() {
        return scannerUserId;
    }

    public void setScannerUserId(String scannerUserId) {
        this.scannerUserId = scannerUserId;
    }
}

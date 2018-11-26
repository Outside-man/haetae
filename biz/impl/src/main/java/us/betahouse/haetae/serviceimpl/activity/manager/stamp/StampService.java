/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.stamp;

import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;


/**
 * 章服务
 *
 * @author dango.yxm
 * @version : StampService.java 2018/11/27 1:49 AM dango.yxm
 */
public interface StampService {

    /**
     * 查询章记录
     *
     * @param userId
     * @param term
     * @return
     */
    StampRecord getStampRecord(String userId, String term);

    /**
     * 查询章记录
     *
     * @param userId
     * @return
     */
    default StampRecord getStampRecord(String userId) {
        return getStampRecord(userId, null);
    }

    /**
     * 批量盖章
     *
     * @param request
     * @return
     */
    void batchStamp(ActivityStampRequest request);
}

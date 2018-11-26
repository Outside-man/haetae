/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager;

import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;

import java.util.List;

/**
 * 章管理器
 *
 * @author dango.yxm
 * @version : StampManager.java 2018/11/27 1:40 AM dango.yxm
 */
public class StampManager {


    /**
     * 查询章记录
     *
     * @param userId
     * @param activityType
     * @param term
     * @return
     */
    StampRecord getStampRecord(String userId, String activityType, String term) {
        return null;
    }


    /**
     * 批量盖章
     *
     * @param activityId
     * @param userIds
     * @return
     */
    void batchStamp(String activityId, List<String> userIds) {

    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.manager.stamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityStamp;
import us.betahouse.haetae.serviceimpl.activity.model.StampRecord;
import us.betahouse.haetae.serviceimpl.activity.request.ActivityStampRequest;

import java.util.List;


/**
 * 章服务
 *
 * @author dango.yxm
 * @version : StampService.java 2018/11/27 1:49 AM dango.yxm
 */
public interface StampService {

    Logger LOGGER = LoggerFactory.getLogger(StampService.class);

    /**
     * 解析章记录
     *
     * @param activityStamps
     * @return
     */
    default StampRecord parseStampRecord(List<ActivityStamp> activityStamps){
        return new StampRecord(activityStamps);
    }

    /**
     * 批量盖章
     *
     * @param request
     * @param userIds
     */
    void batchStamp(ActivityStampRequest request, List<String> userIds, ActivityBO activityBO);
}

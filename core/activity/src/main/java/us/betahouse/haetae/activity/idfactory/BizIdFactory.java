/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.idfactory;

import org.springframework.stereotype.Service;

/**
 * 业务id生成工厂
 * 生成32位业务id
 *
 * @author MessiahJK
 * @version : BizldFactory.java 2018/11/17 17:26 MessiahJK
 */
@Service("activityBizFactory")
public interface BizIdFactory {

    /**
     * 生成活动id
     *
     * @return
     */
    String getActivityId();

    /**
     * 生成活动记录id
     *
     * @return
     */
    String getActivityRecordId();

    /**
     * 生成履历id
     *
     * @return
     */
    String getPositionRecordId();


    String getPastActivityId();

    /**
     * 生成活动报名信息id
     * @return
     */
    String getActivityEntryId();

    /**
     * 生成活动报名记录id
     * @return
     */
    String getActivityEntryRecordId();

    /**
     * 生成活动黑名单id
     * @return
     */
    String getActivityBlacklistId();
}

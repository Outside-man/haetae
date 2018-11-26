/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.utils;

import us.betahouse.haetae.activity.model.ActivityBO;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.util.utils.AssertUtil;

import java.util.Date;

/**
 * @author MessiahJK
 * @version : ActivityUtil.java 2018/11/24 19:32 MessiahJK
 */
public class ActivityUtil {

    /**
     * 活动是否结束
     *
     * @param activityBO
     * @return
     */
    public static boolean isFinish(ActivityBO activityBO) {
        Date now = new Date();
        return (activityBO.getState().equals(ActivityStateEnum.APPROVED.getDesc()) || activityBO.getState().equals(ActivityStateEnum.CANCELED.getDesc())) && now.after(activityBO.getEnd());
    }
}

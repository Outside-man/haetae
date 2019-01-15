/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.model;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.util.common.ToString;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 活动记录统计
 *
 * @author dango.yxm
 * @version : ActivityRecordStatistics.java 2018/12/30 1:30 PM dango.yxm
 */
public class ActivityRecordStatistics extends ToString {

    private static final long serialVersionUID = -2296411708310504839L;

    /**
     * 没有参与记录
     */
    private final static int NOT_EXIST_RECORD = 0;

    private final static int ONE = 1;

    /**
     * 志愿时长
     */
    final static String VOLUNTEER_ACTIVITY_TIME = "volunteerActivityTime";

    /**
     * 义工时长
     */
    final static String VOLUNTEER_WORK_TIME = "volunteerWorkTime";

    /**
     * 用户id
     */
    private String userId;

    /**
     * 学号
     */
    private String stuId;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 记录统计
     */
    private Map<String, Integer> statistics;

    /**
     * 初始化统计key
     */
    public void initStatisticsKey() {
        if (statistics == null) {
            statistics = new HashMap<>(16);
        }
        // 将活动类型都放入
        for (ActivityTypeEnum activityType : ActivityTypeEnum.values()) {
            statistics.put(activityType.getCode(), NOT_EXIST_RECORD);
        }
        // 将统计时长也计入
        statistics.put(VOLUNTEER_ACTIVITY_TIME, NOT_EXIST_RECORD);
        statistics.put(VOLUNTEER_WORK_TIME, NOT_EXIST_RECORD);
    }

    /**
     * 存入记录
     *
     * @param activityType
     * @param recordCount
     */
    public void putStatistics(String activityType, int recordCount) {
        AssertUtil.assertStringNotBlank(activityType);
        if (statistics == null) {
            statistics = new HashMap<>();
        }
        statistics.put(activityType, recordCount);
    }

    /**
     * 读取记录
     *
     * @param activityType
     * @return
     */
    public int fetchStatistics(String activityType) {
        AssertUtil.assertStringNotBlank(activityType);

        if (statistics == null) {
            return NOT_EXIST_RECORD;
        } else {
            Integer recordCount = statistics.get(activityType);
            return recordCount == null ? NOT_EXIST_RECORD : recordCount;
        }
    }

    /**
     * 记录增加次数
     *
     * @param activityType
     */
    public void addStatistics(String activityType) {
        putStatistics(activityType, fetchStatistics(activityType) + ONE);
    }

    /**
     * 记录增加时长
     *
     * @param activityType
     * @param time
     */
    public void addStatisticsTime(String activityType, int time) {
        // 志愿统计时长
        if (StringUtils.equals(activityType, ActivityTypeEnum.VOLUNTEER_ACTIVITY.getCode())) {
            putStatistics(VOLUNTEER_ACTIVITY_TIME, fetchStatistics(VOLUNTEER_ACTIVITY_TIME) + time);
            return;
        }

        // 义工统计时长
        if (StringUtils.equals(activityType, ActivityTypeEnum.VOLUNTEER_WORK.getCode())) {
            putStatistics(VOLUNTEER_WORK_TIME, fetchStatistics(VOLUNTEER_WORK_TIME) + time);
            return;
        }
        throw new BetahouseException("该活动类型无法统计时长");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }

    public void setStatistics(Map<String, Integer> statistics) {
        this.statistics = statistics;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}

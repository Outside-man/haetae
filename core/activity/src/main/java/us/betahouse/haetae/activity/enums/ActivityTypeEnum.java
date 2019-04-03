/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 活动类型
 *
 * @author dango.yxm
 * @version : ActivityTypeEnum.java 2018/11/25 7:52 PM dango.yxm
 */
public enum ActivityTypeEnum {

    /**
     * 校园活动
     */
    SCHOOL_ACTIVITY("schoolActivity", "校园活动"),

    /**
     * 讲座活动
     */
    LECTURE_ACTIVITY("lectureActivity", "讲座活动"),

    /**
     * 志愿活动
     */
    VOLUNTEER_ACTIVITY("volunteerActivity", "志愿活动"),

    /**
     * 实践活动
     */
    PRACTICE_ACTIVITY("practiceActivity", "实践活动"),

    /**
     * 义工活动
     */
    VOLUNTEER_WORK("volunteerWork", "义工"),

    /**
     * 党员活动
     */
    PARTY_ACTIVITY("partyActivity","党员活动"),

    /**
     * 党员时间活动
     */
    PARTY_TIME_ACTIVITY("partyTimeActivity","党员时间活动"),
    ;

    private String code;

    private String desc;

    public static ActivityTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityTypeEnum activityTypeEnum : values()) {
            if (StringUtils.equals(activityTypeEnum.getCode(), code)) {
                return activityTypeEnum;
            }
        }
        return null;
    }

    ActivityTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

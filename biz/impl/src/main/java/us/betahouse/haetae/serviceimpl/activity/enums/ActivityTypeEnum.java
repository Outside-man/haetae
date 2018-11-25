/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 活动类型
 *
 * @author dango.yxm
 * @version : ActivityTypeEnum.java 2018/11/25 7:52 PM dango.yxm
 */
public enum ActivityTypeEnum {

    SCHOOL_ACTIVITY("SCHOOL_ACTIVITY", "校园活动"),

    VOLUNTEER_ACTIVITY("VOLUNTEER_ACTIVITY", "志愿活动"),

    PRACTICE_ACTIVITY("PRACTICE_ACTIVITY", "实践活动"),

    VOLUNTEER_WORK("VOLUNTEER_WORK", "义工"),
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

package us.betahouse.haetae.activity.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author zjb
 * @version : ActivityBlacklistReasonEnum.java 2019/8/3 16:12 zjb
 */
public enum ActivityBlacklistReasonEnum {

    /**
     * 缺席
     */
    ABSENCE("ABSENCE","absence");

    /**
     * 原因id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static ActivityBlacklistReasonEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityBlacklistReasonEnum activityBlacklistReasonEnum : values()) {
            if (StringUtils.equals(activityBlacklistReasonEnum.getCode(), code)) {
                return activityBlacklistReasonEnum;
            }
        }
        return null;
    }

    ActivityBlacklistReasonEnum(String code, String desc) {
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

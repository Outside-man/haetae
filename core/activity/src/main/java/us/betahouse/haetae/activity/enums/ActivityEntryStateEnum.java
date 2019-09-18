package us.betahouse.haetae.activity.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 活动报名状态枚举类
 *
 * @author zjb
 * @version : ActivityEntryStateEnum.java 2019/7/7 15:03 zjb
 */
public enum ActivityEntryStateEnum {

    /**
     * 待发布
     */
    APPROVED("APPROVED", "待发布"),

    /**
     * 发布
     */
    PUBLISHED("PUBLISHED", "发布"),

    /**
     * 结束
     */
    FINISHED("FINISHED", "结束"),

    /**
     * 取消
     */
    CANCELED("CANCELED", "取消");


    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static ActivityEntryStateEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityEntryStateEnum activityEntryStateEnum : values()) {
            if (StringUtils.equals(activityEntryStateEnum.getCode(), code)) {
                return activityEntryStateEnum;
            }
        }
        return null;
    }

    ActivityEntryStateEnum(String code, String desc) {
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

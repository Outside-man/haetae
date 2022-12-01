package us.betahouse.haetae.serviceimpl.activity.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 活动报名状态枚举类
 *
 * @author zjb
 * @version : ActivityEntryStatusType.java
 */
public enum ActivityEntryStatusType {

    /**
     * 待发布
     */
    APPROVED("APPROVED", "待发布"),

    /**
     * 倒计时
     */
    COUNTDOWN("COUNTDOWN", "倒计时"),

    /**
     * 报名
     */
    REGISTRATION("REGISTRATION", "报名"),

    /**
     * 已报名
     */
    REGISTERED("REGISTERED", "已报名"),

    /**
     * 取消报名
     */
    CANCEL_REGISTERED("CANCEL_REGISTERED", "取消报名"),

    /**
     * 人已满
     */
    EXCEED("EXCEED", "人已满"),

    /**
     * 已结束
     */
    FINISHED("FINISHED", "已结束"),

    /**
     * 已取消
     */
    CANCELED("CANCELED", "已取消");


    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static ActivityEntryStatusType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityEntryStatusType activityEntryStatusType : values()) {
            if (StringUtils.equals(activityEntryStatusType.getCode(), code)) {
                return activityEntryStatusType;
            }
        }
        return null;
    }

    ActivityEntryStatusType(String code, String desc) {
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

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.status.activitystatus;

import org.apache.commons.lang.StringUtils;

/**
 * 活动状态枚举类
 *
 * @author MessiahJK
 * @version : ActivityStateEnum.java 2018/11/23 0:58 MessiahJK
 */
public enum ActivityStateEnum {

    /**
     * 通过
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
    CANCELED("CANCELED", "取消"),

    /**
     * 重启
     */
    RESTORE("RESTORE", "重启");

    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static ActivityStateEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityStateEnum activityStateEnum : values()) {
            if (StringUtils.equals(activityStateEnum.getCode(), code)) {
                return activityStateEnum;
            }
        }
        return null;
    }

    ActivityStateEnum(String code, String desc) {
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

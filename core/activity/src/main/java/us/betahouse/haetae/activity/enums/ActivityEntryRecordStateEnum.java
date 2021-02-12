package us.betahouse.haetae.activity.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 活动报名状态枚举类
 *
 * @author zjb
 * @version : ActivityEntryRecordStateEnum.java 2020/3/14 10:12 zjb
 */
public enum ActivityEntryRecordStateEnum {

    /**
     * 报名
     */
    SIGN_UP("SIGN_UP", "报名"),

    /**
     * 取消报名
     */
    UNDO_SIGN_UP("UNDO_SIGN_UP", "取消报名");



    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static ActivityEntryRecordStateEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityEntryRecordStateEnum activityEntryStateEnum : values()) {
            if (StringUtils.equals(activityEntryStateEnum.getCode(), code)) {
                return activityEntryStateEnum;
            }
        }
        return null;
    }

    ActivityEntryRecordStateEnum(String code, String desc) {
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

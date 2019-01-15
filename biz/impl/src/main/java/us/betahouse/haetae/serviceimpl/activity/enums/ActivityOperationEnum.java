/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 活动操作枚举
 *
 * @author dango.yxm
 * @version : ActivityOperationEnum.java 2018/11/26 2:00 PM dango.yxm
 */
public enum ActivityOperationEnum {

    CANCEL("cancel", "取消"),

    PUBLISH("publish", "发布"),

    FINISH("finish", "结束"),

    RESTART("restart", "重启"),;

    public static ActivityOperationEnum getByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (ActivityOperationEnum activityOperationEnum : values()) {
                if (StringUtils.equals(activityOperationEnum.getCode(), code)) {
                    return activityOperationEnum;
                }
            }
        }
        return null;
    }

    private String code;

    private String desc;

    ActivityOperationEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }
}

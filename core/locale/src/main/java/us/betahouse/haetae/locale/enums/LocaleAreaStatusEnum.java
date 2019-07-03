/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public enum LocaleAreaStatusEnum {
    /**
     * 提交
     */
    SUBMITING("SUBMITING", "提交"),
    /**
     * 申请中
     */
    APPLYING("APPLYING", "申请中"),
    /**
     * 已取消
     */
    CANCEL("CANCEL", "已取消");

    public static LocaleAreaStatusEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (LocaleAreaStatusEnum localeAreaStatusEnum : values()) {
            if (StringUtils.equals(localeAreaStatusEnum.getCode(), code)) {
                return localeAreaStatusEnum;
            }
        }
        return null;
    }

    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    LocaleAreaStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

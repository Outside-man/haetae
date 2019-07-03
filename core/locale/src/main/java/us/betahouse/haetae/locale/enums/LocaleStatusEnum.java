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

public enum LocaleStatusEnum {
    /**
     * 可用
     */
    USABLE("USABLE", "可用"),
    /**
     * 不可用
     */
    DISABLE("DISABLE", "不可用");
    /**
     * 状态id
     */
    private final String code;
    /**
     * 描述
     */
    private final String desc;

    public static LocaleStatusEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (LocaleStatusEnum localeStatusEnum : values()) {
            if (StringUtils.equals(localeStatusEnum.getCode(), code)) {
                return localeStatusEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    LocaleStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

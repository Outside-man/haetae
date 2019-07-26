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

public enum LocaleApplyStatusEnum {
    /**
     * 申请提交
     */
    COMMIT("COMMIT", "申请提交"),
    /**
     * 学工审批
     */
    FIRST("FIRST", "学工审批"),
    /**
     * 团学审批
     */
    PASS("PASS", "团学审批"),
    /**
     * 已取消
     */
    CANCEL("CANCEL", "已取消");

    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static LocaleApplyStatusEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (LocaleApplyStatusEnum localeApplyStatusEnum : values()) {
            if (StringUtils.equals(localeApplyStatusEnum.getCode(), code)) {
                return localeApplyStatusEnum;
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

    LocaleApplyStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

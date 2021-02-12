/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author MessiahJK
 * @version : MoneyRecordTypeEnum.java 2019/02/21 20:58 MessiahJK
 */
public enum MoneyRecordTypeEnum {
    /**
     * 负
     */
    negative("negative","负"),

    /**
     * 正
     */
    positive("positive","正");
    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static MoneyRecordTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (MoneyRecordTypeEnum moneyRecordTypeEnum : values()) {
            if (StringUtils.equals(moneyRecordTypeEnum.getCode(), code)) {
                return moneyRecordTypeEnum;
            }
        }
        return null;
    }

    MoneyRecordTypeEnum(String code, String desc) {
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

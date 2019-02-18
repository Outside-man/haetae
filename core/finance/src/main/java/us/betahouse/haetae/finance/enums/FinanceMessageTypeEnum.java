/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author MessiahJK
 * @version : FinanceMessageType.java 2019/02/18 19:37 MessiahJK
 */
public enum FinanceMessageTypeEnum {

    /**
     * 未审核
     */
    UNAUDITED("unaudited","未审核"),

    /**
     * 已审核
     */
    AUDITED("audited","已审核"),

    /**
     * 未通过
     */
    AUDITED_FAIL("audited_fail","审核未通过"),

    /**
     * 已核销
     */
    CHECKED("checked","已核销");



    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static FinanceMessageTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (FinanceMessageTypeEnum financeMessageTypeEnum : values()) {
            if (StringUtils.equals(financeMessageTypeEnum.getCode(), code)) {
                return financeMessageTypeEnum;
            }
        }
        return null;
    }

    FinanceMessageTypeEnum(String code, String desc) {
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

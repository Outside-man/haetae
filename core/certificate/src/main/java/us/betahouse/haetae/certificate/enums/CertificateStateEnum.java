/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 证书状态枚举类
 *
 * @author guofan.cp
 * @version : CertificateStateEnum.java 2019/04/05 8:06 guofan.cp
 */
public enum CertificateStateEnum {
    /**
     * 通过
     */
    APPROVED("APPROVED", "通过"),
    /**
     * 未审核
     */
    UNREVIEWED("UNREVIEWED", "未审核"),
    /**
     * 驳回
     */
    REJECTED("REJECTED", "驳回");
    
    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static CertificateStateEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (CertificateStateEnum activityStateEnum : values()) {
            if (StringUtils.equals(activityStateEnum.getCode(), code)) {
                return activityStateEnum;
            }
        }
        return null;
    }

    CertificateStateEnum(String code, String desc) {
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

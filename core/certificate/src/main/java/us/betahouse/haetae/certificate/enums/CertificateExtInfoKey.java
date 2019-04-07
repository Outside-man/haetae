/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 证书拓展字段
 *
 * @author guofan.cp
 * @version : CertificateRecordExtInfoKey.java 2019/04/05 8:26 guofan.cp
 */
public enum CertificateExtInfoKey {
    /**
     * 证书详细信息
     */
    DESCRIPT("Descript", "详细描述"),
    /**
     * 教师资格
     */
    TEACHER_LEVEL("TeacherLevel", "教师资格"),
    /**
     * 学科教师类型
     */
    TEACHER_SUBJECT("TeacherSubject", "教师学科");
    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static CertificateExtInfoKey getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (CertificateExtInfoKey activityStateEnum : values()) {
            if (StringUtils.equals(activityStateEnum.getCode(), code)) {
                return activityStateEnum;
            }
        }
        return null;
    }

    CertificateExtInfoKey(String code, String desc) {
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

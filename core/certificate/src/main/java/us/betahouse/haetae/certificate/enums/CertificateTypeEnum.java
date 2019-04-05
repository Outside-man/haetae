/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 证书类型枚举类
 *
 * @author guofan.cp
 * @version : CertificateTypeEnum.java 2019/04/05 8:21 guofan.cp
 */
public enum CertificateTypeEnum {

    /**
     * 资格证书
     */
    QUALIFICATIONS("QUALIFICATIONS", "资格证书"),
    /**
     * 竞赛证书
     */
    COMPETITION("COMPETITION", "竞赛证书"),
    /**
     * 技能证书
     */
    SKILL("SKILL", "技能证书"),
    /**
     * 资格证书(普通类型)
     */
    NORMAL_QUALIFICATIONS("Normal", "普通资格证书"),
    /**
     * 资格证书(acca/cfa) 国际性
     */
    INTERNATIONAL_QUALIFICATIONS("International", "国际资格证书"),
    /**
     * 资格证书(教师资格)
     */
    TEACHER_QUALIFICATIONS("Teacher", "教师资格证书"),
    /**
     * 竞赛证书(个人)
     */
    PERSOMAL_COMPETITION("Personal", "个人竞赛"),
    /**
     * 竞赛证书(团队)
     */
    TEAM_COMPETITION("Team", "团队竞赛");
    /**
     * 状态id
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;

    public static CertificateTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (CertificateTypeEnum activityStateEnum : values()) {
            if (StringUtils.equals(activityStateEnum.getCode(), code)) {
                return activityStateEnum;
            }
        }
        return null;
    }

    CertificateTypeEnum(String code, String desc) {
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

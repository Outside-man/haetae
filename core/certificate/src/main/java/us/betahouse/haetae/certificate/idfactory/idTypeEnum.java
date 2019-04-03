/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.certificate.idfactory;

/**
 * @author guofan.cp
 * @version : idTypeEnum.java 2019/04/02 14:19 guofan.cp
 */
public enum idTypeEnum {
    CERTIFICATE_ID("5001", "资格证书id"),
    COMPETITON_ID("5002", "竞赛证书id"),
    SKILL_ID("5003", "技能证书id");
    /**
     * @Description: 描述
     * @param:
     */
    private final String desc;
    /**
     * @Description: 业务id
     * @param:
     */
    private final String bizNum;

    idTypeEnum(String bizNum, String desc) {
        this.desc = desc;
        this.bizNum = bizNum;
    }

    public String getDesc() {
        return desc;
    }

    public String getBizNum() {
        return bizNum;
    }
}

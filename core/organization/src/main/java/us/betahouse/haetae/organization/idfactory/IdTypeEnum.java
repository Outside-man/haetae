/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.organization.idfactory;

/**
 * Id类型枚举
 *
 * @author dango.yxm
 * @version : IdTypeEnum.java 2018/10/06 下午1:25 dango.yxm
 */
public enum IdTypeEnum {

    ORGANIZATION_ID("0011", "组织id"),

    ORGANIZATION_MEMBER_ID("0012", "组织成员关系id"),

    ;
    /**
     * 业务id
     */
    private final String bizNum;

    /**
     * 描述
     */
    private final String desc;

    IdTypeEnum(String bizNum, String desc) {
        this.bizNum = bizNum;
        this.desc = desc;
    }

    public String getBizNum() {
        return bizNum;
    }

    public String getDesc() {
        return desc;
    }
}

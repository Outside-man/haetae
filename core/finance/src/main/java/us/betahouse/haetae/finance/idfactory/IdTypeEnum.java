/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.finance.idfactory;

/**
 * @author MessiahJK
 * @version : IdTypeEnum.java 2019/02/14 15:13 MessiahJK
 */
public enum IdTypeEnum {

    /**
     * 财务信息id
     */
    Finance_Message_Id("3001","财务信息id"),

    /**
     * 财务统计id
     */
    Finance_Total_Id("3002","财务统计id");

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

/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.idfactory;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public enum IdTypeEnum {
    /**
     * 场地id
     */
    Locale_Id("4001", "场地id"),
    Locale_Area_Id("4002", "占场地id"),
    Locale_Apply_Id("4003", "申请场地id");
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

/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.idfactory;

/**
 * Id类型枚举
 *
 * @author MessiahJK
 * @version : IdTypeEnum.java 2018/11/17 16:03 MessiahJK
 */
public enum IdTypeEnum {

    /**
     * 活动id
     */
    Activity_ID("1001", "活动id"),

    /**
     * 活动记录id
     */
    Activity_Record_ID("1002", "活动记录id"),


    /**
     * 履历id
     */
    Position_Record_ID("1004", "履历id"),;


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

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.enums;

/**
 * 活动章状态
 *
 * @author dango.yxm
 * @version : ActivityStampStatusEnum.java 2018/11/25 3:38 PM dango.yxm
 */
public enum ActivityStampStatusEnum {

    ENABLE("生效"),

    DISABLE("无效");

    private String desc;

    ActivityStampStatusEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return name();
    }
}

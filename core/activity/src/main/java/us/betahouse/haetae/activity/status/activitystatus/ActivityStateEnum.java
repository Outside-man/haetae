/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.activity.status.activitystatus;

/**
 * 活动状态枚举类
 *
 * @author MessiahJK
 * @version : ActivityStateEnum.java 2018/11/23 0:58 MessiahJK
 */
public enum ActivityStateEnum {
    APPROVED("2001", "通过"),

    PUBLISHED("2002", "发布"),

    FINISHED("2003", "结束"),

    CANCELED("2004", "取消"),

    RESTORE("2005","重启")
    ;
    /**
     * 状态id
     */
    private final String ActivityState;

    /**
     * 描述
     */
    private final String desc;

    ActivityStateEnum(String ActivityState, String desc) {
        this.ActivityState = ActivityState;
        this.desc = desc;
    }

    public String getActivityState() {
        return ActivityState;
    }

    public String getDesc() {
        return desc;
    }
}

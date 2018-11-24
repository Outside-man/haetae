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
    /**
     * 通过
     */
    APPROVED("2001", "Approved"),

    /**
     * 发布
     */
    PUBLISHED("2002", "Canceled"),

    /**
     * 结束
     */
    FINISHED("2003", "Finished"),

    /**
     * 取消
     */
    CANCELED("2004", "Published"),

    /**
     * 重启
     */
    RESTORE("2005","Restore")
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

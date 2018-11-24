/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.serviceimpl.activity.constant.ActivityPermType;
import us.betahouse.haetae.user.enums.PermType;

/**
 * 权限活动类型
 *
 * @author dango.yxm
 * @version : ActivityPermTypeEnum.java 2018/11/24 7:15 PM dango.yxm
 */
public enum ActivityPermTypeEnum implements PermType {

    ACTIVITY_CREATE(ActivityPermType.ACTIVITY_CREATE, "活动创建"),

    ACTIVITY_PUBLISH(ActivityPermType.ACTIVITY_PUBLISH, "活动发布"),

    ACTIVITY_FINISH(ActivityPermType.ACTIVITY_FINISH, "活动结束"),

    ACTIVITY_RESTART(ActivityPermType.ACTIVITY_RESTART, "活动重启"),

    STAMPER_MANAGE(ActivityPermType.STAMPER_MANAGE, "盖章员管理"),

    ACTIVITY_STAMPER(ActivityPermType.ACTIVITY_STAMPER, "活动盖章"),

    ;

    private String code;

    private String desc;

    public static ActivityPermTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityPermTypeEnum permType : values()) {
            if (StringUtils.equals(permType.getCode(), code)) {
                return permType;
            }
        }
        return null;
    }

    ActivityPermTypeEnum(String code, String name) {
        this.code = code;
        this.desc = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}

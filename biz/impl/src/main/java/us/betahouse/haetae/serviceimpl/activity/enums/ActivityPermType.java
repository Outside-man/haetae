/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.user.enums.PermType;

/**
 * 权限活动类型
 *
 * @author dango.yxm
 * @version : ActivityPermType.java 2018/11/24 7:15 PM dango.yxm
 */
public enum ActivityPermType implements PermType {

    ACTIVITY_CREATE("ACTIVITY_CREATE", "活动创建"),

    ACTIVITY_PUBLISH("ACTIVITY_PUBLISH", "活动发布"),

    ACTIVITY_FINISH("ACTIVITY_FINISH", "活动结束"),

    ACTIVITY_RESTART("ACTIVITY_RESTART", "活动重启"),

    STAMPER_MANAGE("STAMPER_MANAGE", "盖章员管理"),

    ACTIVITY_STAMPER("ACTIVITY_STAMPER", "活动盖章"),

    ;

    private String code;

    private String desc;

    public static ActivityPermType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityPermType permType : values()) {
            if (StringUtils.equals(permType.getCode(), code)) {
                return permType;
            }
        }
        return null;
    }

    ActivityPermType(String code, String name) {
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

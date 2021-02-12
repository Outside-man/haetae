/*
  betahouse.us
  CopyRight (c) 2012 - 2018
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

    //=====校园活动 权限相关=======

    ACTIVITY_CREATE(ActivityPermType.ACTIVITY_CREATE, "活动创建", true),

    ACTIVITY_PUBLISH(ActivityPermType.ACTIVITY_PUBLISH, "活动发布", true),

    ACTIVITY_FINISH(ActivityPermType.ACTIVITY_FINISH, "活动结束", true),

    ACTIVITY_RESTART(ActivityPermType.ACTIVITY_RESTART, "活动重启", true),

    STAMPER_MANAGE(ActivityPermType.STAMPER_MANAGE, "盖章员管理", true),

    ACTIVITY_STAMPER(ActivityPermType.ACTIVITY_STAMPER, "活动盖章", false),

    //=====义工 权限相关=======

    VOLUNTEER_WORK_CREATE(ActivityPermType.ACTIVITY_CREATE, "活动创建", true),

    VOLUNTEER_WORK_PUBLISH(ActivityPermType.ACTIVITY_PUBLISH, "活动发布", true),

    VOLUNTEER_WORK_FINISH(ActivityPermType.ACTIVITY_FINISH, "活动结束", true),

    VOLUNTEER_WORK_RESTART(ActivityPermType.ACTIVITY_RESTART, "活动重启", true),

    VOLUNTEER_WORK_MANAGE(ActivityPermType.STAMPER_MANAGE, "盖章员管理", true),

    VOLUNTEER_WORK_STAMPER(ActivityPermType.ACTIVITY_STAMPER, "活动盖章", false),;

    private String code;

    private String desc;

    private boolean init;

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

    ActivityPermTypeEnum(String code, String desc, boolean init) {
        this.code = code;
        this.desc = desc;
        this.init = init;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public boolean isInit() {
        return init;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.user.enums.RoleCode;

/**
 * 活动角色类型
 *
 * @author dango.yxm
 * @version : ActivityRoleCodeEnum.java 2018/11/24 7:21 PM dango.yxm
 */
public enum ActivityRoleCodeEnum implements RoleCode {

    /**
     * 活动管理者 管理活动
     */
    ACTIVITY_MANAGER("ACTIVITY_MANAGER", "活动管理员"),

    /**
     * 活动盖章员 盖章入口
     */
    ACTIVITY_STAMPER("ACTIVITY_STAMPER", "活动盖章员"),

    ;

    public static ActivityRoleCodeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityRoleCodeEnum roleCode : values()) {
            if (StringUtils.equals(roleCode.getCode(), code)) {
                return roleCode;
            }
        }
        return null;
    }

    private String code;

    private String desc;


    ActivityRoleCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
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

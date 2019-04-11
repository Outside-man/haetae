/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.user.enums.RoleCode;

/**
 * 用户权限码
 *
 * @author dango.yxm
 * @version : UserRoleCode.java 2018/11/24 7:26 PM dango.yxm
 */
public enum UserRoleCode implements RoleCode {

    /**
     * 活动管理者 管理活动
     */
    ACTIVITY_MANAGER("ACTIVITY_MANAGER", "活动管理员"),

    /**
     * 党建活动管理员
     */
    PARTY_ACTIVITY_MANAGER("PARTY_ACTIVITY_MANAGER","党建活动管理员"),

    /**
     * 义工活动管理员
     */
    VOLUNTEER_WORK_MANAGER("VOLUNTEER_WORK_MANAGER","义工活动管理员"),

    /**
     * 志愿活动管理员
     */
    VOLUNTEER_ACTIVITY_MANAGER("VOLUNTEER_ACTIVITY_MANAGER","志愿活动管理员"),

    /**
     * 社会实践管理员
     */
    PRACTICE_ACTIVITY_MANAGER("PRACTICE_ACTIVITY_MANAGER","社会实践管理员"),

    /**
     * 活动盖章员 盖章入口
     */
    ACTIVITY_STAMPER("ACTIVITY_STAMPER", "活动盖章员"),

    /**
     * 物资管理者 管理物资
     */
    ASSET_MANAGER("ASSET_MANAGER", "物资管理员"),

    /**
     * 组织管理员 管理所有组织相关
     */
    ORGANIZATION_MANAGER("ORGANIZATION_MANAGER", "组织管理员"),

    /**
     * 非学生用户
     */
    NOT_STUDENT("NOT_STUDENT", "非学生用户"),
    ;

    public static UserRoleCode getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (UserRoleCode roleCode : values()) {
            if (StringUtils.equals(roleCode.getCode(), code)) {
                return roleCode;
            }
        }
        return null;
    }

    private String code;

    private String desc;


    UserRoleCode(String code, String desc) {
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

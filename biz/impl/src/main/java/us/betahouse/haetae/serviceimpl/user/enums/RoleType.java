/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 角色类型
 *
 * @author dango.yxm
 * @version : RoleType.java 2018/11/20 11:23 PM dango.yxm
 */
public enum RoleType {

    /**
     * 普通学生
     */
    STUDENT("STUDENT", "普通学生"),

    /**
     * 活动管理者 管理活动
     */
    ACTIVITY_MANAGER("ACTIVITY_MANAGER", "活动管理员"),

    /**
     * 活动扫码员 拥有扫码员入口
     */
    ACTIVITY_SCANNER("ACTIVITY_SCANNER", "活动扫码员"),

    ;
    private String code;

    private String name;

    public static RoleType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (RoleType roleType : values()) {
            if (StringUtils.equals(roleType.getCode(), code)) {
                return roleType;
            }
        }
        return null;
    }

    RoleType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

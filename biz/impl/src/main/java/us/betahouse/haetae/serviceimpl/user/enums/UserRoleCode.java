/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
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

    STUDENT("STUDENT", "STUDENT"),

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

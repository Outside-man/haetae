/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.organization.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 成员类型
 *
 * @author dango.yxm
 * @version : MemberType.java 2019/03/25 23:48 dango.yxm
 */
public enum MemberType {
    PRINCIPAL("PRINCIPAL", "负责人"),

    ADMIN("ADMIN", "管理员"),

    MEMBER("MEMBER", "成员"),

    ASSOCIATION_LEADER("ASSOCIATION_LEADER","社长")

    ;


    public static MemberType getByType(String type) {
        if (StringUtils.isNotBlank(type)) {
            for (MemberType memberType : values()) {
                if (StringUtils.equals(memberType.getType(), type)) {
                    return memberType;
                }
            }
        }
        return null;
    }

    private String type;

    private String desc;

    MemberType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
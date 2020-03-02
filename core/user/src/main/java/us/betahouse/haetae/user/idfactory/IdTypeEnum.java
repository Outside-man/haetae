/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.idfactory;

/**
 * Id类型枚举
 *
 * @author dango.yxm
 * @version : IdTypeEnum.java 2018/10/06 下午1:25 dango.yxm
 */
public enum IdTypeEnum {

    /**
     * 用户id
     */
    USER_ID("0001", "用户id"),

    /**
     * 角色id
     */
    ROLE_ID("0002", "角色id"),

    /**
     * 用户角色关联id
     */
    USER_ROLE_RELATION_ID("0003", "用户角色关联id"),

    /**
     * 权限id
     */
    PERM_ID("0004", "权限id"),

    /**
     * 角色权限关联id
     */
    ROLE_PERM_RELATION_ID("0005", "角色权限关联id"),

    /**
     * 用户权限关联id
     */
    USER_PERM_RELATION_ID("0006", "用户权限关联id"),

    /**
     * 用户信息id
     */
    USER_INFO_ID("0007", "用户信息id"),

    /**
     * 专业信息id
     */
    MAJOR_ID("0008","专业");

    /**
     * 业务id
     */
    private final String bizNum;

    /**
     * 描述
     */
    private final String desc;

    IdTypeEnum(String bizNum, String desc) {
        this.bizNum = bizNum;
        this.desc = desc;
    }

    public String getBizNum() {
        return bizNum;
    }

    public String getDesc() {
        return desc;
    }
}

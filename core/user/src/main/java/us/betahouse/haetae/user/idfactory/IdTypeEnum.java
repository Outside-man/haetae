/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.idfactory;

/**
 * Id类型枚举
 *
 * @author dango.yxm
 * @version : IdTypeEnum.java 2018/10/06 下午1:25 dango.yxm
 */
public enum IdTypeEnum {

    USER_ID("0001", "用户id"),

    ROLE_ID("0002", "角色id"),

    USER_ROLE_RELATION_ID("0003", "用户角色关联id"),

    PERM_ID("0004", "权限id"),
    ;

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

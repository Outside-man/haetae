/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.user;

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
     * 管理员 拥有所有权限
     */
    MANAGER("MANAGER", "管理员"),

    /**
     * 活动管理者 管理活动
     */
    ACTIVITY_MANAGER("ACTIVITY_MANAGER", "活动管理者"),


    ;
    private String code;

    private String name;

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

package us.betahouse.haetae.serviceimpl.user.routingtable;

import java.util.List;

/**
 * 路由表结构体
 */
public class UserRoutingTable {

    /**
     * 跳转路径
     */
    private String path;

    /**
     * 中文名
     */
    private String authName;

    /**
     * 名字
     */
    private String name;

    /**
     * 是否有路由子表
     */
    private boolean haveChildren;

    /**
     * 路由子表
     */
    private List<UserRoutingTable> children;

    public UserRoutingTable(String path, String authName, String name, boolean haveChildren, List<UserRoutingTable> children) {
        this.path = path;
        this.authName = authName;
        this.name = name;
        this.haveChildren = haveChildren;
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public List<UserRoutingTable> getChildren() {
        return children;
    }

    public void setChildren(List<UserRoutingTable> children) {
        this.children = children;
    }

    public boolean isHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(boolean haveChildren) {
        this.haveChildren = haveChildren;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.user.builder;

import us.betahouse.haetae.user.user.model.basic.perm.PermBO;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限构建者
 *
 * @author dango.yxm
 * @version : PermBOBuilder.java 2018/11/18 上午12:01 dango.yxm
 */
final public class PermBOBuilder {

    /**
     * 权限码
     */
    private String permCode;

    /**
     * 权限类型
     */
    private String permType;

    /**
     * 权限名称
     */
    private String permName;

    /**
     * 权限描述
     */
    private String permDesc;

    /**
     * 拓展信息
     */
    private Map<String, String> extInfo = new HashMap<>();;

    public static PermBOBuilder getInstance(String permCode, String permType, String permName) {
        return new PermBOBuilder(permCode, permType, permName);
    }

    public PermBO build() {
        PermBO permBO = new PermBO();
        permBO.setPermCode(permCode);
        permBO.setPermType(permType);
        permBO.setPermName(permName);
        permBO.setPermDesc(permDesc);
        if (extInfo != null) {
            permBO.setExtInfo(extInfo);
        }
        return permBO;
    }

    private PermBOBuilder(String permCode, String permType, String permName) {
        this.permCode = permCode;
        this.permType = permType;
        this.permName = permName;
    }

    public PermBOBuilder withPermCode(String permCode) {
        this.permCode = permCode;
        return this;
    }

    public PermBOBuilder withPermType(String permType) {
        this.permType = permType;
        return this;
    }

    public PermBOBuilder withPermName(String permName) {
        this.permName = permName;
        return this;
    }

    public PermBOBuilder withPermDesc(String permDesc) {
        this.permDesc = permDesc;
        return this;
    }

    public PermBOBuilder withExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
        return this;
    }
}

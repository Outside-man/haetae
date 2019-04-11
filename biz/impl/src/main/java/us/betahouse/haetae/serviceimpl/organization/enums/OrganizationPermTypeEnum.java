/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.organization.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.serviceimpl.organization.constant.OrganizationPermType;
import us.betahouse.haetae.user.enums.PermType;

/**
 * 权限活动类型
 *
 * @author dango.yxm
 * @version : ActivityPermTypeEnum.java 2018/11/24 7:15 PM dango.yxm
 */
public enum OrganizationPermTypeEnum implements PermType {

    //=================总管理权限 start=================

    /**
     * 所有组织管理权限 创建&删除
     */
    ALL_ORG_MANAGE(OrganizationPermType.ALL_ORG_MANAGE, "全部组织管理", true),

    /**
     * 所有组织关系管理
     */
    ALL_ORG_RELATION_MANAGE(OrganizationPermType.ALL_ORG_RELATION_MANAGE, "全部组织关系管理", true),

    /**
     * 所有组织成员管理
     */
    ALL_ORG_MEMBER_MANAGE(OrganizationPermType.ALL_ORG_MEMBER_MANAGE, "全部成员管理", true),


    //=================总管理权限 end=================

    /**
     * 组织成员管理 成员 增加&删除
     */
    ORG_MEMBER_MANAGE(OrganizationPermType.ORG_MEMBER_MANAGE, "成员管理", false),


    /**
     * 组织成员身份管理 分配主管 管理员
     */
    ORG_MEMBER_TYPE_MANAGE(OrganizationPermType.ORG_MEMBER_TYPE_MANAGE, "成员身份管理", false);

    private String code;

    private String desc;

    private boolean init;

    public static OrganizationPermTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (OrganizationPermTypeEnum permType : values()) {
            if (StringUtils.equals(permType.getCode(), code)) {
                return permType;
            }
        }
        return null;
    }

    OrganizationPermTypeEnum(String code, String desc, boolean init) {
        this.code = code;
        this.desc = desc;
        this.init = init;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public boolean isInit() {
        return init;
    }
}

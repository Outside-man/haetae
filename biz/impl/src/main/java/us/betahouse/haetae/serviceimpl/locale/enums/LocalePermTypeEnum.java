/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.serviceimpl.locale.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.serviceimpl.locale.constant.LocalePermType;
import us.betahouse.haetae.user.enums.PermType;

/**
 * @author NathanDai
 * @version :  2019-07-09 21:26 NathanDai
 */
public enum LocalePermTypeEnum implements PermType {

    //场地权限相关
    LOCALE_APPLY(LocalePermType.LOCALE_APPLY, "场地申请", true),

    APPLY_FIRST_CHECK(LocalePermType.APPLY_FIRST_CHECK, "场地申请初步审核", true),

    APPLY_CHECK(LocalePermType.APPLY_CHECK, "场地申请审核通过", true);

    private String code;

    private String desc;

    private boolean init;

    public static LocalePermTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (LocalePermTypeEnum permType : values()) {
            if (StringUtils.equals(permType.getCode(), code)) {
                return permType;
            }
        }
        return null;
    }

    LocalePermTypeEnum(String code, String desc, boolean init) {
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

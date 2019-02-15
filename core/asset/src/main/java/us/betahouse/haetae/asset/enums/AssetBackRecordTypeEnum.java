/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author yiyuk.hxy
 * @version : AssetBackRecordTypeEnum.java 2019/02/15 14:46 yiyuk.hxy
 */
public enum AssetBackRecordTypeEnum {
    BACK("back", "归还"),
    DISTORY("distory", "全部损坏或消耗");

    private String code;
    private String desc;

    AssetBackRecordTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AssetBackRecordTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (AssetBackRecordTypeEnum assetBackRecordTypeEnum : values()) {
            if (StringUtils.equals(assetBackRecordTypeEnum.getCode(), code)) {
                return assetBackRecordTypeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

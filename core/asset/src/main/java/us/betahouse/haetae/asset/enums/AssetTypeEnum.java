/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 物资性质
 *
 * @author guofan.cp
 * @version : AssetTypeEnum.java 2019/01/24 8:56 guofan.cp
 */
public enum AssetTypeEnum {
    ASSET_CONSUME("Consumable", "消耗品"),

    ASSET_DURABLE("Durable", "耐用品");

    private String code;

    private String desc;

    AssetTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AssetTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (AssetTypeEnum assetTypeEnum : values()) {
            if (StringUtils.equals(assetTypeEnum.getCode(), code)) {
                return assetTypeEnum;
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

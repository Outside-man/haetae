/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.enums;

import org.apache.commons.lang.StringUtils;

/**
 * @author yiyuk.hxy
 * @version : AssetLoanRecordStatusEnum.java 2019/02/03 14:26 yiyuk.hxy
 */
public enum AssetLoanRecordStatusEnum {
    ASSET_LOAN_RECORD_LOAN("Loading", "借出状态"),
    ASSET_LOAN_RECORD_NOTLOAN("Loaded", "全部归还"),
    ASSET_LOAN_RECORD_DESTORY("Destroyed", "全部归还但有损坏");

    private String code;
    private String desc;

    AssetLoanRecordStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AssetLoanRecordStatusEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (AssetLoanRecordStatusEnum assetLoanRecordStatusEnum : values()) {
            if (StringUtils.equals(assetLoanRecordStatusEnum.getCode(), code)) {
                return assetLoanRecordStatusEnum;
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

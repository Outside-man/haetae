/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.model.basic;

import us.betahouse.haetae.locale.enums.LocaleStatusEnum;
import us.betahouse.util.common.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public class LocaleBO extends ToString {

    private static final long serialVersionUID = 8875918091689078054L;
    /**
     * 场地ID
     */
    private String localeId;

    /**
     * 场地名称 例如：笃行楼501
     */
    private String localeName;

    /**
     * 场地代号 例如：DUXING501
     */
    private String localeCode;

    /**
     * 场地状态 两种：可借 USABLE 不可借 DISABLE
     *
     * @see LocaleStatusEnum
     */
    private String status;

    /**
     * 场地额外信息
     */
    private Map<String, String> extInfo = new HashMap<>();

    public String fetchExtInfo(String key) {
        if (extInfo == null) {
            return null;
        }
        return extInfo.get(key);
    }

    public void putExtInfo(String key, String value) {
        if (extInfo == null) {
            extInfo = new HashMap<>();
        }
        extInfo.put(key, value);
    }

    public String getLocaleId() {
        return localeId;
    }

    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }
}

package us.betahouse.haetae.locale.model.basic;

import us.betahouse.util.common.ToString;

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
     * 是否可借用 两种：可借 USABLE 不可借 DISABLE
     */
    private String status;


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
}

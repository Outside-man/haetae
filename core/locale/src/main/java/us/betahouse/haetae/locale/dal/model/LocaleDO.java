package us.betahouse.haetae.locale.dal.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "locale",
        indexes = {
                @Index(name = "uk_locale_id", columnList = "locale_id", unique = true)
        })
public class LocaleDO extends BaseDO {

    private static final long serialVersionUID = -107533285115723579L;
    /**
     * 场地ID
     */
    @Column(name = "locale_id", length = 32, updatable = false)
    private String localeId;
    /**
     * 场地名称 例如：笃行楼501
     */
    @Column(name = "locale_name", length = 32)
    private String localeName;
    /**
     * 场地代号 例如：DUXING501
     */
    @Column(name = "locale_code", length = 32)
    private String localeCode;
    /**
     * 是否可借用 两种：可借 USABLE 不可借 DISABLE
     */
    @Column(name = "status", length = 32)
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
}

package us.betahouse.haetae.locale.dal.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "locale_area",
        indexes = {
                @Index(name = "uk_locale_area_id", columnList = "locale_area_id", unique = true)
        })
public class LocaleAreaDO extends BaseDO {

    private static final long serialVersionUID = -1623506878883657729L;
    /**
     * 占用ID
     */
    @Column(name = "locale_area_id", length = 32, updatable = false)
    private String localeAreaId;
    /**
     * 场地ID
     */
    @Column(name = "locale_id", length = 32, updatable = false)
    private String localeId;
    /**
     * 场地借用者
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 场地使用日期
     */
    @Column(name = "time_date")
    private String timeDate;
    /**
     * 场地使用时间段
     */
    @Column(name = "time_bucket")
    private String timeBucket;
    /**
     * 是否状况 三种：提交申请 申请中 取消
     */
    @Column(name = "status", length = 32)
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLocaleAreaId() {
        return localeAreaId;
    }

    public void setLocaleAreaId(String localeAreaId) {
        this.localeAreaId = localeAreaId;
    }

    public String getLocaleId() {
        return localeId;
    }

    public void setLocaleId(String localeId) {
        this.localeId = localeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public String getTimeBucket() {
        return timeBucket;
    }

    public void setTimeBucket(String timeBucket) {
        this.timeBucket = timeBucket;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

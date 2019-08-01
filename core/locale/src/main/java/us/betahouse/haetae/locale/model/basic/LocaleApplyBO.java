/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.locale.model.basic;

import us.betahouse.util.common.ToString;

import java.util.*;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

public class LocaleApplyBO extends ToString {

    private static final long serialVersionUID = 1305031373217182020L;
    /**
     * 申请创建的时间
     */
    private Date create;
    /**
     * 申请者的联系方式
     */
    private String tel;

    /**
     * 申请的用途
     */
    private String usages;

    /**
     * 申请的备注
     */
    private String remark;

    /**
     * 申请的附件
     */
    private String document;

    /**
     * 是否状况 三种：提交申请 申请中 取消
     */
    private String status;

    /**
     * 场地使用日期
     */
    private String timeDate;

    /**
     * 场地使用时间段
     */
    private String timeBucket;

    /**
     * 场地代号
     */
    private String localeCode;


    /**
     * 场地名称 例如：笃行楼501
     */
    private String localeName;

    /**
     * 占用ID
     */
    private String localeAreaId;

    /**
     * 场地ID
     */
    private String localeId;

    /**
     * 场地借用者ID
     */
    private String userId;

    /**
     * 申请ID
     */
    private String localeApplyId;


    /**
     * 场地申请额外信息
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

    /**
     * 判断是否可以结束
     *
     * @return
     */
    public boolean canFinish() {
        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(calendar.DATE, -2);
        now = calendar.getTime();
        return create.before(now);
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUsages() {
        return usages;
    }

    public void setUsages(String usages) {
        this.usages = usages;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
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

    public String getLocaleApplyId() {
        return localeApplyId;
    }

    public void setLocaleApplyId(String localeApplyId) {
        this.localeApplyId = localeApplyId;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }
}

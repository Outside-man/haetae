/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.enums;

/**
 * @author MessiahJK
 * @version : ActivityStampImportTemplateEnum.java 2018/12/08 22:03 MessiahJK
 */
public enum ActivityStampImportTemplateEnum {
    /**
     * 姓名
     */
    NAME("姓名"),
    /**
     * 学号
     */
    STU_ID("学号"),
    /**
     * 扫码员学号
     */
    SCANNER("扫码员学号"),
    /**
     * 活动名称
     */
    ACTIVITY_NAME("活动名称");

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    String desc;

    ActivityStampImportTemplateEnum(String desc) {
        this.desc = desc;
    }

}

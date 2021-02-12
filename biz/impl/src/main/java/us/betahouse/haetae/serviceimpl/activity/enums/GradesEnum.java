/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.serviceimpl.activity.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.serviceimpl.activity.constant.GradesConstant;

/**
 * 等第
 *
 * @author dango.yxm
 * @version : GradesEnum.java 2018/11/27 10:29 AM dango.yxm
 */
public enum GradesEnum {

    EXCELLENT(GradesConstant.EXCELLENT, "优秀"),

    PASS(GradesConstant.PASS, "合格"),

    FAIL(GradesConstant.FAIL, "不合格");


    private String code;

    private String desc;

    public static GradesEnum getByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (GradesEnum gradesEnum : values()) {
                if (StringUtils.equals(gradesEnum.getCode(), code)) {
                    return gradesEnum;
                }
            }
        }
        return null;
    }

    GradesEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

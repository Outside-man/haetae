/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.user.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 登录类型
 *
 * @author MessiahJK
 * @version : TripartiteType.java 2019/06/30 20:45 MessiahJK
 */
public enum TripartiteType {

    /**
     * 三方登陆类型
     */
    WeChat_LOGIN("WeChat","微信"),

    YiBanLogin("YiBan","易班"),

    WebLogin("Web","浏览器"),

    AlipayLogin("Alipay","支付宝"),

    QQLogin("QQ","QQ"),

    GitHubLogin("Github","Github")
    ;

    private String code;

    private String message;

    TripartiteType(String code, String message){
        this.code=code;
        this.message=message;
    }

    public static TripartiteType getTripartiteTypeByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }
        for (TripartiteType loginType : values()) {
            if(StringUtils.equals(code, loginType.code)){
                return loginType;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

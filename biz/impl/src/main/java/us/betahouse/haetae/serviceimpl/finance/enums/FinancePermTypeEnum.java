/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.finance.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.user.enums.PermType;

/**
 * @author MessiahJK
 * @version : FinancePermTypeEnum.java 2019/03/02 21:08 MessiahJK
 */
public enum FinancePermTypeEnum  implements PermType {
    /**
     * 财务管理
     */
    FINANCE_MANAGE("FINANCE_MANAGER","财务管理",true);

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 是否初始化
     */
    private boolean init;

    FinancePermTypeEnum(String code, String desc, boolean init ){
        this.code=code;
        this.desc=desc;
        this.init=init;
    }
    public static FinancePermTypeEnum getByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }
        for(FinancePermTypeEnum permType:values()){
            if(StringUtils.equals(permType.getCode(),code)){
                return permType;
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public boolean isInit() {
        return init;
    }
}

package us.betahouse.haetae.serviceimpl.user.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.serviceimpl.user.constant.UserPermType;
import us.betahouse.haetae.user.enums.PermType;

/**
  * @author kana-cr
  * @version  2020/11/13 12:00
  */
public enum UserManagerPermTypeEnum implements PermType {

    /**
     * 用户密码重置
     */
    USER_PASSWORD_RESET(UserPermType.USER_PASSWORD_RESET,"重置用户密码",true),
    ;

    private String code;

    private String desc;

    private boolean init;

    public static UserManagerPermTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (UserManagerPermTypeEnum permType : values()) {
            if (StringUtils.equals(permType.getCode(), code)) {
                return permType;
            }
        }
        return null;
    }

    UserManagerPermTypeEnum(String code, String desc, boolean init) {
        this.code = code;
        this.desc = desc;
        this.init = init;
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

/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.enums;

import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.serviceimpl.asset.constant.AssetPermType;
import us.betahouse.haetae.user.enums.PermType;

/**
 * @author guofan.cp
 * @version : AssetPermTypeEnum.java 2019/02/19 10:27 guofan.cp
 */
public enum AssetPermTypeEnum implements PermType {

    //物资权限相关
    ASSET_CREATE(AssetPermType.ASSET_CREATE,"物资创建",true),
    ASSET_DELETE(AssetPermType.ASSET_DELETE,"物资删除",true),
    ASSET_UPDATE(AssetPermType.ASSET_UPDATE,"物资更新",true);

    private String code;

    private String desc;

    private boolean init;

    public static AssetPermTypeEnum getByCode(String code){
        if(StringUtils.isBlank(code)){
            return null;
        }
        for(AssetPermTypeEnum permType:values()){
            if(StringUtils.equals(permType.getCode(),code)){
                return permType;
            }
        }
        return null;
    }

    AssetPermTypeEnum(String code, String desc, boolean init ){
        this.code=code;
        this.desc=desc;
        this.init=init;
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

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.convert;

import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.model.basic.AssetBO;

/**
 * @author guofan.cp
 * @version : EntityConverter.java 2019/01/21 23:47 guofan.cp
 * 实体转换器 jk那边跟yxm这边封装不一样 感觉还是抽出来更方便一些
 */
public class EntityConverter {
    /**
    * @Description:  物资DO2BO
    * @Param: [assetDO]
    */
    public static AssetBO convert(AssetDO assetDO){
        if(assetDO==null){
            return null;
        }
        AssetBO assetBO=new AssetBO();
        return assetBO;
    }
    /**
    * @Description: 物资BO2DO
    * @Param: [assetBO]
    */
    public static  AssetDO convert(AssetBO assetBO){
        if (assetBO == null) {
            return null;
        }
        AssetDO assetDO=new AssetDO();
        return assetDO;
    }
}

/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.convert;

import com.alibaba.fastjson.JSON;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.model.basic.AssetBO;

import java.util.Map;

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
        assetBO.setAssetId(assetDO.getAssetId());
        assetBO.setAssetMount(assetDO.getAmount());
        assetBO.setAssetRemain(assetDO.getRemain());
        assetBO.setAssetName(assetDO.getAssetName());
        assetBO.setAssetOrginnazation(assetDO.getOrginazationId());
        assetBO.setAssetStatus(assetDO.getStatus());
        assetBO.setAssetType(assetDO.getType());
        assetBO.setCreate(assetDO.getGmtCreate());
        assetBO.setModified(assetDO.getGmtModified());
        assetBO.setExtInfo(JSON.parseObject(assetDO.getExtInfo(), Map.class));
        //JSON.parseObject jsonn转对象 ，前一个参数为json串，后一个参数为转换成的类
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
        assetDO.setAmount(assetBO.getAssetMount());
        assetDO.setAssetId(assetBO.getAssetId());
        assetDO.setAssetName(assetBO.getAssetName());
        assetDO.setOrginazationId(assetBO.getAssetOrginnazation());
        assetDO.setRemain(assetBO.getAssetRemain());
        assetDO.setStatus(assetBO.getAssetStatus());
        assetDO.setType(assetBO.getAssetType());
        assetDO.setGmtCreate(assetBO.getCreate());
        assetDO.setGmtModified(assetBO.getModified());
        assetDO.setExtInfo(JSON.toJSONString(assetBO.getExtInfo()));
        //把对象o转换为json格式文本
        return assetDO;
    }
}

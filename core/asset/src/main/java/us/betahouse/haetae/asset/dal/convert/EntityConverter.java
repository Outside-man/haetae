/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.dal.convert;

import com.alibaba.fastjson.JSON;
import us.betahouse.haetae.asset.dal.model.AssetDO;
import us.betahouse.haetae.asset.model.basic.AssetBO;

/**
 * @author guofan.cp
 * @version : EntityConverter.java 2019/01/21 23:47 guofan.cp
 * 实体转换器 jk那边跟yxm这边封装不一样 感觉还是抽出来更方便一些
 */
final public class EntityConverter {
    /**
     * 物资DO2BO
     *
     * @param assetDO
     * @return
     */
    public static AssetBO convert(AssetDO assetDO) {
        if (assetDO == null) {
            return null;
        }
        AssetBO assetBO = new AssetBO();
        assetBO.setAssetId(assetDO.getAssetId());
        assetBO.setAssetAmount(assetDO.getAmount());
        assetBO.setAssetRemain(assetDO.getRemain());
        assetBO.setAssetDestory(assetDO.getDestory());
        assetBO.setAssetName(assetDO.getAssetName());
        assetBO.setAssetOrganizationId(assetDO.getOrginazationId());
        assetBO.setAssetStatus(assetDO.getStatus());
        assetBO.setAssetType(assetDO.getType());
        assetBO.setCreate(assetDO.getGmtCreate());
        assetBO.setModified(assetDO.getGmtModified());
        //assetBO.setExtInfo(JSON.parseObject(assetDO.getExtInfo(), Map.class));
        // TODO 暂时把上一句注释掉，会报JSON错误
        //JSON.parseObject jsonn转对象 ，前一个参数为json串，后一个参数为转换成的类
        return assetBO;
    }

    /**
     * 物资BO2DO
     *
     * @param assetBO
     */
    public static AssetDO convert(AssetBO assetBO) {
        if (assetBO == null) {
            return null;
        }
        AssetDO assetDO = new AssetDO();
        assetDO.setAmount(assetBO.getAssetAmount());
        assetDO.setAssetId(assetBO.getAssetId());
        assetDO.setAssetName(assetBO.getAssetName());
        assetDO.setOrginazationId(assetBO.getAssetOrganizationId());
        assetDO.setRemain(assetBO.getAssetRemain());
        assetDO.setDestory(assetBO.getAssetDestory());
        assetDO.setStatus(assetBO.getAssetStatus());
        assetDO.setType(assetBO.getAssetType());
        assetDO.setGmtCreate(assetBO.getCreate());
        assetDO.setGmtModified(assetBO.getModified());
        assetDO.setExtInfo(JSON.toJSONString(assetBO.getExtInfo()));
        //把对象o转换为json格式文本
        return assetDO;
    }
}

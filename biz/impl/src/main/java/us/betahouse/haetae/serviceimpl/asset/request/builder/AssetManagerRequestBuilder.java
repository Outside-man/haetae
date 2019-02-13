/*
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.request.builder;

import us.betahouse.haetae.serviceimpl.asset.request.AssetManagerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 *请求构造器
 * @author guofan.cp
 * @version : AssetManagerRequestBuilder.java 2019/01/23 23:48 guofan.cp
 */
public class AssetManagerRequestBuilder {
    /**
     *额外拓展信息
     */
    private Map<String , String> extInfo=new HashMap<>();
    /**
     * 物资数量
     */
    private int assetAmount;
    /**
     * 物资id
     */
    private String assetId;
    /**
     * 物资名字
     */
    private String assetName;
    /**
     * 物资归属组织id
     */
    private String assetOrginazationId;
    /**
     * 物资归属组织名字
     */
    private String assetOrganizationName;
    /**
     * 物资剩余
     */
    private int assetReamain;
    /**
     * 物资状态 /可借/不可借
     */
    private  String assetStatus;
    /**
     * 物资类别 消耗品/不消耗
     */
    private String assetType;

    public AssetManagerRequestBuilder() {
    }

    public static AssetManagerRequestBuilder getInstance(){
        return new AssetManagerRequestBuilder();
    }

    public AssetManagerRequestBuilder withAssetId(String assetId){
        this.assetId=assetId;
        return this;
    }

    public AssetManagerRequestBuilder withAmount(int amount){
        this.assetAmount=amount;
        return this;
    }

    public AssetManagerRequestBuilder withAssetName(String assetName){
        this.assetName=assetName;
        return this;
    }

    public AssetManagerRequestBuilder withExtInfo(Map<String , String > extInfo){
        this.extInfo=extInfo;
        return this;
    }

    public AssetManagerRequestBuilder withOrginazationId(String assetOrginazationId){
        this.assetOrginazationId=assetOrginazationId;
        return this;
    }

    public AssetManagerRequestBuilder withReamain(int assetReamain){
        this.assetReamain=assetReamain;
        return this;
    }

    public AssetManagerRequestBuilder withStatus(String assetStatus){
        this.assetStatus=assetStatus;
        return this;
    }

    public AssetManagerRequestBuilder withType(String assetType){
        this.assetType=assetType;
        return this;
    }

    public AssetManagerRequestBuilder withAssetOrganizationName(String assetOrganizationName){
        this.assetOrganizationName=assetOrganizationName;
        return this;
    }

    public AssetManagerRequest builder(){
        AssetManagerRequest assetManagerRequest=new AssetManagerRequest();
        assetManagerRequest.setAssetId(assetId);
        assetManagerRequest.setAssetAmount(assetAmount);
        assetManagerRequest.setExtInfo(extInfo);
        assetManagerRequest.setAssetName(assetName);
        assetManagerRequest.setAssetRemain(assetReamain);
        assetManagerRequest.setAssetType(assetType);
        assetManagerRequest.setAssetStatus(assetStatus);
        assetManagerRequest.setAssetOrganizationId(assetOrginazationId);
        assetManagerRequest.setAssetOrganizationName(assetOrganizationName);
        return assetManagerRequest;
    }
}

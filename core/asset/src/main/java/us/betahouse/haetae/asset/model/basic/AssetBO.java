/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.model.basic;

import us.betahouse.util.common.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 物资领域对象
 * 暂时还不知道乍用
 * @author guofan.cp
 * @version : AssetBO.java 2019/01/21 22:53 guofan.cp
 */
public class AssetBO extends ToString{
    private static final long serialVersionUID = -3850765153043424655L;
    /**
     * 物资id
     */
    private String assetId;
    /**
     * 物资名字
     */
    private String assetName;
    /**
     * 物资创建时间
     */
    private Date create;
    /**
     *物资修改时间
     */
    private Date modified;
    /**
     *物资数量
     */
    private int assetMount;
    /**
     * 物资归属组织id
     */
    private String assetOrginnazationId;
    /**
     * 物资归属组织名字
     */
    private String getAssetOrginnazationName;
    /**
     * 物资剩余
     */
    private int assetRemain;
    /**
     * 物资状态
     */
    private String assetStatus;
    /**
     * 物资类型
     */
    private String assetType;
    /**
     * 物资额外信息
     */
    private Map<String , String> extInfo=new HashMap<>();
    public  String fecthExtInfo(String key){
        if(extInfo==null) {
            return  null;
        }
        return extInfo.get(key);
    }
    public void putExtInfo(String key,String value){
        if(extInfo==null){
            extInfo=new HashMap<>();
        }
        extInfo.put(key,value);
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified (Date modified) {
        this.modified = modified;
    }

    public String getAssetOrginnazationId() {
        return assetOrginnazationId;
    }

    public void setAssetOrginnazationId(String assetOrginnazationId) {
        this.assetOrginnazationId = assetOrginnazationId;
    }

    public String getGetAssetOrginnazationName() {
        return getAssetOrginnazationName;
    }

    public void setGetAssetOrginnazationName(String getAssetOrginnazationName) {
        this.getAssetOrginnazationName = getAssetOrginnazationName;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Map<String, String> getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(Map<String, String> extInfo) {
        this.extInfo = extInfo;
    }

    public int getAssetMount() {
        return assetMount;
    }

    public void setAssetMount(int assetMount) {
        this.assetMount = assetMount;
    }

    public int getAssetRemain() {
        return assetRemain;
    }

    public void setAssetRemain(int assetRemain) {
        this.assetRemain = assetRemain;
    }
}

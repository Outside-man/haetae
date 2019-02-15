/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.asset.request;

/**
 * 物质请求管理
 *
 * @author guofan.cp
 * @version : AssetRequest.java 2019/01/20 23:21 guofan.cp
 */
public class AssetRequest extends BaseRequest {
    private static final long serialVersionUID = 7597108455802717599L;
    /**
     * 物资id
     */
    private String assetId;
    /**
     * 物资名字
     */
    private String assetName;
    /**
     * 物资备注信息
     */
    private String assetExtInfo;
    /**
     * 物资总数量
     */
    private int assetAmount;
    /**
     * 物资所属组织id
     */
    private String assetOrganizationId;
    /**
     * 物资所属组织名字
     */
    private String assetOrganizationName;
    /**
     * 物资剩余数量
     */
    private int assetRemain;
    /**
     * 物资损坏个数
     */
    private int assetDestory;
    /**
     * 物资类别
     */
    private String assetType;

    /**
     * 物资状态
     */
    private String assetStatus;

    /**
     * 物资创建者
     */
    private String userId;

    /**
     * 物资状态码
     */
    private String assetStatusCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getAssetRemain() {
        return assetRemain;
    }

    public void setAssetRemain(int assetRemain) {
        this.assetRemain = assetRemain;
    }

    public int getAssetDestory() {
        return assetDestory;
    }

    public void setAssetDestory(int assetDestory) {
        this.assetDestory = assetDestory;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAssetOrganizationId() {
        return assetOrganizationId;
    }

    public void setAssetOrganizationId(String assetOrganizationId) {
        this.assetOrganizationId = assetOrganizationId;
    }

    public String getAssetOrganizationName() {
        return assetOrganizationName;
    }

    public void setAssetOrganizationName(String assetOrganizationName) {
        this.assetOrganizationName = assetOrganizationName;
    }

    public int getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(int assetAmount) {
        this.assetAmount = assetAmount;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(String assetStatus) {
        this.assetStatus = assetStatus;
    }

    public String getAssetStatusCode() {
        return assetStatusCode;
    }

    public void setAssetStatusCode(String assetStatusCode) {
        this.assetStatusCode = assetStatusCode;
    }
}

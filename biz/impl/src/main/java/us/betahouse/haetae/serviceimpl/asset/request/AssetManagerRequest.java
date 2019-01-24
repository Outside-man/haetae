/**
 * betahouse.us
 * CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.serviceimpl.asset.request;

import us.betahouse.haetae.asset.request.AssetRequest;
import us.betahouse.haetae.serviceimpl.common.verify.VerifyRequest;

import java.io.Serializable;

/**
 * @author guofan.cp
 * @version : AssetManagerRequest.java 2019/01/21 9:13 guofan.cp
 */
public class AssetManagerRequest extends AssetRequest implements VerifyRequest {

    private static final long serialVersionUID = 3245100919662928018L;

    private String assetOrganizationName;

    /**
     * 获取组织的名字
     */
    public String getAssetOrganizationName() {
        return assetOrganizationName;
    }

    /**
     * 设置组织名字(基本没用)
     */
    public void setAssetOrganizationName(String assetOrganizationName) {
        this.assetOrganizationName = assetOrganizationName;
    }

    @Override
    public String getVerifyUserId() {
        return getUserId();
    }
//    暂时不知道有没有用先保留一下
//    /**
//     *存入物资创建者学号
//     */
//    public void putStamperStuId(String stuId) {
//        putExtInfo(ASSET_CREATE_ID, stuId);
//    }
//    /**
//     * 取出物资创建者学号
//     */
//    public static String getAssetCreateId() {
//        return ASSET_CREATE_ID;
//    }

}

package us.betahouse.haetae.asset.idfactory;
/** 
* @Description:  业务id生成工厂仿写
* @Param:  cp
*/

public interface BizIdFactory {
    /**
    * @Description: 生成物资id
    * @Param:
    */
    String getAssetId();
    /**
    * @Description: 生成物资借用id
    * @Param:
    */
    String getAssetLoadId();
    /**
    * @Description:生成物资归还id
    * @Param:
    */
    String getAssetBackId();

}

package us.betahouse.haetae.asset.idfactory;
/**
* @Description: id类型枚举类
* @Param:  cp
*/
public enum idTypeEnum {
    ASSET_ID("2001","物资id"),
    ASSET_LOAD_ID("2002","物资借用id"),
    ASSET_BACK_ID("2003","物资归还id");

    /**
    * @Description: 业务id
    * @Param: [bizNum, desc]
    */
    private final String desc;
    /**
    * @Description: 描述
    * @Param:
    */
    private final  String bizNum;
    idTypeEnum(String bizNum, String desc) {
        this.desc = desc;
        this.bizNum=bizNum;
    }

    public String getDesc() {
        return desc;
    }

    public String getBizNum() {
        return bizNum;
    }
}

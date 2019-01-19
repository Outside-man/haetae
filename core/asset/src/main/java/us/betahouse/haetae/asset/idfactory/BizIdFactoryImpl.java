package us.betahouse.haetae.asset.idfactory;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import us.betahouse.util.utils.DateUtil;

import java.util.Date;
import java.util.Random;

/**
* @Description: id工厂实现 
* @Param:  
*/
@Service("assetBizIdFactory")
//这边注解主要作用是起一个别名方便别处导入
public class BizIdFactoryImpl implements BizIdFactory {
    /**
    * @Description: 随机数范围1
    * @Param:
    */
    private  static  int RANDOM_RANGE=100000000;
    /**
    * @Description: 随机器
    * @Param:
    */
    private final  static Random random=new Random();

    @Override
    public String getAssetId() {
        StringBuilder builder= new StringBuilder(32);
        Date now= new Date();
        //1-16位系统时间
        builder.append(DateUtil.getShortDatesStr(now));
        //16-22位 6位随机数
        builder.append(getRandNum(6));
        //22-26 4位
        builder.append(idTypeEnum.ASSET_ID.getBizNum());
        //26-30业务自定义码
        builder.append(DateUtil.getYear(now));
        //2 随机数
        builder.append(getRandNum(2));
        return builder.toString();
    }

    @Override
    public String getAssetLoadId() {
        StringBuilder builder= new StringBuilder(32);
        Date now= new Date();
        //1-16位系统时间
        builder.append(DateUtil.getShortDatesStr(now));
        //16-22位 6位随机数
        builder.append(getRandNum(6));
        //22-26 4位
        builder.append(idTypeEnum.ASSET_LOAD_ID.getBizNum());
        //26-30业务自定义码
        builder.append(DateUtil.getMonthDay(now));
        //2 随机数
        builder.append(getRandNum(2));
        return builder.toString();
    }

    @Override
    public String getAssetBackId() {
        StringBuilder builder= new StringBuilder(32);
        Date now= new Date();
        //1-16位系统时间
        builder.append(DateUtil.getShortDatesStr(now));
        //16-22位 6位随机数
        builder.append(getRandNum(6));
        //22-26 4位
        builder.append(idTypeEnum.ASSET_BACK_ID.getBizNum());
        //26-30业务自定义码
        builder.append(DateUtil.getDay(now));
        //2 随机数
        builder.append(getRandNum(2));
        return builder.toString();
    }
    /** 
    * @Description: 获取指定长度的随机数
    * @Param: [length] 
    */ 
    private  String getRandNum(int length)
    {
        if(length<0) {
            throw  new IllegalArgumentException("截取非法长度");
        }
        String numStr=String.valueOf(random.nextInt(RANDOM_RANGE));
        return getLngthString(numStr,length);
    }
    /** 
    * @Description: 获取长度字符床不足向左补零 
    * @Param: [str, length] 
    */ 
    private  String getLngthString(String str,int length){
        String lengthString = StringUtils.right(str,length);
        if(StringUtils.isBlank(str)){
            return getZeroString(length);
        }
        if(length>str.length()){
            return getZeroString(length-str.length())+str;
        }
        return lengthString;
    }
    /** 
    * @Description: 获取长度为length的0字符串 
    * @Param:  
    */
    private  String getZeroString(int length){
        StringBuilder SB=new StringBuilder();
        for(int i=0;i<length;i++) {
            SB.append("0");
        }
        return SB.toString();
    }
}

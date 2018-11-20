/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package utils.wechat;

import com.alibaba.fastjson.JSONObject;
import utils.HttpUtils;

/**
 * @author MessiahJK
 * @version : WeChatLoginUtil.java 2018/11/19 21:41 MessiahJK
 */
public class WeChatLoginUtil {
    /**
     * 获取openId
     * 获取失败将返回errcode
     * @return
     * @throws Exception
     */
    public String getOpenId(String code,String appId,String secret){
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        String  oppId = null;
        try {
            oppId = new HttpUtils().doGet(requestUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(oppId);
        JSONObject jsonObject=JSONObject.parseObject(oppId);
        if(jsonObject.getString("openid")!=null){
            return jsonObject.getString("openid");
        }else{
            return jsonObject.getString("errcode");
        }
    }
}

package us.betahouse.util.wechat;

import com.alibaba.fastjson.JSONObject;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.HttpUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
  * @Author kana-cr
  * @Date  2020/8/28 12:06
  **/
public class WeChatPublishUtil {


    /**
     * 获取accessToken的url模板
     */
    private final static String ACCESS_TOKEN_URL_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    /**
     * 返回体的access_token
     */
    private final static String ACCESS_TOKEN="access_token";


    /**
     * 返回体的错误码
     */
    private final static String ERROR_CODE = "errcode";

    /**
     * 返回体的openId
     */
    private final static String OPEN_ID = "openid";

    /**
     * 获取accessToken
     * @return
     */
    public static String GetAccessToken(String appId,String secret){
        Map<String,Object> parameterMap=new HashMap<>(2);
        parameterMap.put("appid",appId);
        parameterMap.put("secret",secret);
        String result;
        try {
            result = HttpUtils.doPost(ACCESS_TOKEN_URL_TEMPLATE,parameterMap);
        } catch (Exception e) {
            throw new BetahouseException(e, CommonResultCode.SYSTEM_ERROR.getCode(), "微信服务器请求失败, 网络异常");
        }
        JSONObject jsonObject = JSONObject.parseObject(result);

        String accessToken = jsonObject.getString(ACCESS_TOKEN);
        AssertUtil.assertStringNotBlank(accessToken, CommonResultCode.SYSTEM_ERROR.getCode(), MessageFormat.format("微信服务器请求失败:{0}", jsonObject.getString(ERROR_CODE)));
        //gc
        parameterMap=null;
        return accessToken;
    }


}

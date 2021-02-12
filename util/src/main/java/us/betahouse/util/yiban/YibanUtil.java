/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.util.yiban;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.HttpUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MessiahJK
 * @version : YibanUtil.java 2019/07/03 9:27 MessiahJK
 */
@Component
public class YibanUtil {
    /**
     *  获取已授权用户的access_token
     */
    private final String YIBAN_ACCESS_TOKEN_URL ="https://openapi.yiban.cn/oauth/access_token";

    private final String YIBAN_USER_OTHER="https://openapi.yiban.cn/user/other?access_token={0}&yb_userid={1}";

    private final String YIBAN_VERIFY_ME="https://openapi.yiban.cn/user/verify_me?access_token={0}";


    @Value("${Yiban.appId}")
    private String appId;

    @Value("${Yiban.appSecret}")
    private String appSecret;

    @Value("${Yiban.redirectUri}")
    private String redirectUri;

    public YiModel getAccessToken(String code){
        String result;
        try {
            Map<String,String> map=new HashMap<String,String>(4);
            map.put("client_id", appId );
            map.put("client_secret", appSecret);
            map.put("code", code);
            map.put("redirect_uri",redirectUri);
            result=HttpUtils.doPost(YIBAN_ACCESS_TOKEN_URL,map );
        }catch (Exception e){
            throw new BetahouseException(e, CommonResultCode.SYSTEM_ERROR.getCode(), "易班服务器请求失败, 网络异常");
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);
        YiModel yiModel=new YiModel();
        yiModel.setUserId(jsonObject.getString("userid"));
        yiModel.setAccessToken(jsonObject.getString("access_token"));
        return yiModel;
    }

    public String getStuId(YiModel yiModel){
        String url=MessageFormat.format(YIBAN_VERIFY_ME, yiModel.getAccessToken());
        String result;
        try {
            result = HttpUtils.doGet(url);
        } catch (Exception e) {
            throw new BetahouseException(e, CommonResultCode.SYSTEM_ERROR.getCode(), "易班服务器请求失败, 网络异常");
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        System.out.println(jsonObject);
        return jsonObject.getJSONObject("info").getString("yb_studentid");
    }

}

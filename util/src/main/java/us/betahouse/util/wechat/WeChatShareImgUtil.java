package us.betahouse.util.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.HttpUtils;

import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WeChatShareImgUtil {
    
    /**
     * 请求路径模板
     */
    private static final String SHARE_IMG_URL_TEMPLATE = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";
    
    /**
     * 获取小程序二维码
     **/
    public static String getWXCode(String accessToken, Map<String, Object> param) {
        String code = "data:image/jpg;base64,";
        try {
            String url = SHARE_IMG_URL_TEMPLATE + accessToken;
            String json = JSON.toJSONString(param);
            ByteArrayInputStream inputStream = HttpUtils.sendPost(url, json);
    
            if (inputStream.available() >= 200){
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int i;
                byte[] buffer = new byte[200];
                while ((i = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer,0,i);
                }
                code += Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
                byteArrayOutputStream.close();
                inputStream.close();
                return code;
            }
            
            String result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject res = JSON.parseObject(result);
            if ((Integer) res.get("errcode") == 41030) {
                throw new BetahouseException(CommonResultCode.ILLEGAL_PARAMETERS.getCode(), "小程序没有发布或参数 page 不正确");
            } else if ((Integer) res.get("errcode") == 45009) {
                throw new BetahouseException(CommonResultCode.FORBIDDEN.getCode(), "调用分钟频率受限");
            }
            inputStream.close();
        } catch (JSONException | IOException ignored) {
        
        }
        return code;
    }
    
}

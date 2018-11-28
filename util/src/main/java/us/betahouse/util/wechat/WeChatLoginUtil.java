/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.util.wechat;

import com.alibaba.fastjson.JSONObject;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.HttpUtils;

import java.text.MessageFormat;

/**
 * 微信openId请求工具
 *
 * @author MessiahJK
 * @version : WeChatLoginUtil.java 2018/11/19 21:41 MessiahJK
 */
public class WeChatLoginUtil {


    /**
     * 请求openId 的url模板
     */
    private final static String OPEN_ID_URL_TEMPLATE = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";

    /**
     * 返回体的openId
     */
    private final static String OPEN_ID = "openid";

    /**
     * 返回体的错误码
     */
    private final static String ERROR_CODE = "errcode";

    /**
     * 获取openId
     *
     * @param code
     * @param appId
     * @param secret
     * @return
     */
    public static String fetchOpenId(String code, String appId, String secret) {
        String url = MessageFormat.format(OPEN_ID_URL_TEMPLATE, appId, secret, code);
        String result;
        try {
            result = HttpUtils.doGet(url);
        } catch (Exception e) {
            throw new BetahouseException(e, CommonResultCode.SYSTEM_ERROR.getCode(), "微信服务器请求失败, 网络异常");
        }
        JSONObject jsonObject = JSONObject.parseObject(result);

        String openId = jsonObject.getString(OPEN_ID);
        AssertUtil.assertStringNotBlank(openId, CommonResultCode.SYSTEM_ERROR.getCode(), MessageFormat.format("微信服务器请求失败:{0}", jsonObject.getString(ERROR_CODE)));

        return openId;
    }
}

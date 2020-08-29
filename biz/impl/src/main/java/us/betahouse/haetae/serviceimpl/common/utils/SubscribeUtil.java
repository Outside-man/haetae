package us.betahouse.haetae.serviceimpl.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryPublish;
import us.betahouse.haetae.serviceimpl.schedule.SubscriptionTask;
import us.betahouse.util.enums.CommonResultCode;
import us.betahouse.util.exceptions.BetahouseException;
import us.betahouse.util.utils.AssertUtil;
import us.betahouse.util.utils.DateUtil;
import us.betahouse.util.utils.HttpUtils;
import us.betahouse.util.wechat.WeChatAccessTokenUtil;

import java.text.MessageFormat;
import java.util.concurrent.locks.ReentrantLock;

/**
  * @Author kana-cr
  * @Date  2020/8/28 13:58
  */
public class SubscribeUtil {


    /**
     * 模板id
     */
    private final static String TEMPLATE_ID="oqC6UDgnz6u9FucXj0BPSz7CSn5rZUAT8cq2Ek8Obr4";
    /**
     *推送api
     */
    private final static String PUBLISH_URL="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token={0}";

    /**
     * 返回体的错误码
     */
    private final static String ERROR_CODE = "errcode";

    /**
     * 返回体的错误信息
     */
    private final static String ERROR_MESSAGE = "errmsg";
    /**
     *
     * @param openId
     * @param activityEntryPublish 活动信息对象
     * @return
     */
    private static final ReentrantLock lock = new ReentrantLock();

    public static String publishActivityByOpenId(String openId  , String accessToken, ActivityEntryPublish activityEntryPublish){
        AssertUtil.assertStringNotBlank(activityEntryPublish.getStart(),"开始时间不能为空");
        AssertUtil.assertStringNotBlank(activityEntryPublish.getActivityName(),"活动名称不能为空");
        AssertUtil.assertStringNotBlank(activityEntryPublish.getLocation(),"活动地点不能为空");


        //String accessToken= WeChatAccessTokenUtil.GetAccessToken(appId,secret);
        String url = MessageFormat.format(PUBLISH_URL,accessToken);

        StringBuilder publishBuilder = new StringBuilder();
        publishBuilder.append(DateUtil.getMediumDatesStr(activityEntryPublish.getStart())).append("起");
        String holdTime=publishBuilder.toString();

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("touser",openId);
        jsonObject.put("template_id",TEMPLATE_ID);

        JSONObject data=new JSONObject();
        JSONObject thing1 = new JSONObject();
        thing1.put("value",activityEntryPublish.getNote());

        JSONObject thing2 = new JSONObject();
        thing2.put("value",activityEntryPublish.getActivityName());
        JSONObject date3 = new JSONObject();
        date3.put("value",DateUtil.parse_CH(activityEntryPublish.getStart(),"yyyy-MM-dd HH:mm:ss"));
        JSONObject thing6 = new JSONObject();
        thing6.put("value",activityEntryPublish.getLocation());
        JSONObject thing5 = new JSONObject();
        thing5.put("value", holdTime);

        data.put("thing1", thing1);
        data.put("thing2",thing2);
        data.put("date3",date3);
        data.put("thing6",thing6);
        data.put("thing5",thing5);

        jsonObject.put("data",data);

        try {
            String result = HttpUtils.doPostUseJson(url,jsonObject.toString());

            JSONObject resultJson = JSONObject.parseObject(result);
            String code = resultJson.getString(ERROR_CODE);
            //GC
            jsonObject=null;
            result=null;
            publishBuilder=null;
            if (StringUtils.equals(code,"0")) {
                return CommonResultCode.SUCCESS.getCode();
            }else if (StringUtils.equals(code,"42001")){
                //防止token被连续刷新多次
                lock.lock();
                if (StringUtils.equals(accessToken,new SubscriptionTask().GetToken())) {
                    accessToken = new SubscriptionTask().refreshToken();
                    return SubscribeUtil.publishActivityByOpenId(openId, accessToken, activityEntryPublish);
                }
                lock.unlock();
            }else if (StringUtils.equals(code,"43101"))
                return CommonResultCode.FORBIDDEN.getCode();

        } catch (Exception e) {
            throw new BetahouseException(e, CommonResultCode.SYSTEM_ERROR.getCode(), "请求推送失败, 网络异常");
        }
        return CommonResultCode.SYSTEM_ERROR.getMessage();
    }
}

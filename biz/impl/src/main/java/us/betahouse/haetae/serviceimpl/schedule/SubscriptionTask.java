package us.betahouse.haetae.serviceimpl.schedule;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryPublish;
import us.betahouse.util.utils.DateUtil;
import us.betahouse.util.wechat.WeChatAccessTokenUtil;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SubscriptionTask {

    public static String AccessToken ;

    private static String appid;

    private static String secret;

    @Value("${wechat.appId}")
    public void setAppId(String appId) {    //注意这里的set方法不能是静态的
        SubscriptionTask.appid = appId;
    }
    @Value("${wechat.secret}")
    public void setSecret(String secret) {    //注意这里的set方法不能是静态的
        SubscriptionTask.secret = secret;
    }




   // static final ConcurrentHashMap<String, List<String>> SUBSCRIPTION_CONTAINER =new ConcurrentHashMap<>();

    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);


     public static synchronized String GetToken(){
        if (StringUtils.isEmpty(AccessToken))
            AccessToken = WeChatAccessTokenUtil.GetAccessToken(appid,secret);
        return AccessToken;
     }
     public static synchronized String refreshToken(){
         AccessToken = WeChatAccessTokenUtil.GetAccessToken(appid,secret);
         return AccessToken;
     }

  //  public static void putSubscriptionTask(ActivityEntryPublish activityEntryPublish){

   // }


}

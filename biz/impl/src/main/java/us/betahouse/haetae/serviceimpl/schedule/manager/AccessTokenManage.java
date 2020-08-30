package us.betahouse.haetae.serviceimpl.schedule.manager;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.betahouse.util.wechat.WeChatAccessTokenUtil;

import java.util.concurrent.locks.ReentrantLock;

@Component
public class AccessTokenManage {

    private static String AccessToken ;

    private static String appid;

    private static String secret;

    @Value("${wechat.appId}")
    public void setAppId(String appId) {    //注意这里的set方法不能是静态的
        AccessTokenManage.appid = appId;
    }

    @Value("${wechat.secret}")
    public void setSecret(String secret) {    //注意这里的set方法不能是静态的
        AccessTokenManage.secret = secret;
    }

    private static final ReentrantLock lock = new ReentrantLock();

     public static String GetToken(){
            if (StringUtils.isEmpty(AccessToken)) {
                lock.lock();
                if (AccessToken == null) {
                    AccessToken = WeChatAccessTokenUtil.GetAccessToken(appid, secret);
                }
                lock.unlock();
            }

        return AccessToken;
     }
     public static synchronized String refreshToken(){
         AccessToken = WeChatAccessTokenUtil.GetAccessToken(appid,secret);
         return AccessToken;
     }




}

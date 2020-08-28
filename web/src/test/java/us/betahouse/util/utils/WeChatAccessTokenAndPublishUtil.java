package us.betahouse.util.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.util.wechat.WeChatLoginUtil;
import us.betahouse.util.wechat.WeChatPublishUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatAccessTokenAndPublishUtil {

    private String APP_ID = "wxabf0acc20f14bbba";


    private String SECRET ="d85d7d457b94d5feb17ba31b6b8f24fd";

    @Test
    public void WeChatAccessTokenGet(){
       String accessToken = WeChatPublishUtil.GetAccessToken(APP_ID,SECRET);
        System.out.println(accessToken);
    }

    @Test
    public void WeChatPublish(){
        String accessToken = WeChatPublishUtil.GetAccessToken(APP_ID,SECRET);

    }

}

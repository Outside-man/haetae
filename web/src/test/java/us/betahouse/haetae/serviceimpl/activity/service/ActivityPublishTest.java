package us.betahouse.haetae.serviceimpl.activity.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.manager.ActivityManager;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryPublish;
import us.betahouse.haetae.serviceimpl.common.utils.PublishUtil;
import us.betahouse.util.utils.CollectionUtils;
import us.betahouse.util.utils.DateUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityPublishTest {

    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityManager activityManager;
    @Autowired
    ActivityEntryService activityEntryService;

    private String APP_ID = "wxabf0acc20f14bbba";

    private String SECRET ="d85d7d457b94d5feb17ba31b6b8f24fd";
    /**
     * 每天早上七点 查询是否有活动需要发布
     *
     */
    @Test
    public void GetActivityStartTime(){
        List<Date> activityStartTimeList;
        activityStartTimeList =
                CollectionUtils.toStream(activityManager.findAll())
              .filter(Objects::nonNull)
              .filter(ActivityBO::canPublish).map(ActivityBO::getStart)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
        System.out.println(activityStartTimeList);
    }

   @Test
    public void PublishActivityToday(){
       ActivityEntryPublish publish = new ActivityEntryPublish();
       publish.setStart("2020-08-28 13:00:00");
       publish.setEnd("2020-08-29 15:00:00");
       publish.setActivityName("测试活动");
       publish.setLocation("测试地点");
       publish.setNote("备注提示");
       PublishUtil.publishActivityByOpenId("oe38G5ju57Z57WXsqBygQUQmw-CQ",APP_ID,SECRET,publish);
   }

//todo 通过accessToken TemplateId 以及自定义的Data(发布内容) 来申请进行发布订阅消息
}

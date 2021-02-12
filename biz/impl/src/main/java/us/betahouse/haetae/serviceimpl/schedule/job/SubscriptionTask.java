package us.betahouse.haetae.serviceimpl.schedule.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryPublish;
import us.betahouse.haetae.serviceimpl.common.utils.SubscribeUtil;
import us.betahouse.haetae.serviceimpl.schedule.ScheduleTaskMap;
import us.betahouse.haetae.serviceimpl.schedule.manager.AccessTokenManage;

/**
  * @Author kana-cr
  * @Date  2020/8/29 16:38
  * 定时任务类
  **/
public class SubscriptionTask implements Runnable {

    private final Logger LOGGER = LoggerFactory.getLogger(SubscriptionTask.class);

    private ActivityEntryPublish activityEntryPublish;

    private String openid;

    private String page;
    /**
     * 定时消费任务 消费后不论成功失败都删除该任务
     */
    @Override
    public void run() {
        SubscribeUtil.publishActivityByOpenId(page,openid,AccessTokenManage.GetToken(),activityEntryPublish);
        //订阅发布后删除容器中的该任务
        LOGGER.info("消费订阅消息，订阅id为 {}",activityEntryPublish.getSubscribeId());
        ScheduleTaskMap.getInstance().cancelMap(activityEntryPublish.getSubscribeId() );
    }

    public SubscriptionTask(ActivityEntryPublish activityEntryPublish, String openid, String page) {
        this.activityEntryPublish = activityEntryPublish;
        this.openid = openid;
        this.page = page;
    }

    public ActivityEntryPublish getActivityEntryPublish() {
        return activityEntryPublish;
    }
}

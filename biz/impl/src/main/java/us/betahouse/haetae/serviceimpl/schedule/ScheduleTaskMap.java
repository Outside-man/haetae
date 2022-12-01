package us.betahouse.haetae.serviceimpl.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryPublish;
import us.betahouse.haetae.serviceimpl.schedule.dto.RealTask;
import us.betahouse.haetae.serviceimpl.schedule.job.SubscriptionTask;
import us.betahouse.util.utils.DateUtil;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduleTaskMap {


    /**
     * [秒] [分] [小时] [日] [月] [周] [年]
     */
    //0 1 2 分别为 小时 日 月
    private final static String CRON_TEMPLATE ="0 {0} {1} {2} {3} ?";

    private final Logger LOGGER = LoggerFactory.getLogger(ScheduleTaskMap.class);

    // 静态内部类实现单例模式
    private ScheduleTaskMap() { }

    private static class  ScheduleTaskMapInstance {
        private static final  ScheduleTaskMap INSTANCE = new  ScheduleTaskMap();
    }

    public static  ScheduleTaskMap getInstance() {
        return  ScheduleTaskMapInstance.INSTANCE;
    }

    /**
     * 启动后存储任务列表
     */
    private final static Map<String, RealTask> currentHashMap = new ConcurrentHashMap<>();

    /**
     * 工具类注入bean的方法
     */
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostConstruct
    public void init() {
        ScheduleTaskMapInstance.INSTANCE.threadPoolTaskScheduler = this.threadPoolTaskScheduler;
    }

    /**
     * 将任务列表处理存入Map容器中
     *
     * @param activityEntryPublish
     * @param openid
     * @param advanceTime  自定义提前时间 0 - 120分钟
     * @return
     */
    public RealTask putMap(int advanceTime,String page,ActivityEntryPublish activityEntryPublish , String openid) {
        System.out.println(threadPoolTaskScheduler);
        Date startTime=DateUtil.subMinute(DateUtil.parseTime_Database(activityEntryPublish.getStart()),advanceTime);
        //将订阅发布时间转化成cron表达式
        String cron = MessageFormat.format(CRON_TEMPLATE,DateUtil.getMinute(startTime), DateUtil.getHour(startTime),
               DateUtil.getDay(startTime), DateUtil.getMonth(startTime));

        RealTask realTask = new RealTask();
        realTask.setCron(cron);
        SubscriptionTask task =new SubscriptionTask(activityEntryPublish,openid,page);
        realTask.setTask(task);
        //延时队列
        ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));
        realTask.setFuture(future);
        currentHashMap.putIfAbsent(activityEntryPublish.getSubscribeId(), realTask);
        LOGGER.info("加入定时任务: {}",realTask.toString());

        return realTask;
    }

    /**
     * 根据ID暂停任务,同时在容器中删除
     * @param id
     * @return
     */
    public RealTask cancelMap(String id) {
        RealTask task = null;
        if(currentHashMap.containsKey(id)){
            task = currentHashMap.get(id);
            ScheduledFuture<?> future = task.getFuture();
            if (future != null) {
                future.cancel(true);
            }
            //删除任务
            currentHashMap.remove(id);
        }
        return task;
    }

    public ActivityEntryPublish delMap(String id){
        RealTask task = cancelMap(id);
        if (task != null){
            SubscriptionTask subTask = (SubscriptionTask)task.getTask();
            return subTask.getActivityEntryPublish();
        }
        return null;
    }
    /**
     * 查询是否存在任务
     */
    public ActivityEntryPublish ifExist(String id){
      RealTask task = currentHashMap.getOrDefault(id, null);
      if (task != null){
          SubscriptionTask subTask = (SubscriptionTask)task.getTask();
         return subTask.getActivityEntryPublish();
      }
      return null;
    }


}

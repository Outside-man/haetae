package us.betahouse.haetae.serviceimpl.Schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.serviceimpl.activity.model.ActivityEntryPublish;
import us.betahouse.haetae.serviceimpl.schedule.ScheduleTaskMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTest {

    @Test
    public void ThreadRejectHandlerTest(){
        int i=0;
        while (true){
            i++;
            ActivityEntryPublish activityEntryPublish = new ActivityEntryPublish();
            activityEntryPublish.setSubscribeId(String.valueOf(i));
            activityEntryPublish.setStart("2020-09-01 01:56:15");
            ScheduleTaskMap.getInstance().putMap(0,"",
                 activityEntryPublish  ,"");
        }
    }

}

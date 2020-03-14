package us.betahouse.haetae.serviceimpl.activity.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.model.basic.ActivityEntryRecordBO;
import us.betahouse.haetae.activity.request.ActivityEntryRecordRequest;
import us.betahouse.haetae.serviceimpl.activity.builder.ActivityEntryRecordRequestBuilder;
import us.betahouse.haetae.serviceimpl.activity.service.ActivityEntryService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityEntryServiceImplTest {

    @Autowired
    private ActivityEntryService activityEntryService;

    @Test
    public void deleteActivityEntryRecord() {
        ActivityEntryRecordRequest activityEntryRecordRequest = ActivityEntryRecordRequestBuilder.anActivityEntryRecordRequest()
                .withActivityEntryId("201812041846232069140910012018")
                .withUserId("201811302142154453730001201841")
                .build();
        ActivityEntryRecordBO activityEntryRecordBO = activityEntryService.undoSignUp(activityEntryRecordRequest);
        if(activityEntryRecordBO == null){
            System.out.println("取消报名成功");
        }else{
            System.out.println( "取消报名失败");
        }
    }
}

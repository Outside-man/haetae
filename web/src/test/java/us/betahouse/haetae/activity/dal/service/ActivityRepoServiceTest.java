package us.betahouse.haetae.activity.dal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.betahouse.haetae.activity.builder.ActivityBOBuilder;
import us.betahouse.haetae.activity.enums.ActivityStateEnum;
import us.betahouse.haetae.activity.enums.ActivityTypeEnum;
import us.betahouse.haetae.activity.model.basic.ActivityBO;
import us.betahouse.haetae.activity.model.common.PageList;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRepoServiceTest {
    @Autowired
    private ActivityRepoService activityRepoService;

    @Test
    public void queryAllActivity() {
    }

    @Test
    public void queryActivityByType() {
    }

    @Test
    public void createActivity() {
        ActivityBO activityBO = ActivityBOBuilder.getInstance()
                .withActivityName("2019义工")
                .withType(ActivityTypeEnum.VOLUNTEER_WORK.getCode())
                .withDescription("2019年义工")
                .withLocation("杭电信工")
                .withEnd(new Date())
                .withStart(new Date())
                .withOrganizationMessage("团学办公室")
                .withCreatorId("201811302141587352590001201845")
                .withTerm("2019A")
                .build();
        System.out.println(activityRepoService.createActivity(activityBO));
    }

    @Test
    public void updateActivity() {
    }

    @Test
    public void queryActivityByActivityId() {
    }

    @Test
    public void queryActivityByTermAndStateAndTypePager() {
        String term="2018A";
        String status=ActivityStateEnum.RESTARTED.getCode();
        String type=ActivityTypeEnum.SCHOOL_ACTIVITY.getCode();
        Integer page=0;
        Integer limit=10;
        PageList<ActivityBO> activityBOPageList=activityRepoService.queryActivityByTermAndStateAndTypePagerDESC(term, status, type, page, limit);
        for(ActivityBO activityBO:activityBOPageList.getContent()){
            System.out.println(activityBO);
        }
        System.out.println(activityBOPageList.getNumber());
        System.out.println(activityBOPageList.getNumberOfElements());
        System.out.println(activityBOPageList.getSize());
        System.out.println(activityBOPageList.getTotalElements());
        System.out.println(activityBOPageList.getTotalPages());
        System.out.println(activityBOPageList.isEnd());
        System.out.println(activityBOPageList.isFirst());
    }
}